package br.edu.ifal.agorahub.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.data.projetosExemplo
import br.edu.ifal.agorahub.domain.Projeto
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun CardProjeto(
    projeto: Projeto,
    nomeResponsavel: String?,
    seuProjeto: Boolean,
    meusInteresses: List<String>,
    navController: NavController
) {
    val textoVagas = if (projeto.vagasDisponiveis == 1) {
        "1 vaga disponível"
    } else {
        "${projeto.vagasDisponiveis} vagas disponíveis"
    }
    // "Criado por Nome · " normal e as vagas em negrito, na mesma linha
    val linhaCriador = buildAnnotatedString {
        append("Criado por ${nomeResponsavel ?: "nome não disponível"} · ")
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append(textoVagas)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            // Tocar no cartão abre os detalhes (RF16); o TalkBack anuncia como botão
            .clickable(role = Role.Button, onClickLabel = "Ver detalhes") {
                navController.navigate("projeto/${projeto.id}")
            },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Título e, se o projeto for de quem está logado, o selo
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = projeto.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f, fill = false)
                )
                if (seuProjeto) {
                    Selo(
                        texto = "Seu projeto",
                        fundo = MaterialTheme.colorScheme.primaryContainer,
                        cor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Text(
                text = linhaCriador,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            // Interesses procurados, quebrando a linha quando não cabem
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                projeto.interesses.forEach { interesse ->
                    ChipInteresse(interesse = interesse, emComum = interesse in meusInteresses)
                }
            }
            // RF16: os interesses em comum também em texto, não só pela cor (RNF02)
            val emComum = projeto.interesses.filter { it in meusInteresses }
            if (emComum.isNotEmpty()) {
                Text(
                    text = "Em comum com você: ${emComum.joinToString(", ")}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardProjetoPreview() {
    AgoraHubTheme {
        CardProjeto(
            projetosExemplo[0],
            "Marina Coutinho",
            true,
            listOf("Mobile", "UX/UI"),
            rememberNavController()
        )
    }
}