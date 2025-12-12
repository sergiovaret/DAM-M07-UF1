package es.lasalle.m07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.lasalle.m07.ui.theme.MyApplicationTheme
import es.lasalle.m07.ui.navigation.MainNavigationApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainNavigationApp()
            }
        }
    }
}