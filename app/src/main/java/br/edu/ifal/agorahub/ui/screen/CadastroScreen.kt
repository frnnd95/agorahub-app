package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CadastroScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember {mutableStateOf("")}
    var senha  by remember {mutableStateOf("")}
    var confirmacaoSenha by remember {mutableStateOf("")}

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Criar conta",
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("E-mail")},
            placeholder = { Text("nome@aluno.ifal.edu.br") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = senha,
            onValueChange = {senha = it},
            label = {Text("Senha")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = confirmacaoSenha,
            onValueChange = {confirmacaoSenha = it},
            label = {Text("Confirmar senha")},
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Usamos seu e-mail apenas para login e, se você autorizar, para contato em projetos.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = "Criar conta",
                style = MaterialTheme.typography.titleMedium
            )
        }
        TextButton(
            onClick = { navController.navigate("privacidade") },
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(
                text = "Ver privacidade e dados",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview() {
    AgoraHubTheme {
        CadastroScreen(rememberNavController())
    }
}