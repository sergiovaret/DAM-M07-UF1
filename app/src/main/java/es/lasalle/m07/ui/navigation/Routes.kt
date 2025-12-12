package es.lasalle.m07.ui.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Login : Routes("login")
    object RocketsList : Routes("rockets_list")
    object RocketDetail : Routes("rocket_detail")
}