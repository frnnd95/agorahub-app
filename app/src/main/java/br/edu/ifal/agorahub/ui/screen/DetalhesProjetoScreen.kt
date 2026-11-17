package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.data.ContaDados
import br.edu.ifal.agorahub.data.PerfilDados
import br.edu.ifal.agorahub.data.ProjetoDados
import br.edu.ifal.agorahub.data.projetosExemplo
import br.edu.ifal.agorahub.domain.Projeto
import br.edu.ifal.agorahub.ui.components.ChipInteresse
import br.edu.ifal.agorahub.ui.components.Selo
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy")

@Composable
fun DetalhesProjetoScreen(id: String?, modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var falhou by remember { mutableStateOf(false) }
    var projeto by remember { mutableStateOf<Projeto?>(null) }
    var nomeResponsavel by remember { mutableStateOf<String?>(null) }
    var seuProjeto by remember { mutableStateOf(false) }
    var meusInteresses by remember { mutableStateOf(listOf<String>()) }

    // Lê o projeto e, depois, o nome do responsável em estudantes (seção 9.3)
    LaunchedEffect(Unit) {
        try {
            val encontrado = ProjetoDados().buscarPorId(id)
            projeto = encontrado
            if (encontrado != null) {
                nomeResponsavel = PerfilDados().buscarPerfilDe(encontrado.responsavel)?.nome
                // O projeto é de quem está logado? (RN17)
                seuProjeto = encontrado.responsavel == ContaDados().uidAtual()
                meusInteresses = PerfilDados().buscarPerfil()?.interesses ?: listOf()
            }
        } catch (e: Exception) {
            falhou = true
            Log.e("DetalhesProjeto", "Erro ao carregar o projeto", e)
        }
        carregando = false
    }
    DetalhesProjetoConteudo(carregando, falhou, projeto, nomeResponsavel, seuProjeto, meusInteresses, modifier)
}

@Composable
private fun DetalhesProjetoConteudo(
    carregando: Boolean,
    falhou: Boolean,
    projeto: Projeto?,
    nomeResponsavel: String?,
    seuProjeto: Boolean,
    meusInteresses: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (carregando) {
            Text(text = "Carregando projeto...", style = MaterialTheme.typography.bodyLarge)
        } else if (falhou) {
            Text(
                text = "Não foi possível carregar o projeto. Confira a conexão com a internet.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        } else if (projeto == null) {
            Text(text = "Projeto não encontrado.", style = MaterialTheme.typography.bodyLarge)
        } else {
            val textoVagas = if (projeto.vagasDisponiveis == 1) {
                "1 vaga disponível"
            } else {
                "${projeto.vagasDisponiveis} vagas disponíveis"
            }
            val dataCriacao = projeto.criadoEm.toDate().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate().format(formatoData)

            // Cores do selo pelo status: Aberto verde, Fechado vermelho, Encerrado cinza
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

            // Selos: o status e, se o projeto for de quem está logado, "o Seu projeto"
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Selo(texto = projeto.status, fundo = fundoStatus, cor = corStatus, comPonto = true)
                if (seuProjeto) {
                    Selo(
                        texto = "Seu projeto",
                        fundo = MaterialTheme.colorScheme.primaryContainer,
                        cor = MaterialTheme.colorScheme.onPrimaryContainer,
                        comPonto = false
                    )
                }
            }

            // Título, responsável e data
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = projeto.titulo,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Criado por ${nomeResponsavel ?: "nome não disponível"} · $dataCriacao",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(text = projeto.descricao, style = MaterialTheme.typography.bodyLarge)

            // Cartão das vagas (RN18)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Vagas",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = textoVagas,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Interesses procurados
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "INTERESSES PROCURADOS",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    projeto.interesses.forEach { interesse ->
                        ChipInteresse(interesse = interesse, emComum = interesse in meusInteresses)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalhesProjetoScreenPreview() {
    AgoraHubTheme {
        DetalhesProjetoConteudo(
            carregando = false,
            falhou = false,
            projeto = projetosExemplo[0],
            nomeResponsavel = "Marina Coutinho",
            seuProjeto = true,
            meusInteresses = listOf("Mobile", "UX/UI")
        )
    }
}