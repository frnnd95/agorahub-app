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