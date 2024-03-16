package com.example.trainrunner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.trainrunner.data.StationRepository

class MainActivity: ComponentActivity() {
    internal lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)


        setContent {
            navController = rememberSwipeDismissableNavController()

            TrainRunnerApp(
                swipeDismissableNavController = navController
            )
        }
    }

    override val defaultViewModelCreationExtras: CreationExtras
        get() = MutableCreationExtras(super.defaultViewModelCreationExtras).apply {
            set(
                StationRepository.INITIAL_STATION_REPOSITORY_KEY,
                (application as BaseApplication).stationRepository
            )
        }
}