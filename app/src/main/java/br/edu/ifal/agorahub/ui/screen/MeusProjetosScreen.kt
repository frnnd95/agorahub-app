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

// RF19: projetos criados pelo estudante logado
@Composable
fun MeusProjetosScreen(modifier: Modifier = Modifier) {
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
    MeusProjetosConteudo(carregando, falhou, projetos, modifier)
}

@Composable
private fun MeusProjetosConteudo(
    carregando: Boolean,
    falhou: Boolean,
    projetos: List<Projeto>,
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
        // Por enquanto, só o título; o item completo vem no próximo incremento
        items(projetos, key = { it.id }) { projeto ->
            Text(
                text = projeto.titulo,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
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
            projetos = projetosExemplo // dados de teste
        )
    }
}