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

    fun entrar(
        email: String,
        senha: String,
        aoEntrar: () -> Unit,
        aoFalhar: (Exception) -> Unit
    ) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, senha)
            .addOnSuccessListener { aoEntrar() }
            .addOnFailureListener { erro -> aoFalhar(erro) }
    }

    fun enviarLinkSenha(
        email: String,
        aoEnviar: () -> Unit,
        aoFalhar: (Exception) -> Unit
    ) {
        FirebaseAuth.getInstance()
            .sendPasswordResetEmail(email)
            .addOnSuccessListener { aoEnviar() }
            .addOnFailureListener { erro -> aoFalhar(erro) }
    }

    fun enviarLinkConfirmacao(
        aoEnviar: () -> Unit,
        aoFalhar: (Exception) -> Unit
    ) {
        val usuario = FirebaseAuth.getInstance().currentUser
        if (usuario == null) {
            aoFalhar(Exception("Sem login"))
            return
        }
        usuario.sendEmailVerification()
            .addOnSuccessListener { aoEnviar() }
            .addOnFailureListener { erro -> aoFalhar(erro) }
    }

    fun estaLogado(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

    fun uidAtual(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

    fun emailConfirmado(): Boolean {
        return FirebaseAuth.getInstance().currentUser?.isEmailVerified == true
    }

    fun sair() {
        FirebaseAuth.getInstance().signOut()
    }
}