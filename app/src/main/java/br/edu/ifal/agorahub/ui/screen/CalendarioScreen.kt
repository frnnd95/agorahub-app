package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.data.CalendarioDados
import br.edu.ifal.agorahub.domain.EventoCalendario
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun CalendarioScreen(modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var eventos by remember { mutableStateOf<List<EventoCalendario>?>(null) }
    LaunchedEffect(Unit) {
        try {
            eventos = CalendarioDados().listarEventos()
        } catch (e: Exception) {
            Log.e("CarregamentoCalendario", "Erro ao carregar o calendário", e)
        }
        carregando = false
    }
    CalendarioConteudo(carregando, eventos, modifier)
}

@Composable
private fun CalendarioConteudo(
    carregando: Boolean,
    eventos: List<EventoCalendario>?,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Calendário acadêmico",
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            Text(
                text = "Semestre 2026.2 · IFAL Campus Arapiraca",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (carregando) {
            item {
                Text(
                    text = "Carregando o calendário...",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else if (eventos == null) {
            item {
                Text(
                    text = "Não foi possível carregar o calendário. Confira a conexão com a internet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        } else if (eventos.isEmpty()) {
            item {
                Text(
                    text = "Nenhuma data cadastrada no calendário.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            items(eventos) { evento ->
                ItemEvento(evento)
            }
        }
    }
}

@Composable
private fun ItemEvento(evento: EventoCalendario) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = evento.data,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = evento.evento,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarioScreenPreview() {
    AgoraHubTheme {
        CalendarioConteudo(
            carregando = false,
            eventos = listOf(
                EventoCalendario("1", "20/07/2026", "Início do semestre letivo 2026.2"),
                EventoCalendario("2", "09/09/2026", "Semana de Sistemas de Informação (até 11/09)")
            )
        )
    }
}