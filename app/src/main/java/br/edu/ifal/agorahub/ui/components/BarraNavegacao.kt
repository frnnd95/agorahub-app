package br.edu.ifal.agorahub.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BarraNavegacao(navController: NavController) {
    // Rota da tela aberta agora ("mural", "projetos" ou "perfil")
    val entradaAtual by navController.currentBackStackEntryAsState()
    val rotaAtual = entradaAtual?.destination?.route
    // Abre a aba sem empilhar telas repetidas
    fun irPara(rota: String) {
        navController.navigate(rota) {
            popUpTo("mural")
            launchSingleTop = true
        }
    }
    val cores = NavigationBarItemDefaults.colors(
        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
        selectedIconColor = MaterialTheme.colorScheme.onSurface,
        selectedTextColor = MaterialTheme.colorScheme.onSurface,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
    NavigationBar {
        NavigationBarItem(
            selected = rotaAtual == "mural" || rotaAtual?.startsWith("detalhes") == true,
            onClick = { irPara("mural") },
            icon = { Icon(Icons.Default.Campaign, contentDescription = null) },
            label = { Text("Mural") },
            colors = cores
        )
        NavigationBarItem(
            selected = rotaAtual == "projetos",
            onClick = { irPara("projetos") },
            icon = { Icon(Icons.Default.Folder, contentDescription = null) },
            label = { Text("Projetos") },
            colors = cores
        )
        NavigationBarItem(
            selected = rotaAtual == "perfil",
            onClick = { irPara("perfil") },
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Perfil") },
            colors = cores
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BarraNavegacaoPreview() {
    AgoraHubTheme {
        BarraNavegacao(rememberNavController())
    }
}