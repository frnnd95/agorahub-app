package br.edu.ifal.agorahub.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.ifal.agorahub.data.PerfilDados

@Composable
fun InicioScreen(navController: NavController, modifier: Modifier = Modifier) {
    var tentativa by remember { mutableStateOf(0) }
    var erro by remember { mutableStateOf(false) }

    LaunchedEffect(tentativa) {
        try {
            val destino = if (PerfilDados().perfilCompleto()) "mural" else "completar-perfil"
            navController.navigate(destino) {
                popUpTo("inicio") { inclusive = true }
            }
        } catch (e: Exception) {
            erro = true
            Log.e("CarregamentoPerfil", "Erro ao carregar perfil", e)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (!erro) {
            Text(text = "Carregando…", style = MaterialTheme.typography.bodyLarge)
        } else {
            Text(
                text = "Não foi possível carregar o seu perfil. Confira a internet e tente de novo.",
                        style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
            Button(
                onClick = {
                    erro = false
                    tentativa++
                },
                modifier = Modifier.heightIn(min = 48.dp)
            ) {
                Text("Tentar de novo")
            }
        }
    }
}