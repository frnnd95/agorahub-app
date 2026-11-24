package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import br.edu.ifal.agorahub.ui.components.SeletorInteresses
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import br.edu.ifal.agorahub.data.ProjetoDados
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect

// RF13: formulário para criar um projeto
@Composable
fun NovoProjetoScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    editar: Boolean = false,
    id: String? = null // RF25: projeto que será editado
) {
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    // Vagas como texto, porque o campo devolve texto
    var vagas by remember { mutableStateOf("") }
    var interesses by remember { mutableStateOf(listOf<String>()) }
    var erroTitulo by remember { mutableStateOf("") }
    var erroDescricao by remember { mutableStateOf("") }
    var erroVagas by remember { mutableStateOf("") }
    var erroInteresses by remember { mutableStateOf("") }
    var erroGeral by remember { mutableStateOf("") }
    var criando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var carregando by remember { mutableStateOf(editar) }
    // RF25: no modo editar, preenche os campos com o projeto atual
    LaunchedEffect(Unit) {
        if (editar) {
            try {
                val projeto = ProjetoDados().buscarPorId(id)
                if (projeto != null) {
                    titulo = projeto.titulo
                    descricao = projeto.descricao
                } else {
                    erroGeral = "Projeto não encontrado."
                }
            } catch (e: Exception) {
                erroGeral = "Não foi possível carregar o projeto. Confira a conexão com a internet."
                Log.e("EditarProjeto", "Erro ao carregar o projeto", e)
            }
            carregando = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = if (editar) "Editar projeto" else "Novo projeto",
            style = MaterialTheme.typography.titleLarge
        )
        if (carregando) {
            Text(text = "Carregando projeto...", style = MaterialTheme.typography.bodyLarge)
        }
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            singleLine = true,
            isError = erroTitulo != "",
            supportingText = { if (erroTitulo != "") Text(erroTitulo) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição") },
            isError = erroDescricao != "",
            supportingText = { if (erroDescricao != "") Text(erroDescricao) },
            modifier = Modifier.fillMaxWidth()
        )
        // RF25: vagas e interesses não são editados
        if (!editar) {
            OutlinedTextField(
                value = vagas,
                onValueChange = { vagas = it },
                label = { Text("Número de vagas") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = erroVagas != "",
                supportingText = { if (erroVagas != "") Text(erroVagas) },
                modifier = Modifier.fillMaxWidth()
            )
            SeletorInteresses(
                escolhidos = interesses,
                aoMudar = { nova -> interesses = nova },
                titulo = "Interesses procurados",
                orientacao = "Escolha de 1 a 5 interesses."
            )
            if (erroInteresses != "") {
                Text(
                    text = erroInteresses,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
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
                // RN16: confere cada campo e mostra o erro junto dele
                val tituloLimpo = titulo.trim()
                val descricaoLimpa = descricao.trim()
                val numeroVagas = vagas.trim().toIntOrNull()
                erroTitulo = if (tituloLimpo == "") "Preencha este campo."
                else if (editar && tituloLimpo.length < 5) "O título precisa ter pelo menos 5 caracteres."
                else if (tituloLimpo.length > 80) "O título pode ter no máximo 80 caracteres."
                else ""
                erroDescricao = if (descricaoLimpa == "") "Preencha este campo."
                else if (editar && descricaoLimpa.length < 20) "A descrição precisa ter pelo menos 20 caracteres."
                else if (descricaoLimpa.length > 1000) "A descrição pode ter no máximo 1000 caracteres."
                else ""
                // RF25: no modo editar, vagas e interesses não são conferidos
                erroVagas = if (!editar && (numeroVagas == null || numeroVagas < 1 || numeroVagas > 10))
                    "Informe um número de 1 a 10." else ""
                erroInteresses = if (!editar && interesses.isEmpty()) "Escolha pelo menos 1 interesse." else ""
                erroGeral = if (erroTitulo != "" || erroDescricao != "" || erroVagas != "")
                    "Confira os campos no início da tela." else ""
                val valido = erroTitulo == "" && erroDescricao == "" &&
                        erroVagas == "" && erroInteresses == ""
                if (valido && !editar && numeroVagas != null) {
                    criando = true
                    scope.launch {
                        try {
                            ProjetoDados().criarProjeto(
                                tituloLimpo, descricaoLimpa, numeroVagas, interesses
                            )
                            // Volta ao Hub, que lê o Firestore de novo
                            navController.popBackStack()
                        } catch (e: Exception) {
                            criando = false
                            erroGeral = "Não foi possível criar o projeto. Confira a internet e tente de novo."
                            Log.e("NovoProjeto", "Erro ao criar projeto", e)
                        }
                    }
                }
                // RF25: no modo editar, grava só título e descrição
                if (valido && editar && id != null) {
                    criando = true
                    scope.launch {
                        try {
                            ProjetoDados().editarProjeto(id, tituloLimpo, descricaoLimpa)
                            // Volta aos detalhes, que leem o Firestore de novo
                            navController.popBackStack()
                        } catch (e: Exception) {
                            criando = false
                            erroGeral = "Não foi possível salvar as alterações. Confira a internet e tente de novo."
                            Log.e("EditarProjeto", "Erro ao salvar o projeto", e)
                        }
                    }
                }
            },
            enabled = !criando && !carregando,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(if (editar) "Salvar alterações" else "Criar projeto")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NovoProjetoScreenPreview() {
    AgoraHubTheme {
        NovoProjetoScreen(rememberNavController())
    }
}