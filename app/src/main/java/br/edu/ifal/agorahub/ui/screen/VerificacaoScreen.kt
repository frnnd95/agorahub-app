package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.data.ContaDados
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import com.google.firebase.FirebaseNetworkException

@Composable
fun VerificacaoScreen(navController: NavController, modifier: Modifier = Modifier) {
    var mensagem by remember { mutableStateOf("") }
    var enviando by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Verifique seu e-mail",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Enviamos um link para o seu e-mail institucional. Toque no link para confirmar a conta e depois entre no app. Confira também o spam.",
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = {
                mensagem = ""
                enviando = true
                ContaDados().enviarLinkConfirmacao(
                    aoEnviar = {
                        enviando = false
                        mensagem = "Enviamos um novo link. Confira a caixa de entrada e o spam."
                    },
                    aoFalhar = { erro ->
                        enviando = false
                        mensagem = when (erro) {
                            is FirebaseNetworkException ->
                                "Sem conexão com a internet. Tente de novo."
                            else -> "Não foi possível reenviar o link. Aguarde um pouco e tente de novo."
                        }
                    }
                )
            },
            enabled = !enviando,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = if (enviando) "Enviando…" else "Reenviar link",
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
        TextButton(
            onClick = { navController.navigate("entrar") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = "Já confirmei, entrar",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificacaoScreenPreview() {
    AgoraHubTheme {
        VerificacaoScreen(rememberNavController())
    }
}