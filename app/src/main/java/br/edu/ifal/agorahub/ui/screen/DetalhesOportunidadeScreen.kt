package br.edu.ifal.agorahub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.agorahub.data.oportunidadesExemplo
import br.edu.ifal.agorahub.domain.Oportunidade
import br.edu.ifal.agorahub.ui.theme.AgoraHubTheme
import coil.compose.AsyncImage
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.platform.LocalUriHandler


private val formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy")

@Composable
fun DetalhesOportunidadeScreen(
    oportunidade: Oportunidade,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (oportunidade.imagemUrl != null) {
            AsyncImage(
                model = oportunidade.imagemUrl,
                contentDescription = oportunidade.descricaoImagem,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = oportunidade.titulo,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = oportunidade.tipo,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Prazo: ${oportunidade.prazo.format(formatoData)}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Publicada em ${oportunidade.dataPublicacao.format(formatoData)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = oportunidade.descricao,
                style = MaterialTheme.typography.bodyLarge
            )
            Button(
                onClick = { uriHandler.openUri(oportunidade.link) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.OpenInNew,
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text("Abrir no navegador")
            }

            }

        }
    }

@Preview(showBackground = true)
@Composable
fun DetalhesComImagemPreview() {
    AgoraHubTheme {
        DetalhesOportunidadeScreen(oportunidadesExemplo[1])
    }
}

@Preview(showBackground = true)
@Composable
fun DetalhesSemImagemPreview() {
    AgoraHubTheme {
        DetalhesOportunidadeScreen(oportunidadesExemplo[3])
    }
}
