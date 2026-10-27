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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.layout.imePadding
import br.edu.ifal.agorahub.data.ContaDados
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

@Composable
fun CadastroScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmacaoSenha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    var confirmacaoVisivel by remember { mutableStateOf(false) }
    var erroEmail by remember { mutableStateOf("") }
    var erroSenha by remember { mutableStateOf("") }
    var erroConfirmacao by remember { mutableStateOf("") }
    var erroGeral by remember { mutableStateOf("") }
    var criandoConta by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
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
            supportingText = {
                Column {
                    if (erroSenha != "") Text(erroSenha)
                    Text(
                        text = "De 8 a 64 caracteres, com letras e números.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
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
        OutlinedTextField(
            value = confirmacaoSenha,
            onValueChange = { confirmacaoSenha = it },
            label = { Text("Confirmar senha") },
            singleLine = true,
            visualTransformation = if (confirmacaoVisivel) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = erroConfirmacao != "",
            supportingText = { if (erroConfirmacao != "") Text(erroConfirmacao) },
            trailingIcon = {
                IconButton(onClick = { confirmacaoVisivel = !confirmacaoVisivel }) {
                    Icon(
                        imageVector = if (confirmacaoVisivel) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (confirmacaoVisivel) "Ocultar confirmação da senha" else "Mostrar confirmação da senha"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Usamos seu e-mail apenas para login e, se você autorizar, para contato em projetos.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
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
        if (erroGeral != "") {
            Text(
                text = erroGeral,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
        Button(
            onClick = {
                val emailLimpo = email.trim().lowercase()
                erroEmail = mensagemErroEmail(emailLimpo)
                erroSenha = mensagemErroSenha(senha, emailLimpo)
                erroConfirmacao = mensagemErroConfirmacao(confirmacaoSenha, senha)
                erroGeral = ""
                if (erroEmail == "" && erroSenha == "" && erroConfirmacao == "") {
                    criandoConta = true
                    ContaDados().criarConta(
                        email = emailLimpo,
                        senha = senha,
                        aoCriar = {
                            navController.navigate("verificacao") {
                                popUpTo("abertura")
                            }
                        },
                        aoFalhar = { erro ->
                            criandoConta = false
                            when (erro) {
                                is FirebaseAuthUserCollisionException ->
                                    erroEmail =
                                        "Já existe uma conta com este e-mail. Entre ou recupere sua senha."

                                is FirebaseNetworkException ->
                                    erroGeral = "Sem conexão com a internet. Tente de novo."

                                else ->
                                    erroGeral = "Não foi possível criar a conta. Tente de novo."
                            }
                        }
                    )
                }
            },
            enabled = !criandoConta,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = if (criandoConta) "Criando conta…" else "Criar conta",
                style = MaterialTheme.typography.titleMedium
            )
        }
        TextButton(
            onClick = { navController.navigate("entrar") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = "Já tenho conta",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

private fun mensagemErroEmail(email: String): String {
    return when {
        email == "" -> "Preencha este campo."
        !email.endsWith("@aluno.ifal.edu.br") -> "Use seu e-mail institucional (@aluno.ifal.edu.br)."
        email.substringBefore("@") == "" -> "Use seu e-mail institucional (@aluno.ifal.edu.br)."
        else -> ""
    }
}

private fun mensagemErroSenha(senha: String, email: String): String {
    val parteEmail = email.substringBefore("@")
    return when {
        senha == "" -> "Preencha este campo."
        senha.length < 8 -> "A senha precisa ter pelo menos 8 caracteres."
        senha.length > 64 -> "A senha pode ter no máximo 64 caracteres."
        !senha.any { it.isLetter() } || !senha.any { it.isDigit() } -> "A senha precisa ter letras e números."
        parteEmail != "" && senha.contains(
            parteEmail,
            ignoreCase = true
        ) -> "A senha não pode conter a parte do seu e-mail antes do @."

        else -> ""
    }
}

private fun mensagemErroConfirmacao(confirmacao: String, senha: String): String {
    return when {
        confirmacao == "" -> "Preencha este campo."
        confirmacao != senha -> "As senhas não são iguais."
        else -> ""
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview() {
    AgoraHubTheme {
        CadastroScreen(rememberNavController())
    }
}