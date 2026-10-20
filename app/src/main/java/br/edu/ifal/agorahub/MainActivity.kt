package br.edu.ifal.agorahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.agorahub.data.OportunidadeDados
import br.edu.ifal.agorahub.ui.components.BarraNavegacao
import br.edu.ifal.agorahub.ui.screen.DetalhesOportunidadeScreen
import br.edu.ifal.agorahub.ui.screen.MuralScreen
import br.edu.ifal.agorahub.ui.screen.PerfilScreen
import br.edu.ifal.agorahub.ui.screen.ProjetosScreen
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import br.edu.ifal.agorahub.ui.screen.AberturaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgoraHubTheme {
                val navController = rememberNavController()
                val entradaAtual by navController.currentBackStackEntryAsState()
                val rotaAtual = entradaAtual?.destination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (rotaAtual != "abertura") {
                            BarraNavegacao(navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "abertura",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("abertura") { AberturaScreen(navController) }
                        composable("mural") { MuralScreen(navController) }
                        composable("projetos") { ProjetosScreen() }
                        composable("perfil") { PerfilScreen() }
                        composable("detalhes/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")
                            val oportunidade = OportunidadeDados().buscarPorId(id)
                            if (oportunidade != null) {
                                DetalhesOportunidadeScreen(oportunidade = oportunidade)
                            }
                        }
                    }
                }
            }
        }
    }
}