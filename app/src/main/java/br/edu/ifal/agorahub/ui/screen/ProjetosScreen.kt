package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.data.ContaDados
import br.edu.ifal.agorahub.data.PerfilDados
import br.edu.ifal.agorahub.data.ProjetoDados
import br.edu.ifal.agorahub.data.projetosExemplo
import br.edu.ifal.agorahub.domain.Projeto
import br.edu.ifal.agorahub.ui.components.CardProjeto
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import br.edu.ifal.agorahub.data.InteresseDados

@Composable
fun ProjetosScreen(navController: NavController, modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var falhou by remember { mutableStateOf(false) }
    var projetos by remember { mutableStateOf(listOf<Projeto>()) }
    var nomes by remember { mutableStateOf(mapOf<String, String>()) }
    var meuUid by remember { mutableStateOf<String?>(null) }
    var meusInteresses by remember { mutableStateOf(listOf<String>()) }

    // Pede a lista uma vez, quando a tela abre; depois, o nome de cada responsável
    LaunchedEffect(Unit) {
        try {
            val lista = ProjetoDados().listarHub()
            val encontrados = mutableMapOf<String, String>()
            for (uid in lista.map { it.responsavel }.distinct()) {
                val nome = PerfilDados().buscarPerfilDe(uid)?.nome
                if (nome != null) {
                    encontrados[uid] = nome
                }
            }
            nomes = encontrados
            projetos = lista
            meuUid = ContaDados().uidAtual()
            meusInteresses = PerfilDados().buscarPerfil()?.interesses ?: listOf()
        } catch (e: Exception) {
            Log.e("CarregamentoProjetos", "Erro ao carregar projetos", e)
            falhou = true
        }
        carregando = false
    }
    ProjetosConteudo(carregando, falhou, projetos, nomes, meuUid, meusInteresses, navController, modifier)
}
@Composable
private fun ProjetosConteudo(
    carregando: Boolean,
    falhou: Boolean,
    projetos: List<Projeto>,
    nomes: Map<String, String>,
    meuUid: String?,
    meusInteresses: List<String>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Hub de Projetos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            // RF13: abre o formulário de novo projeto
            Button(
                onClick = { navController.navigate("novo-projeto") },
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.size(8.dp))
                Text("Novo projeto")
            }
        }
        item {
            // RF15: filtro por interesse
            FiltroInteresse(modifier = Modifier.padding(16.dp))
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
                    text = "Nenhum projeto com vagas no momento.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        items(projetos, key = { it.id }) { projeto ->
            CardProjeto(
                projeto = projeto,
                nomeResponsavel = nomes[projeto.responsavel],
                seuProjeto = projeto.responsavel == meuUid,
                meusInteresses = meusInteresses,
                navController = navController
            )
        }
    }
}

// RF15: opções do filtro (por enquanto só o layout, sem filtrar)
@Composable
private fun FiltroInteresse(modifier: Modifier = Modifier) {
    val opcoes = remember {
        var lista = listOf("Todos")
        InteresseDados().listarCategorias().forEach { categoria ->
            lista = lista + categoria.interesses
        }
        lista
    }
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        opcoes.forEach { opcao ->
            FilterChip(
                selected = opcao == "Todos",
                onClick = { },
                label = { Text(opcao) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjetosScreenPreview() {
    AgoraHubTheme {
        ProjetosConteudo(
            carregando = false,
            falhou = false,
            projetos = projetosExemplo,
            nomes = mapOf("uid-exemplo-1" to "Marina Coutinho", "uid-exemplo-2" to "Pedro Henrique"),
            meuUid = "uid-exemplo-1",
            meusInteresses = listOf("Mobile", "Banco de Dados"),
            navController = rememberNavController()
        )
    }
}