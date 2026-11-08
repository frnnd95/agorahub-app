package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Projeto
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
        return resultado.documents.mapNotNull { converter(it) }
    }

    // Documento do Firestore para Projeto (estrutura da D21)
    private fun converter(doc: DocumentSnapshot): Projeto? {
        val vagas = doc.getLong("vagas") ?: return null
        val membros = doc.get("membros") as? List<*> ?: return null
        val interesses = doc.get("interesses") as? List<*> ?: return null
        return Projeto(
            titulo = doc.getString("titulo") ?: return null,
            vagasDisponiveis = vagas.toInt() - membros.size, // RN18
            interesses = interesses.filterIsInstance<String>(),
            status = doc.getString("status") ?: return null,
            criadoEm = doc.getTimestamp("criadoEm") ?: return null,
        )
    }
}