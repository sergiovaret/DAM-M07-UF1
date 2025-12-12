package es.lasalle.m07.ui.screens.rockets_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import es.lasalle.m07.data.local.RocketEntity
import es.lasalle.m07.viewmodel.RocketsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketsListScreen(
    viewModel: RocketsViewModel,
    onRocketClick: (String) -> Unit,
    onLogout: () -> Unit
){
    LaunchedEffect(Unit) {
        //viewModel.loadRockets()
        viewModel.refreshRockets()
    }

    val rockets by viewModel.rockets.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de cohetes") }
            )
        }
    )  { padding ->

        var showOnlyActive by remember { mutableStateOf(false) }
        var searchText by remember { mutableStateOf("") }
        val filteredRockets = rockets.filter { rocket ->
            // 2. Se compara explícitamente con 'true' para manejar el caso nulo
            (!showOnlyActive || rocket.active == true) &&
            (searchText.isEmpty() || rocket.name.contains(searchText, ignoreCase = true))
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Buscar por nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text("Mostrar solo activos")

                Spacer(modifier = Modifier.width(8.dp))

                Switch(
                    checked = showOnlyActive,
                    onCheckedChange = { showOnlyActive = it }
                )
            }

            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {

                when {
                    loading ->
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    error != null ->
                        Column(
                            Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(error!!)

                            Spacer(Modifier.height(8.dp))

                            // 3. Se llama a la función correcta en el botón de reintentar
                            Button(onClick = { viewModel.refreshRockets() }) { 
                                Text("Reintentar")
                            }
                        }
                    filteredRockets.isEmpty() && !loading ->
                        Text("No se encontraron cohetes", Modifier.align(Alignment.Center))
                    else ->
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(filteredRockets) { rocket ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable { onRocketClick(rocket.id) }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        // 4. Acceso seguro a la lista de imágenes, que ahora puede ser nula
                                        val imageUrl = rocket.flickr_images?.firstOrNull()
                                            ?: "https://via.placeholder.com/64"

                                        AsyncImage(
                                            model = imageUrl,
                                            contentDescription = rocket.name,
                                            modifier = Modifier.size(64.dp)
                                        )

                                        Spacer(modifier = Modifier.width(16.dp))

                                        Text(rocket.name)
                                    }
                                }
                            }
                    }
                }

                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ){
                    Text("Cerrar sesión")
                }

            }
        }
    }
}