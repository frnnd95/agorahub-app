package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun PrivacidadeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Privacidade e dados",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "O ÁgoraHub é um projeto acadêmico de estudantes de Sistemas de Informação do IFAL Arapiraca.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Quais dados o app coleta",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Só o seu nome, o seu e-mail institucional, o seu curso e os seus interesses.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Para que os dados são usados",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "O e-mail institucional serve para você criar a conta e entrar no app e, se você for aceito num projeto, para o responsável pelo projeto entrar em contato com você. O nome, o curso e os interesses formam o seu perfil, que ajuda na formação das equipes de projeto.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Quem vê cada dado",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Nome, curso e interesses: os outros estudantes que usam o app.",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "E-mail institucional: só você e o responsável por um projeto do qual você faz parte.",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Suas solicitações para participar de projetos: só você e o responsável pelo projeto.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Onde os dados ficam guardados",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "No Firebase, um serviço do Google.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Como corrigir ou excluir seus dados",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Para corrigir o nome, o curso ou os interesses, use Editar perfil.",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Para pedir a exclusão da sua conta e dos seus dados, mande um e-mail para a Equipe ÁgoraHub: agorahubifal@gmail.com",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacidadeScreenPreview() {
    AgoraHubTheme {
        PrivacidadeScreen()
    }
}