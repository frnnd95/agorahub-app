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
import br.edu.ifal.agorahub.ui.screen.PrivacidadeScreen
import br.edu.ifal.agorahub.ui.screen.CadastroScreen
import br.edu.ifal.agorahub.ui.screen.EntrarScreen
import br.edu.ifal.agorahub.ui.screen.VerificacaoScreen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.domain.Oportunidade

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
                        if (rotaAtual != "abertura" &&
                            rotaAtual != "privacidade" &&
                            rotaAtual != "cadastro" &&
                            rotaAtual != "entrar" &&
                            rotaAtual != "verificacao"
                        ) {
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
                        composable("privacidade") { PrivacidadeScreen() }
                        composable("cadastro") { CadastroScreen(navController) }
                        composable("entrar") { EntrarScreen() }
                        composable("verificacao") { VerificacaoScreen() }
                        composable("mural") { MuralScreen(navController) }
                        composable("projetos") { ProjetosScreen() }
                        composable("perfil") { PerfilScreen(navController) }
                        composable("detalhes/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")
                            var carregando by remember { mutableStateOf(true) }
                            var oportunidade by remember { mutableStateOf<Oportunidade?>(null) }

                            LaunchedEffect(Unit) {
                                OportunidadeDados().buscarPorId(id) { encontrada ->
                                    oportunidade = encontrada
                                    carregando = false
                                }
                            }

                            val atual = oportunidade
                            if (carregando) {
                                Text(
                                    text = "Carregando oportunidade...",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(16.dp)
                                )
                            } else if (atual != null) {
                                DetalhesOportunidadeScreen(oportunidade = atual)
                            } else {
                                Text(
                                    text = "Oportunidade não encontrada.",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}