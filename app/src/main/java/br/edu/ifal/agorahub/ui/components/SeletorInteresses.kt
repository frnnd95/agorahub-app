package br.edu.ifal.agorahub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.data.InteresseDados

    @Composable
    fun SeletorInteresses(
        escolhidos: List<String>,
        aoMudar: (List<String>) -> Unit,
        modifier: Modifier = Modifier
    ) {
        val categorias = remember {
            InteresseDados().listarCategorias()
        }

        var avisoEm by remember { mutableStateOf("") }

        Column(modifier = modifier) {

            Text(
                text = "Escolha de 3 a 5. Escolhidos: ${escolhidos.size}.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            categorias.forEach { categoria ->

                Text(
                    text = categoria.nome,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 4.dp)
                        .semantics { heading() }
                )

                categoria.interesses.forEach { interesse ->

                    val marcado = interesse in escolhidos

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .toggleable(
                                value = marcado,
                                role = Role.Checkbox,
                                onValueChange = { marcar ->

                                    if (!marcar) {
                                        avisoEm = ""
                                        aoMudar(escolhidos - interesse)

                                    } else if (escolhidos.size < 5) {
                                        avisoEm = ""
                                        aoMudar(
                                            (escolhidos + interesse).distinct()
                                        )

                                    } else {
                                        avisoEm = interesse
                                    }
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = marcado,
                            onCheckedChange = null
                        )

                        Text(
                            text = interesse,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    if (avisoEm == interesse) {
                        Text(
                            text = "Você já escolheu 5 interesses, o máximo. Desmarque um para escolher outro.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.semantics {
                                liveRegion = LiveRegionMode.Polite
                            }
                        )
                    }
                }
            }
        }
    }


