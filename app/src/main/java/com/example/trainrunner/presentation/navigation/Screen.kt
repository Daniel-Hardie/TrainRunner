package com.example.trainrunner.presentation.navigation

sealed class Screen(
    val route: String
) {
    object Home : Screen("home")
    object Settings: Screen("settings")
    object Routes: Screen("routes")

}
