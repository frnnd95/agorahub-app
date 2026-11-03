package br.edu.ifal.agorahub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.data.InteresseDados

// Seletor de interesses (RN08 e RN09), usado no RF07 e no RF09
@Composable
fun SeletorInteresses(
    escolhidos: List<String>,
    aoMudar: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    val categorias = remember { InteresseDados().listarCategorias() }
    // Categoria onde o estudante tentou marcar o sexto interesse
    var avisoEm by remember { mutableStateOf("") }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Seus interesses",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.semantics { heading() }
        )
        Text(
            text = "Escolha de 3 a 5 interesses. Eles ajudam a mostrar com o que você pode colaborar.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "${escolhidos.size} de 5 selecionados",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
        categorias.forEach { categoria ->
            Text(
                text = categoria.nome,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .semantics { heading() }
            )
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                categoria.interesses.forEach { interesse ->
                    val marcado = interesse in escolhidos
                    FilterChip(
                        selected = marcado,
                        onClick = {
                            if (marcado) {
                                avisoEm = ""
                                aoMudar(escolhidos - interesse)
                            } else if (escolhidos.size < 5) {
                                avisoEm = ""
                                aoMudar((escolhidos + interesse).distinct())
                            } else {
                                avisoEm = categoria.nome
                            }
                        },
                        label = { Text(interesse) },
                        leadingIcon = if (marcado) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        }
                    )
                }
            }
            if (avisoEm == categoria.nome) {
                Text(
                    text = "Você já escolheu 5 interesses, o máximo. Desmarque um para escolher outro.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite }
                )
            }
        }
    }
}
