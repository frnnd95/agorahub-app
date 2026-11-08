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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage

@Composable
fun EquipeScreen(modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var falhou by remember { mutableStateOf(false) }
    var integrantes by remember { mutableStateOf(listOf<Integrante>()) }

    // Pede a lista uma vez, quando a tela abre
    LaunchedEffect(Unit) {
        try {
            integrantes = EquipeDados().listarIntegrantes()
        } catch (e: Exception) {
            Log.e("CarregamentoEquipe", "Erro ao carregar a equipe", e)
            falhou = true
        }
        carregando = false
    }
    EquipeConteudo(carregando, falhou, integrantes, modifier)
}

@Composable
private fun EquipeConteudo(
    carregando: Boolean,
    falhou: Boolean,
    integrantes: List<Integrante>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Equipe ÁgoraHub",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        if (carregando) {
            item {
                Text(
                    text = "Carregando a equipe...",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else if (falhou) {
            item {
                Text(
                    text = "Não foi possível carregar a equipe. Confira a conexão com a internet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else if (integrantes.isEmpty()) {
            item {
                Text(
                    text = "Nenhum integrante cadastrado.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        items(integrantes) { integrante ->
            ItemIntegrante(integrante)
        }
    }
}

@Composable
private fun ItemIntegrante(integrante: Integrante) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .semantics(mergeDescendants = true) {},
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (integrante.foto != null) {
                // Foto decorativa: o nome ao lado já identifica a pessoa
                AsyncImage(
                    model = integrante.foto,
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = integrante.nome,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = integrante.papel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EquipeScreenPreview() {
    AgoraHubTheme {
        EquipeConteudo(
            carregando = false,
            falhou = false,
            // Dados de teste
            integrantes = listOf(
                Integrante(id = "1", nome = "Maria Clara Santos", foto = null, papel = "Dev"),
                Integrante(id = "2", nome = "João Pedro Lima", foto = null, papel = "Testes de acessibilidade"),
            )
        )
    }
}