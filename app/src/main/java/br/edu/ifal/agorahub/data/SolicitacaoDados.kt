package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Solicitacao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.google.firebase.Timestamp

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