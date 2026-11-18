package br.edu.ifal.agorahub.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Interesse procurado; em verde quando também está no perfil de quem está logado
@Composable
fun ChipInteresse(interesse: String, emComum: Boolean) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = if (emComum) {
            MaterialTheme.colorScheme.tertiaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }
    ) {
        Text(
            text = interesse,
            style = MaterialTheme.typography.labelLarge,
            color = if (emComum) {
                MaterialTheme.colorScheme.onTertiaryContainer
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}