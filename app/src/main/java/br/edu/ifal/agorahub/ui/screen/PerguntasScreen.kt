package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import br.edu.ifal.agorahub.data.PerguntaDados
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme


@Composable
fun PerguntasScreen(modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        try {
            val perguntas = PerguntaDados().listarPerguntas()
            Log.i("Perguntas", "Recebidas: ${perguntas.size}, primeira: ${perguntas.firstOrNull()?.pergunta}")
        } catch (e: Exception) {
            Log.e("Perguntas", "Erro ao carregar perguntas", e)
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Perguntas frequentes",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PerguntasScreenPreview() {
    AgoraHubTheme {
        PerguntasScreen()
    }
}