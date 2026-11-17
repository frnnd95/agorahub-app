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
            interesses = interesses.filterIsInstance<String>(),
            status = doc.getString("status") ?: return null,
            responsavel = doc.getString("responsavel") ?: return null,
            criadoEm = doc.getTimestamp("criadoEm") ?: return null,
        )
    }
}
