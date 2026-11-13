package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import br.edu.ifal.agorahub.domain.Pergunta


@Composable
fun PerguntasScreen(modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var perguntas by remember { mutableStateOf<List<Pergunta>?>(null) }
    LaunchedEffect(Unit) {
        try {
            perguntas = PerguntaDados().listarPerguntas()
        } catch (e: Exception) {
            Log.e("Perguntas", "Erro ao carregar perguntas", e)
        }
        carregando = false
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
        val lista = perguntas
        if (carregando) {
            Text(text = "Carregando perguntas...", style = MaterialTheme.typography.bodyLarge)
        } else if (lista == null) {
            Text(
                text = "Não foi possível carregar as perguntas. Confira a conexão com a internet.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        } else if (lista.isEmpty()) {
            Text(text = "Ainda não há perguntas frequentes.", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(lista) { item ->
                    // Pergunta antes da resposta: o TalkBack lê nesta ordem
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = item.pergunta, style = MaterialTheme.typography.titleMedium)
                        Text(text = item.resposta, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerguntasScreenPreview() {
    AgoraHubTheme {
        PerguntasScreen()
    }
}