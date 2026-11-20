package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.data.SolicitacaoDados
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import kotlinx.coroutines.launch

// Os motivos da RN27, na ordem da lista
private val motivos = listOf("Perfil não compatível", "Vagas preenchidas", "Outro")

@Composable
fun RecusarSolicitacaoScreen(
    id: String?,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var escolhido by remember { mutableStateOf("") }
    var textoOutro by remember { mutableStateOf("") }
    var erroMotivo by remember { mutableStateOf("") }
    var erroOutro by remember { mutableStateOf("") }
    var erroGeral by remember { mutableStateOf("") }
    var recusando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Recusar solicitação",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.semantics { heading() }
        )
        Text(
            text = "Escolha o motivo. O estudante vai ver o motivo da recusa.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Column(modifier = Modifier.selectableGroup()) {
            motivos.forEach { motivo ->
                OpcaoMotivo(
                    texto = motivo,
                    selecionada = escolhido == motivo,
                    aoEscolher = {
                        escolhido = motivo
                        erroMotivo = ""
                    }
                )
            }
        }
        if (erroMotivo != "") {
            Text(
                text = erroMotivo,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
        if (escolhido == "Outro") {
            OutlinedTextField(
                value = textoOutro,
                // RN27: até 200 caracteres; o 201º não entra
                onValueChange = { novo ->
                    if (novo.length <= 200) {
                        textoOutro = novo
                        erroOutro = ""
                    }
                },
                label = { Text("Motivo") },
                isError = erroOutro != "",
                supportingText = {
                    Text(if (erroOutro != "") erroOutro else "${textoOutro.length}/200")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (erroGeral != "") {
            Text(
                text = erroGeral,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
        Button(
            onClick = {
                // Confere a escolha e o texto antes de gravar (RNF05)
                val textoLimpo = textoOutro.trim()
                erroMotivo = if (escolhido == "") "Escolha um motivo." else ""
                erroOutro =
                    if (escolhido == "Outro" && textoLimpo == "") "Escreva o motivo." else ""
                if (id != null && erroMotivo == "" && erroOutro == "") {
                    val motivo = if (escolhido == "Outro") textoLimpo else escolhido
                    recusando = true
                    scope.launch {
                        try {
                            SolicitacaoDados().recusar(id, motivo)
                            // Volta às Solicitações recebidas, que leem o Firestore de novo
                            navController.popBackStack()
                        } catch (e: Exception) {
                            recusando = false
                            erroGeral =
                                "Não foi possível recusar a solicitação. Confira a internet e tente de novo."
                            Log.e("RecusarSolicitacao", "Erro ao recusar a solicitação", e)
                        }
                    }
                }
            },
            enabled = !recusando,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(if (recusando) "Recusando…" else "Recusar solicitação")
        }
    }
}

@Composable
private fun OpcaoMotivo(texto: String, selecionada: Boolean, aoEscolher: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .selectable(selected = selecionada, role = Role.RadioButton, onClick = aoEscolher),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(selected = selecionada, onClick = null)
        Text(text = texto, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun RecusarSolicitacaoScreenPreview() {
    AgoraHubTheme {
        RecusarSolicitacaoScreen(id = "1", navController = rememberNavController())
    }
}