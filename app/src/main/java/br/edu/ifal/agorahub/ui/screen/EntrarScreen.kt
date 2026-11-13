package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.data.ContaDados
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

@Composable
fun EntrarScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    var erroEmail by remember { mutableStateOf("") }
    var erroSenha by remember { mutableStateOf("") }
    var erroGeral by remember { mutableStateOf("") }
    var entrando by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Entrar no ÁgoraHub",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Só para estudantes do IFAL.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (erroGeral != "") {
            Text(
                text = erroGeral,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
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
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = erroSenha != "",
            supportingText = { if (erroSenha != "") Text(erroSenha) },
            trailingIcon = {
                IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                    Icon(
                        imageVector = if (senhaVisivel) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        TextButton(
            onClick = { navController.navigate("recuperar-senha") },
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(
                text = "Esqueci a senha",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textDecoration = TextDecoration.Underline
            )
        }
        Button(
            onClick = {
                val emailLimpo = email.trim().lowercase()
                erroEmail = if (emailLimpo == "") "Preencha este campo." else ""
                erroSenha = if (senha == "") "Preencha este campo." else ""
                erroGeral = ""
                if (erroEmail == "" && erroSenha == "") {
                    entrando = true
                    ContaDados().entrar(
                        email = emailLimpo,
                        senha = senha,
                        aoEntrar = {
                            navController.navigate("inicio") {
                                popUpTo("abertura") { inclusive = true }
                            }
                        },
                        aoFalhar = { erro ->
                            entrando = false
                            erroGeral = when (erro) {
                                is FirebaseAuthInvalidCredentialsException,
                                is FirebaseAuthInvalidUserException ->
                                    "E-mail ou senha incorretos. Confira e tente de novo."

                                is FirebaseNetworkException ->
                                    "Sem conexão com a internet. Tente de novo."

                                else ->
                                    "Não foi possível entrar. Tente de novo."
                            }
                        }
                    )
                }
            },
            enabled = !entrando,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = if (entrando) "Entrando…" else "Entrar",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntrarScreenPreview() {
    AgoraHubTheme {
        EntrarScreen(rememberNavController())
    }
}