package br.edu.ifal.agorahub.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import br.edu.ifal.agorahub.data.OportunidadeDados
import br.edu.ifal.agorahub.ui.components.CardOportunidade
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp

@Composable
fun MuralScreen(modifier: Modifier = Modifier) {
    val oportunidades = OportunidadeDados().listarMural()

    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Mural de Oportunidades",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(oportunidades) { oportunidade ->
            CardOportunidade(oportunidade)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MuralScreenPreview() {
    AgoraHubTheme() {
        MuralScreen()
    }
}