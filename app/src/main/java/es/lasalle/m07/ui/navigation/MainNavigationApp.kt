package es.lasalle.m07.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.lasalle.m07.MyApplication
import es.lasalle.m07.ui.screens.login.LoginScreen
import es.lasalle.m07.ui.screens.rocket_detail.RocketDetailScreen
import es.lasalle.m07.ui.screens.rockets_list.RocketsListScreen
import es.lasalle.m07.ui.screens.splash.SplashScreen
import es.lasalle.m07.viewmodel.RocketsViewModel
import es.lasalle.m07.viewmodel.ViewModelFactory

@Composable
fun MainNavigationApp() {

    val navController = rememberNavController()

    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    val factory = ViewModelFactory(app.repository)

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {

        composable(Routes.Splash.route) {
            SplashScreen(
                onNavigate = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.Login.route) {
            LoginScreen(
                onNavigate = {
                    navController.navigate(Routes.RocketsList.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.RocketsList.route) {
            val rocketsViewModel: RocketsViewModel = viewModel(factory = factory)

            RocketsListScreen(
                viewModel = rocketsViewModel,
                onRocketClick = { id -> navController.navigate(Routes.RocketDetail.route + "/$id") },
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.RocketDetail.route + "/{rocketId}") { backStackEntry ->
            val rocketId = backStackEntry.arguments?.getString("rocketId") ?: ""
            val rocketsViewModel: RocketsViewModel = viewModel(factory = factory)

            RocketDetailScreen(
                rocketId = rocketId,
                viewModel = rocketsViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}