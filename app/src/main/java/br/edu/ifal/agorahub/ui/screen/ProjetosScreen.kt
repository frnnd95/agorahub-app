package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun ProjetosScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Hub de Projetos",
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProjetosScreenPreview() {
    AgoraHubTheme {
        ProjetosScreen()
    }
}