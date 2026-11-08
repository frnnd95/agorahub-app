package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.edu.ifal.agorahub.data.EquipeDados
import br.edu.ifal.agorahub.domain.Integrante

@Composable
fun EquipeScreen(modifier: Modifier = Modifier) {
    var integrantes by remember { mutableStateOf(listOf<Integrante>()) }

    LaunchedEffect(Unit) {
        try {
            integrantes = EquipeDados().listarIntegrantes()
        } catch (e: Exception) {
            Log.e("CarregamentoEquipe", "Erro ao carregar a equipe", e)
        }
    }

    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Equipe ÁgoraHub",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        // Provisório: só para conferir a leitura
        items(integrantes) { integrante ->
            Text(
                text = "${integrante.nome}: ${integrante.papel} (foto: ${integrante.foto})",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EquipeScreenPreview() {
    AgoraHubTheme {
        EquipeScreen()
    }
}