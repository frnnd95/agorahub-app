package br.edu.ifal.agorahub.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import br.edu.ifal.agorahub.data.OportunidadeDados
import br.edu.ifal.agorahub.ui.components.CardOportunidade
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.edu.ifal.agorahub.domain.Oportunidade

@Composable
fun MuralScreen(navController: NavController, modifier: Modifier = Modifier)  {
    var carregando by remember { mutableStateOf(true) }
    var oportunidades by remember { mutableStateOf(listOf<Oportunidade>()) }

    // Pede a lista uma vez, quando a tela abre
    LaunchedEffect(Unit) {
        OportunidadeDados().listarMural { lista ->
            oportunidades = lista
            carregando = false
        }
    }

    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Mural de Oportunidades",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        if (carregando) {
            item {
                Text(
                    text = "Carregando oportunidades...",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else if (oportunidades.isEmpty()) {
            item {
                Text(
                    text = "Nenhuma oportunidade aberta no momento.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        items(oportunidades) { oportunidade ->
            CardOportunidade(oportunidade, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MuralScreenPreview() {
    AgoraHubTheme() {
        MuralScreen(rememberNavController())
    }
}