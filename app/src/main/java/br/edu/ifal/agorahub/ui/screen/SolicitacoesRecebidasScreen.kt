package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.ifal.agorahub.data.PerfilDados
import br.edu.ifal.agorahub.data.ProjetoDados
import br.edu.ifal.agorahub.data.SolicitacaoDados
import br.edu.ifal.agorahub.data.projetosExemplo
import br.edu.ifal.agorahub.data.solicitacoesExemplo
import br.edu.ifal.agorahub.data.solicitantesExemplo
import br.edu.ifal.agorahub.domain.Perfil
import br.edu.ifal.agorahub.domain.Solicitacao
import br.edu.ifal.agorahub.ui.components.CartaoPerfil
import br.edu.ifal.agorahub.ui.components.Selo
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun SolicitacoesRecebidasScreen(navController: NavController, modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var falhou by remember { mutableStateOf(false) }
    var solicitacoes by remember { mutableStateOf(listOf<Solicitacao>()) }
    var perfis by remember { mutableStateOf(mapOf<String, Perfil>()) }
    var titulos by remember { mutableStateOf(mapOf<String, String>()) }

    // Pede a lista uma vez; depois, o perfil de cada solicitante e o título de cada projeto
    LaunchedEffect(Unit) {
        try {
            val lista = SolicitacaoDados().listarRecebidas()
            val perfisEncontrados = mutableMapOf<String, Perfil>()
            for (uid in lista.map { it.solicitante }.distinct()) {
                val perfil = PerfilDados().buscarPerfilDe(uid)
                if (perfil != null) {
                    perfisEncontrados[uid] = perfil
                }
            }
            val titulosEncontrados = mutableMapOf<String, String>()
            for (id in lista.map { it.projeto }.distinct()) {
                val projeto = ProjetoDados().buscarPorId(id)
                if (projeto != null) {
                    titulosEncontrados[id] = projeto.titulo
                }
            }
            perfis = perfisEncontrados
            titulos = titulosEncontrados
            solicitacoes = lista
        } catch (e: Exception) {
            Log.e("SolicitacoesRecebidas", "Erro ao carregar as solicitações recebidas", e)
            falhou = true
        }
        carregando = false
    }
    SolicitacoesRecebidasConteudo(
        carregando, falhou, solicitacoes, perfis, titulos,
        aoRecusar = { id -> navController.navigate("recusar/$id") },
        modifier = modifier
    )
}

// Ordem dos grupos: as pendentes primeiro (RF20)
private val grupos = listOf(
    "Pendente" to "Pendentes",
    "Aceita" to "Aceitas",
    "Recusada" to "Recusadas",
    "Removida" to "Removidas",
)

@Composable
private fun SolicitacoesRecebidasConteudo(
    carregando: Boolean,
    falhou: Boolean,
    solicitacoes: List<Solicitacao>,
    perfis: Map<String, Perfil>,
    titulos: Map<String, String>,
    aoRecusar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Solicitações recebidas",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.semantics { heading() }
            )
        }
        if (carregando) {
            item {
                Text(
                    text = "Carregando as solicitações...",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else if (falhou) {
            item {
                Text(
                    text = "Não foi possível carregar as solicitações. Confira a conexão com a internet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        } else if (solicitacoes.isEmpty()) {
            item {
                Text(
                    text = "Você ainda não recebeu solicitações.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            for ((status, tituloGrupo) in grupos) {
                val doGrupo = solicitacoes.filter { it.status == status }
                if (doGrupo.isNotEmpty()) {
                    item(key = status) {
                        Text(
                            text = tituloGrupo,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .semantics { heading() }
                        )
                    }
                    items(doGrupo, key = { it.id }) { solicitacao ->
                        ItemSolicitacao(
                            solicitacao = solicitacao,
                            perfil = perfis[solicitacao.solicitante],
                            tituloProjeto = titulos[solicitacao.projeto],
                            aoRecusar = { aoRecusar(solicitacao.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemSolicitacao(
    solicitacao: Solicitacao,
    perfil: Perfil?,
    tituloProjeto: String?,
    aoRecusar: () -> Unit
) {
    // Cores do selo pelo status, do tema
    val fundo = when (solicitacao.status) {
        "Pendente" -> MaterialTheme.colorScheme.secondaryContainer
        "Aceita" -> MaterialTheme.colorScheme.tertiaryContainer
        "Recusada" -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    val cor = when (solicitacao.status) {
        "Pendente" -> MaterialTheme.colorScheme.onSecondaryContainer
        "Aceita" -> MaterialTheme.colorScheme.onTertiaryContainer
        "Recusada" -> MaterialTheme.colorScheme.onErrorContainer
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // O TalkBack lê o status, o projeto e o cartão de uma vez
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .semantics(mergeDescendants = true) {},
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Selo(texto = solicitacao.status, fundo = fundo, cor = cor, comPonto = true)
            Text(
                text = "Projeto: ${tituloProjeto ?: "não encontrado"}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (perfil != null) {
                // O mesmo cartão da prévia do Completar perfil (RN05)
                CartaoPerfil(
                    nome = perfil.nome,
                    curso = perfil.curso,
                    interesses = perfil.interesses
                )
            } else {
                Text(
                    text = "Estudante sem perfil",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        // RF22: só a Pendente pode ser recusada (RN24)
        if (solicitacao.status == "Pendente") {
            val nome = perfil?.nome ?: "estudante sem perfil"
            OutlinedButton(
                onClick = aoRecusar,
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .semantics { contentDescription = "Recusar a solicitação de $nome" }
            ) {
                Text("Recusar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SolicitacoesRecebidasScreenPreview() {
    AgoraHubTheme {
        SolicitacoesRecebidasConteudo(
            carregando = false,
            falhou = false,
            solicitacoes = solicitacoesExemplo,
            perfis = solicitantesExemplo,
            titulos = projetosExemplo.associate { it.id to it.titulo },
            aoRecusar = {}
        )
    }
}