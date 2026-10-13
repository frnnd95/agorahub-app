package br.edu.ifal.agorahub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.data.oportunidadesExemplo
import br.edu.ifal.agorahub.domain.Oportunidade
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun CardOportunidade(oportunidade: Oportunidade) {
    val diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), oportunidade.prazo).toInt()

    val textoPrazo = when (diasRestantes) {
        0 -> "Encerra hoje"
        1 -> "Falta 1 dia"
        2, 3 -> "Faltam $diasRestantes dias"
        else -> "Prazo: " + oportunidade.prazo.format(DateTimeFormatter.ofPattern("dd/MM"))
    }

    val corPrazo = if (diasRestantes <= 3) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = oportunidade.tipo,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = oportunidade.titulo,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = textoPrazo,
                style = MaterialTheme.typography.bodyMedium,
                color = corPrazo
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardOportunidadePreview() {
    AgoraHubTheme {
        CardOportunidade(oportunidadesExemplo[1])
    }
}