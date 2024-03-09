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


        setContent {
            navController = rememberSwipeDismissableNavController()

            TrainRunnerApp(
                swipeDismissableNavController = navController
            )
        }

//        setContent {
//            TrainRunnerTheme {
//                val navController = rememberSwipeDismissableNavController()
//
//                // NavHost contains all of the screens of the app
//                NavHost(
//                    navController = navController,
//                    startDestination = Route.homeScreen
//                ){
//                    composable(route = Route.homeScreen){
//                        HomeScreen(
//                            greetingName = "Player 1",
//                            navigateToSettingsScreen = {
//                                navController.navigate(Route.settingsScreen)
//                            }
//                        )
//                    }
//                    composable(route = Route.settingsScreen){
//                        SettingsScreen(
//                            navigateToEditScreen = {
//                                navController.navigate(Route.editScreen)
//                            },
//                            navigateBack = {
//                                navController.popBackStack()
//                            }
//                        )
//                    }
//                    composable(route = Route.editScreen){
//                        EditScreen(
//                            navigateToHomeScreen = {
//                                navController.popBackStack(
//                                    route = Route.homeScreen,
//                                    // if set to true, would remove home screen from backstack and would be empty screen
//                                    inclusive = false
//                                )
//                            },
//                            navigateBack = {
//                                navController.popBackStack()
//                            }
//                        )
//                    }
//                }
//
//            }
//        }
    }
}