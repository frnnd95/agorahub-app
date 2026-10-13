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

@Composable
fun CardOportunidade(oportunidade: Oportunidade) {
    val prazo = oportunidade.prazo.format(DateTimeFormatter.ofPattern("dd/MM"))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
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
                text = "Prazo: $prazo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardOportunidadePreview() {
    AgoraHubTheme {
        CardOportunidade(oportunidadesExemplo[0])
    }
}