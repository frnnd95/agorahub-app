package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.data.ProjetoDados
import br.edu.ifal.agorahub.data.SolicitacaoDados
import br.edu.ifal.agorahub.data.projetosExemplo
import br.edu.ifal.agorahub.data.solicitacoesExemplo
import br.edu.ifal.agorahub.domain.Solicitacao
import br.edu.ifal.agorahub.ui.components.Selo
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun MinhasSolicitacoesScreen(modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var falhou by remember { mutableStateOf(false) }
    var solicitacoes by remember { mutableStateOf(listOf<Solicitacao>()) }
    var titulos by remember { mutableStateOf(mapOf<String, String>()) }

    // Pede a lista uma vez; depois, o título de cada projeto
    LaunchedEffect(Unit) {
        try {
            val lista = SolicitacaoDados().listarMinhas()
            val titulosEncontrados = mutableMapOf<String, String>()
            for (id in lista.map { it.projeto }.distinct()) {
                val projeto = ProjetoDados().buscarPorId(id)
                if (projeto != null) {
                    titulosEncontrados[id] = projeto.titulo
                }
            }
            titulos = titulosEncontrados
            solicitacoes = lista
        } catch (e: Exception) {
            Log.e("MinhasSolicitacoes", "Erro ao carregar as minhas solicitações", e)
            falhou = true
        }
        carregando = false
    }
    MinhasSolicitacoesConteudo(carregando, falhou, solicitacoes, titulos, modifier)
}

@Composable
private fun MinhasSolicitacoesConteudo(
    carregando: Boolean,
    falhou: Boolean,
    solicitacoes: List<Solicitacao>,
    titulos: Map<String, String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Minhas solicitações",
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
                    text = "Você ainda não enviou solicitações.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            items(solicitacoes, key = { it.id }) { solicitacao ->
                ItemMinhaSolicitacao(solicitacao, titulos[solicitacao.projeto])
            }
        }
    }
}

@Composable
private fun ItemMinhaSolicitacao(solicitacao: Solicitacao, tituloProjeto: String?) {
    // Cores do selo pelo status, as mesmas das Solicitações recebidas
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
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {},
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Selo(texto = solicitacao.status, fundo = fundo, cor = cor, comPonto = true)
            Text(
                text = tituloProjeto ?: "Projeto não encontrado",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            // RN27: o motivo só na Recusada
            val motivo = solicitacao.motivo
            if (solicitacao.status == "Recusada" && motivo != null) {
                Text(
                    text = "Motivo: $motivo",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (solicitacao.status == "Aceita") {
                Text(
                    text = "O responsável recebeu seu e-mail institucional e deve entrar em contato.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MinhasSolicitacoesScreenPreview() {
    AgoraHubTheme {
        MinhasSolicitacoesConteudo(
            carregando = false,
            falhou = false,
            solicitacoes = solicitacoesExemplo,
            titulos = projetosExemplo.associate { it.id to it.titulo }
        )
    }
}