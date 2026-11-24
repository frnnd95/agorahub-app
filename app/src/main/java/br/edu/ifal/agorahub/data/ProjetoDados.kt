package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Projeto
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProjetoDados {
    suspend fun listarHub(): List<Projeto> {
        val resultado = FirebaseFirestore.getInstance()
            .collection("projetos")
            .get()
            .await()
        // Converte cada documento; os incompletos ficam de fora
        val todos = resultado.documents.mapNotNull { converter(it) }
        // RN19: só Aberto com vagas, do mais recente para o mais antigo
        val abertos = todos.filter { it.status == "Aberto" && it.vagasDisponiveis > 0 }
        return abertos.sortedByDescending { it.criadoEm }
    }

    suspend fun buscarPorId(id: String?): Projeto? {
        if (id == null) {
            return null
        }
        // Espera o Firestore responder; se falhar, lança uma exceção
        val doc = FirebaseFirestore.getInstance()
            .collection("projetos")
            .document(id)
            .get()
            .await()
        // Documento inexistente ou incompleto vira null
        return converter(doc)
    }

    // RF19: projetos do estudante logado, em todos os status
    suspend fun listarMeus(): List<Projeto> {
        val uid = ContaDados().uidAtual()
            ?: throw IllegalStateException("Nenhum estudante logado")
        val resultado = FirebaseFirestore.getInstance()
            .collection("projetos")
            .get()
            .await()
        val todos = resultado.documents.mapNotNull { converter(it) }
        val meus = todos.filter { it.responsavel == uid }
        // Do mais recente para o mais antigo (sugestão da ficha)
        return meus.sortedByDescending { it.criadoEm }
    }

    // RF13: grava um projeto novo com os campos da seção 9.3
    suspend fun criarProjeto(
        titulo: String,
        descricao: String,
        vagas: Int,
        interesses: List<String>
    ) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: throw IllegalStateException("Nenhum estudante logado")
        val dados = mapOf(
            "titulo" to titulo,
            "descricao" to descricao,
            "vagas" to vagas,
            "interesses" to interesses,
            "status" to "Aberto",          // RN17
            "responsavel" to uid,          // RN17
            "membros" to listOf<String>(),
            "criadoEm" to Timestamp.now(),
        )
        FirebaseFirestore.getInstance()
            .collection("projetos")
            .add(dados)
            .await()
    }
    // RF25: grava só o título e a descrição; o resto do documento não muda
    suspend fun editarProjeto(id: String, titulo: String, descricao: String) {
        val dados = mapOf(
            "titulo" to titulo,
            "descricao" to descricao,
        )
        FirebaseFirestore.getInstance()
            .collection("projetos")
            .document(id)
            .update(dados)
            .await()
    }

    // RF26: encerra o projeto e recusa as pendentes numa só gravação (RN30, D25)
    suspend fun encerrar(id: String) {
        val uid = ContaDados().uidAtual()
            ?: throw IllegalStateException("Nenhum estudante logado")
        val banco = FirebaseFirestore.getInstance()
        // As pendentes deste projeto; a regra só libera a consulta por responsavel (D24)
        val pendentes = banco.collection("solicitacoes")
            .whereEqualTo("responsavel", uid)
            .whereEqualTo("projeto", id)
            .whereEqualTo("status", "Pendente")
            .get()
            .await()
        val lote = banco.batch()
        lote.update(banco.collection("projetos").document(id), mapOf("status" to "Encerrado"))
        for (doc in pendentes.documents) {
            lote.update(
                banco.collection("solicitacoes").document(doc.id),
                mapOf("status" to "Recusada", "motivo" to "Projeto encerrado")
            )
        }
        // Ou grava tudo, ou nada; se falhar, lança uma exceção
        lote.commit().await()
    }

    // Documento do Firestore para Projeto (seção 9.3)
    private fun converter(doc: DocumentSnapshot): Projeto? {
        val vagas = doc.getLong("vagas") ?: return null
        val membros = doc.get("membros") as? List<*> ?: return null
        val interesses = doc.get("interesses") as? List<*> ?: return null
        return Projeto(
            id = doc.id,
            titulo = doc.getString("titulo") ?: return null,
            descricao = doc.getString("descricao") ?: return null,
            vagasDisponiveis = vagas.toInt() - membros.size, // RN18
            vagas = vagas.toInt(),
            membros = membros.filterIsInstance<String>(),
            interesses = interesses.filterIsInstance<String>(),
            status = doc.getString("status") ?: return null,
            responsavel = doc.getString("responsavel") ?: return null,
            criadoEm = doc.getTimestamp("criadoEm") ?: return null,
        )
    }
}
