package br.edu.ifal.agorahub.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MuralScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Mural de Oportunidades",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MuralScreenPreview() {
    MuralScreen()
}