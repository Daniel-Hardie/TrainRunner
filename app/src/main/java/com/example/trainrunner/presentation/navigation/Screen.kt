package com.example.trainrunner.presentation.navigation

const val STATION_SELECT_NAV_ARGUMENT = "default"

sealed class Screen(
    val route: String
) {
    object Home : Screen("home")
    object Settings: Screen("settings")
    object Routes: Screen("routes")
    object Notifications: Screen("notifications")
    object AddRoute: Screen("add-route")
    object EditRoute: Screen("edit-route")
    object StationSelect: Screen("station-select")
    object LineSelect: Screen("line-select")
    object DaysTracked: Screen("days-tracked")

}
