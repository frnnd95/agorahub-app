package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import br.edu.ifal.agorahub.data.ContaDados
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun RecuperarSenhaScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var erroEmail by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }
    var enviando by remember { mutableStateOf(false) }
    // RN04: o mesmo texto com ou sem conta
    val mensagemEnvio = "Se houver uma conta vinculada a esse e-mail, enviamos um link para criar uma nova senha. Confira a caixa de entrada e o spam."

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Recuperar a senha",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Digite o seu e-mail institucional para receber o link.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail institucional") },
            singleLine = true,
            placeholder = { Text("nome@aluno.ifal.edu.br") },
            isError = erroEmail != "",
            supportingText = { if (erroEmail != "") Text(erroEmail) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val emailLimpo = email.trim().lowercase()
                erroEmail = if (emailLimpo == "") "Preencha este campo." else ""
                mensagem = ""
                if (erroEmail == "") {
                    enviando = true
                    ContaDados().enviarLinkSenha(
                        email = emailLimpo,
                        aoEnviar = {
                            enviando = false
                            mensagem = mensagemEnvio
                        },
                        aoFalhar = { erro ->
                            enviando = false
                            mensagem = when (erro) {
                                is FirebaseAuthInvalidUserException -> mensagemEnvio
                                is FirebaseNetworkException ->
                                    "Sem conexão com a internet. Tente de novo."
                                else -> "Não foi possível enviar o link. Tente de novo."
                            }
                        }
                    )
                }
            },
            enabled = !enviando,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = if (enviando) "Enviando…" else "Enviar link",
                style = MaterialTheme.typography.titleMedium
            )
        }
        if (mensagem != "") {
            Text(
                text = mensagem,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecuperarSenhaScreenPreview() {
    AgoraHubTheme {
        RecuperarSenhaScreen()
    }
}