package br.edu.ifal.agorahub.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import br.edu.ifal.agorahub.data.OportunidadeDados
import br.edu.ifal.agorahub.ui.components.CardOportunidade

@Composable
fun MuralScreen(modifier: Modifier = Modifier) {
    val oportunidades = OportunidadeDados().listarMural()

    LazyColumn(modifier = modifier) {
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