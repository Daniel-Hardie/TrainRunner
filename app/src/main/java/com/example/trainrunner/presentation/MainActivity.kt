package com.example.trainrunner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController

class MainActivity: ComponentActivity() {
    internal lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)

        // Sets up the database
//        Graph.provide(this)

        setContent {
            navController = rememberSwipeDismissableNavController()

            TrainRunnerApp(
                swipeDismissableNavController = navController
            )
        }
    }

//    override val defaultViewModelCreationExtras: CreationExtras
//        get() = MutableCreationExtras(super.defaultViewModelCreationExtras).apply {
//            set(
//                StationRepository.INITIAL_STATION_REPOSITORY_KEY,
//                (application as BaseApplication).stationRepository
//            )
//        }
}