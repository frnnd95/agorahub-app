package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.data.PerfilDados
import br.edu.ifal.agorahub.domain.Perfil
import br.edu.ifal.agorahub.ui.components.CartaoPerfil
import br.edu.ifal.agorahub.ui.components.SeletorInteresses
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import kotlinx.coroutines.launch

@Composable
fun CompletarPerfilScreen(navController: NavController, modifier: Modifier = Modifier) {
    var nome by remember { mutableStateOf("") }
    var outroCurso by remember { mutableStateOf(false) }
    var nomeCurso by remember { mutableStateOf("") }
    var escolhidos by remember { mutableStateOf(listOf<String>()) }
    var erroNome by remember { mutableStateOf("") }
    var erroCurso by remember { mutableStateOf("") }
    var erroInteresses by remember { mutableStateOf("") }
    var erroGeral by remember { mutableStateOf("") }
    var salvando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Complete seu perfil", style = MaterialTheme.typography.titleLarge)
        Text(
            text = "Seu nome, seu curso e seus interesses aparecem para os outros estudantes no app.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            singleLine = true,
            isError = erroNome != "",
            supportingText = { if (erroNome != "") Text(erroNome) },
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Curso", style = MaterialTheme.typography.titleSmall)
        Column(modifier = Modifier.selectableGroup()) {
            OpcaoCurso(
                texto = "Sistemas de Informação",
                selecionada = !outroCurso,
                aoEscolher = { outroCurso = false; erroCurso = "" }
            )
            OpcaoCurso(
                texto = "Outro curso",
                selecionada = outroCurso,
                aoEscolher = { outroCurso = true }
            )
        }
        if (outroCurso) {
            OutlinedTextField(
                value = nomeCurso,
                onValueChange = { nomeCurso = it },
                label = { Text("Nome do curso") },
                singleLine = true,
                isError = erroCurso != "",
                supportingText = { if (erroCurso != "") Text(erroCurso) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        SeletorInteresses(
            escolhidos = escolhidos,
            aoMudar = { nova ->
                escolhidos = nova
                erroInteresses = ""
            }
        )
        if (erroInteresses != "") {
            Text(
                text = erroInteresses,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
        // Prévia do perfil, como os colegas vão ver (RN05)
        Text(
            text = "COMO OS OUTROS VEEM",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "É isso que o responsável por um projeto vê quando você pede para participar. Seu e-mail não aparece.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        CartaoPerfil(
            nome = if (nome.trim() == "") "Seu nome" else nome.trim(),
            curso = if (!outroCurso) "Sistemas de Informação"
            else if (nomeCurso.trim() == "") "Seu curso"
            else nomeCurso.trim(),
            interesses = escolhidos
        )
        if (erroGeral != "") {
            Text(
                text = erroGeral,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
        Button(
            onClick = {
                val nomeLimpo = nome.trim()
                val curso = if (outroCurso) nomeCurso.trim() else "Sistemas de Informação"
                erroNome = if (nomeLimpo == "") "Preencha este campo." else ""
                erroCurso = if (curso == "") "Informe o nome do curso." else ""
                erroInteresses = if (escolhidos.size < 3) "Escolha pelo menos 3 interesses." else ""
                erroGeral = if (erroNome != "" || erroCurso != "") "Confira os campos no início da tela." else ""
                if (erroNome == "" && erroCurso == "" && erroInteresses == "") {
                    salvando = true
                    scope.launch {
                        try {
                            PerfilDados().salvarPerfil(
                                Perfil(nome = nomeLimpo, curso = curso, interesses = escolhidos)
                            )
                            navController.navigate("mural") {
                                popUpTo("completar-perfil") { inclusive = true }
                            }
                        } catch (e: Exception) {
                            salvando = false
                            erroGeral = "Não foi possível salvar o perfil. Confira a internet e tente de novo."
                            Log.e("CompletarPerfil", "Erro ao salvar perfil", e)
                        }
                    }
                }
            },
            enabled = !salvando,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = if (salvando) "Salvando…" else "Salvar e continuar",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
@Composable
private fun OpcaoCurso(texto: String, selecionada: Boolean, aoEscolher: () -> Unit) {
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
fun CompletarPerfilScreenPreview() {
    AgoraHubTheme {
        CompletarPerfilScreen(rememberNavController())
    }
}
