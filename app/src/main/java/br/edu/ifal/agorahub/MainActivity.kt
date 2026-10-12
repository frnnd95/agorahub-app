package br.edu.ifal.agorahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import br.edu.ifal.agorahub.ui.screen.MuralScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.ui.screen.PerfilScreen
import br.edu.ifal.agorahub.ui.screen.ProjetosScreen
import br.edu.ifal.agorahub.ui.components.BarraNavegacao
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgoraHubTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BarraNavegacao(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "mural",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("mural") { MuralScreen() }
                        composable("projetos") { ProjetosScreen() }
                        composable("perfil") { PerfilScreen() }
                    }
                }
            }
        }
    }
}