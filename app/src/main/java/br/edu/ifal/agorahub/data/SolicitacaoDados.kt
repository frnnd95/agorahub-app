package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Projeto
import br.edu.ifal.agorahub.domain.Solicitacao
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SolicitacaoDados {
    // As solicitações de quem está logado para um projeto (RN21)
    suspend fun listarMinhasDoProjeto(projetoId: String): List<Solicitacao> {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return listOf()
        // A regra só deixa ler as próprias solicitações
        val resultado = FirebaseFirestore.getInstance()
            .collection("solicitacoes")
            .whereEqualTo("solicitante", uid)
            .whereEqualTo("projeto", projetoId)
            .get()
            .await()
        return resultado.documents.mapNotNull { converter(it) }
    }

    // Grava a solicitação e o e-mail numa só operação (D23 a D25)
    suspend fun enviar(projetoId: String, responsavel: String) {
        val usuario = FirebaseAuth.getInstance().currentUser ?: throw Exception("Sem login")
        val banco = FirebaseFirestore.getInstance()
        val solicitacao = banco.collection("solicitacoes").document()
        val lote = banco.batch()
        lote.set(solicitacao, mapOf(
            "projeto" to projetoId,
            "solicitante" to usuario.uid,
            "responsavel" to responsavel,
            "status" to "Pendente",
            "criadaEm" to Timestamp.now()
        ))
        lote.set(banco.collection("emails").document(solicitacao.id), mapOf(
            "email" to usuario.email,
            "solicitante" to usuario.uid
        ))
        lote.commit().await()
    }

    // RF22: recusa uma solicitação Pendente, com o motivo (RN24, RN27)
    suspend fun recusar(id: String, motivo: String) {
        FirebaseFirestore.getInstance()
            .collection("solicitacoes")
            .document(id)
            .update(mapOf("status" to "Recusada", "motivo" to motivo))
            .await()
    }

    // RF21: aceita uma solicitação Pendente, num só lote (D25)
    // Devolve true se as vagas acabaram e o projeto foi fechado (RN26)
    suspend fun aceitar(solicitacao: Solicitacao, projeto: Projeto): Boolean {
        val banco = FirebaseFirestore.getInstance()
        val lote = banco.batch()
        val membros = projeto.membros + solicitacao.solicitante
        val fechou = membros.size >= projeto.vagas

        lote.update(
            banco.collection("solicitacoes").document(solicitacao.id),
            mapOf("status" to "Aceita")
        )
        val documentoProjeto = banco.collection("projetos").document(projeto.id)
        if (fechou) {
            lote.update(documentoProjeto, mapOf("membros" to membros, "status" to "Fechado"))
            // As outras Pendentes do projeto; a regra exige o filtro por responsavel (D24)
            val pendentes = banco.collection("solicitacoes")
                .whereEqualTo("responsavel", solicitacao.responsavel)
                .whereEqualTo("projeto", projeto.id)
                .whereEqualTo("status", "Pendente")
                .get()
                .await()
            for (doc in pendentes.documents) {
                if (doc.id != solicitacao.id) {
                    lote.update(doc.reference, mapOf("status" to "Recusada", "motivo" to "Vagas preenchidas"))
                }
            }
        } else {
            lote.update(documentoProjeto, mapOf("membros" to membros))
        }
        lote.commit().await()
        return fechou
    }

    // RF20: as solicitações dos projetos de quem está logado (D24)
    suspend fun listarRecebidas(): List<Solicitacao> {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return listOf()
        // A regra só deixa o responsável ler as solicitações dos seus projetos
        val resultado = FirebaseFirestore.getInstance()
            .collection("solicitacoes")
            .whereEqualTo("responsavel", uid)
            .get()
            .await()
        // Ordena no app, sem orderBy: da mais recente para a mais antiga
        return resultado.documents
            .mapNotNull { converter(it) }
            .sortedByDescending { it.criadaEm }
    }

    // RF18: as solicitações enviadas por quem está logado (D24)
    suspend fun listarMinhas(): List<Solicitacao> {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return listOf()
        // A regra só deixa o solicitante ler as próprias solicitações
        val resultado = FirebaseFirestore.getInstance()
            .collection("solicitacoes")
            .whereEqualTo("solicitante", uid)
            .get()
            .await()
        // Ordena no app, sem orderBy: da mais recente para a mais antiga
        return resultado.documents
            .mapNotNull { converter(it) }
            .sortedByDescending { it.criadaEm }
    }

    private fun converter(doc: DocumentSnapshot): Solicitacao? {
        return Solicitacao(
            id = doc.id,
            projeto = doc.getString("projeto") ?: return null,
            solicitante = doc.getString("solicitante") ?: return null,
            responsavel = doc.getString("responsavel") ?: return null,
            status = doc.getString("status") ?: return null,
            motivo = doc.getString("motivo"),
            criadaEm = doc.getTimestamp("criadaEm") ?: return null,
        )
    }
}