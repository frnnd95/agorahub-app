package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.data.ContaDados
import br.edu.ifal.agorahub.data.PerfilDados
import br.edu.ifal.agorahub.data.perfilExemplo
import br.edu.ifal.agorahub.domain.Perfil
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme

@Composable
fun PerfilScreen(navController: NavController, modifier: Modifier = Modifier) {
    var carregando by remember { mutableStateOf(true) }
    var perfil by remember { mutableStateOf<Perfil?>(null) }
    LaunchedEffect(Unit) {
        try {
            perfil = PerfilDados().buscarPerfil()
        } catch (e: Exception) {
            Log.e("CarregamentoPerfil", "Erro ao carregar perfil", e)
        }
        carregando = false
    }
    PerfilConteudo(carregando, perfil, navController, modifier)
}

@Composable
private fun PerfilConteudo(
    carregando: Boolean,
    perfil: Perfil?,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Perfil",
            style = MaterialTheme.typography.titleLarge
        )
        if (carregando) {
            Text(
                text = "Carregando perfil...",
                style = MaterialTheme.typography.bodyLarge
            )
        } else if (perfil == null) {
            Text(
                text = "Não foi possível carregar o perfil. Confira a conexão com a internet.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        } else {
            CartaoPerfil(perfil)
        }
        // Cartão do estudante: editar o perfil e o calendário
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            Column {
                ItemOpcao(
                    icone = Icons.Default.Edit,
                    texto = "Editar perfil",
                    onClick = { navController.navigate("editar-perfil") }
                )
                ItemOpcao(
                    icone = Icons.Default.CalendarMonth,
                    texto = "Calendário acadêmico",
                    onClick = { navController.navigate("calendario") }
                )
            }
        }
        // Cartão das outras opções
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            Column {
                ItemOpcao(
                    icone = Icons.Default.QuestionAnswer,
                    texto = "Perguntas frequentes",
                    onClick = { navController.navigate("perguntas") }
                )
                ItemOpcao(
                    icone = Icons.Default.Shield,
                    texto = "Privacidade e dados",
                    onClick = { navController.navigate("privacidade") }
                )
                ItemOpcao(
                    icone = Icons.AutoMirrored.Filled.Logout,
                    texto = "Sair",
                    onClick = {
                        ContaDados().sair()
                        navController.navigate("abertura") {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun CartaoPerfil(perfil: Perfil) {
    val partes = perfil.nome.split(" ").filter { it.isNotEmpty() }
    val iniciais = if (partes.size > 1) {
        partes.first().take(1) + partes.last().take(1)
    } else if (partes.size == 1) {
        partes.first().take(1)
    } else {
        ""
    }
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
                        .clearAndSetSemantics { },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = iniciais.uppercase(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = perfil.nome,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = perfil.curso,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                perfil.interesses.forEach { interesse ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = interesse,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemOpcao(
    icone: ImageVector,
    texto: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .heightIn(min = 48.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icone,
            contentDescription = null
        )
        Text(
            text = texto,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    AgoraHubTheme {
        PerfilConteudo(
            carregando = false,
            perfil = perfilExemplo,
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartaoPerfilPreview() {
    AgoraHubTheme {
        CartaoPerfil(perfilExemplo)
    }
}
