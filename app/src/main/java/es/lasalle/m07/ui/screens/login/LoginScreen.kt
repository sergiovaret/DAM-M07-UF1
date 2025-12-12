package es.lasalle.m07.ui.screens.login

import android.content.Intent
import android.net.Uri
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import es.lasalle.m07.ui.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(onNavigate: (String) -> Unit) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var mensajeEmail by rememberSaveable { mutableStateOf("") }
    var mensajePassword by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login")

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true
            )

            if (mensajeEmail.isNotEmpty()) {
                Text(mensajeEmail, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            if (mensajePassword.isNotEmpty()) {
                Text(mensajePassword, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                mensajeEmail = ""
                mensajePassword = ""

                if (!email.contains("@") || !email.contains(".")) {
                    mensajeEmail = "El email no es válido"
                }

                if (password.isEmpty()) {
                    mensajePassword = "La contraseña no puede estar vacía"
                }

                if (mensajeEmail.isNotEmpty() || mensajePassword.isNotEmpty())
                    return@Button

                if (email == "admin@lasalle.es" && password == "admin1234") {
                    onNavigate(Routes.RocketsList.route)
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar("Credenciales incorrectas")
                    }
                }
            }) {
                Text("Iniciar sesión")
            }

            Spacer(Modifier.height(16.dp))

            TextButton(onClick = {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://lasallefp.com/contactar/"))
                context.startActivity(intent)
            }) {
                Text("He olvidado mis datos de acceso")
            }
        } // fin-column
    } // fin-scaffold
} // fin-fun


