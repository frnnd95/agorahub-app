package br.edu.ifal.agorahub.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun ProjetosScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Hub de Projetos",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ProjetosScreenPreview() {
    AgoraHubTheme {
        ProjetosScreen()
    }
}