package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.data.ProjetoDados
import br.edu.ifal.agorahub.data.projetosExemplo
import br.edu.ifal.agorahub.domain.Projeto
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.text.font.FontWeight
import br.edu.ifal.agorahub.ui.components.Selo
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// RF19: projetos criados pelo estudante logado
@Composable
fun MeusProjetosScreen(navController: NavController, modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var falhou by remember { mutableStateOf(false) }
    var projetos by remember { mutableStateOf(listOf<Projeto>()) }

    // Pede a lista uma vez, quando a tela abre
    LaunchedEffect(Unit) {
        try {
            projetos = ProjetoDados().listarMeus()
        } catch (e: Exception) {
            Log.e("CarregamentoMeusProjetos", "Erro ao carregar meus projetos", e)
            falhou = true
        }
        carregando = false
    }
    MeusProjetosConteudo(carregando, falhou, projetos, navController, modifier)
}

@Composable
private fun MeusProjetosConteudo(
    carregando: Boolean,
    falhou: Boolean,
    projetos: List<Projeto>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Text(
                text = "Meus projetos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        if (carregando) {
            item {
                Text(
                    text = "Carregando projetos...",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else if (falhou) {
            item {
                Text(
                    text = "Não foi possível carregar os projetos. Confira a conexão com a internet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else if (projetos.isEmpty()) {
            item {
                Text(
                    text = "Você ainda não criou projetos.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        items(projetos, key = { it.id }) { projeto ->
            ItemMeuProjeto(projeto, navController)
        }
    }
}

// Um projeto da lista: status, título e vagas (RN18)
@Composable
private fun ItemMeuProjeto(projeto: Projeto, navController: NavController) {
    val disponiveis = if (projeto.vagasDisponiveis == 1) {
        "1 vaga disponível"
    } else {
        "${projeto.vagasDisponiveis} vagas disponíveis"
    }
    val preenchidas = if (projeto.membros.size == 1) {
        "1 preenchida"
    } else {
        "${projeto.membros.size} preenchidas"
    }
    // Mesmas cores do selo nos detalhes do projeto
    val fundoStatus = when (projeto.status) {
        "Aberto" -> MaterialTheme.colorScheme.tertiaryContainer
        "Fechado" -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    val corStatus = when (projeto.status) {
        "Aberto" -> MaterialTheme.colorScheme.onTertiaryContainer
        "Fechado" -> MaterialTheme.colorScheme.onErrorContainer
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            // Tocar abre os detalhes (RF16); o TalkBack anuncia como botão
            .clickable(role = Role.Button, onClickLabel = "Ver detalhes") {
                navController.navigate("projeto/${projeto.id}")
            },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    )  {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Selo(texto = projeto.status, fundo = fundoStatus, cor = corStatus, comPonto = true)
            Text(
                text = projeto.titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$disponiveis · $preenchidas",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeusProjetosScreenPreview() {
    AgoraHubTheme {
        MeusProjetosConteudo(
            carregando = false,
            falhou = false,
            projetos = projetosExemplo, // dados de teste
            navController = rememberNavController()
        )
    }
}