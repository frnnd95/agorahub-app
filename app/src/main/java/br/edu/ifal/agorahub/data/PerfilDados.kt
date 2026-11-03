package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Perfil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PerfilDados {
    fun buscarPerfil(): Perfil {
        return perfilExemplo
    }
    suspend fun perfilCompleto(): Boolean {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return false
        val documento = FirebaseFirestore.getInstance()
            .collection("estudantes")
            .document(uid)
            .get()
            .await()
        return documento.exists()
    }

    suspend fun salvarPerfil(perfil: Perfil) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: throw IllegalStateException("Nenhum estudante logado")
        val dados = mapOf(
            "nome" to perfil.nome,
            "curso" to perfil.curso,
            "interesses" to perfil.interesses,
        )
        FirebaseFirestore.getInstance()
            .collection("estudantes")
            .document(uid)
            .set(dados)
            .await()
    }
}


