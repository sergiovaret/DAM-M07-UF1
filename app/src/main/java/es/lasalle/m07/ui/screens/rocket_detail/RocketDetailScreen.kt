package es.lasalle.m07.ui.screens.rocket_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import es.lasalle.m07.viewmodel.RocketsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDetailScreen(
    rocketId: String,
    viewModel: RocketsViewModel,
    onBack: () -> Unit
) {
    val rockets by viewModel.rockets.collectAsState()
    val rocket by remember(rockets, rocketId) {
        derivedStateOf {
            rockets.find { it.id == rocketId }
        }
    }
    val context = LocalContext.current
    val currentRocket = rocket

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentRocket?.name ?: "Detalle") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->

        if (currentRocket == null) {
            Box(
                Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) { Text("Error: cohete no encontrado") }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val imageUrl = currentRocket.flickr_images?.firstOrNull() ?: "https://via.placeholder.com/400"
            AsyncImage(
                model = imageUrl,
                contentDescription = currentRocket.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(currentRocket.name, style = MaterialTheme.typography.headlineSmall)

            Spacer(Modifier.height(16.dp))

            Text("País: " + currentRocket.country ?: "No disponible")
            Text("Primer lanzamiento: " + currentRocket.first_flight ?: "No disponible")
            Text("Éxito: %" + currentRocket.success_rate_pct ?: "No disponible")
            Text("Costo por lanzamiento: $" + currentRocket.cost_per_launch ?: "No disponible")
            Text("Etapas: " + currentRocket.stages ?: "No disponible")
            Text("Descripción:", style = MaterialTheme.typography.titleMedium)
            Text(currentRocket.description ?: "No hay descripción disponible.")

            Spacer(Modifier.height(16.dp))

            if (currentRocket.wikipedia != null) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentRocket.wikipedia))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Más info (Wikipedia)")
                }
            }

        } // fin-column
    } // fin-scaffold
} // fin-fun