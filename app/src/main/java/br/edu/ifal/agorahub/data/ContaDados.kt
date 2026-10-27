package br.edu.ifal.agorahub.data

import com.google.firebase.auth.FirebaseAuth

class ContaDados {
    fun criarConta(
        email: String,
        senha: String,
        aoCriar: () -> Unit,
        aoFalhar: (Exception) -> Unit
    ) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, senha)
            .addOnSuccessListener { aoCriar() }
            .addOnFailureListener { erro -> aoFalhar(erro) }
    }
}