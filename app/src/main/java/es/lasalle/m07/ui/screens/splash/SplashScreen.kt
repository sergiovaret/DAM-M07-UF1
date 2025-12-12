package es.lasalle.m07.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import es.lasalle.m07.R
import es.lasalle.m07.ui.navigation.Routes

@Composable
fun SplashScreen(onNavigate: (String) -> Unit) {

    LaunchedEffect(true) {
        delay(2000)
        onNavigate(Routes.Login.route)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.spaceapp_logo),
                contentDescription = "Logo SpaceApps",
                modifier = Modifier.size(512.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(text = "SpaceApps", fontSize = 32.sp)
        } // fin-column
    } // fin-box
} // fin-fun