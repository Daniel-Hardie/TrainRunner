package com.example.trainrunner.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.trainrunner.presentation.data.room.sources.route.MetlinkRouteModel
import com.example.trainrunner.presentation.data.room.sources.route.MetlinkRouteModelFactory
import com.example.trainrunner.presentation.data.room.sources.station.StationModel
import com.example.trainrunner.presentation.data.room.sources.station.StationModelFactory
import com.example.trainrunner.presentation.navigation.Screen
import com.example.trainrunner.presentation.theme.TrainRunnerTheme
import com.example.trainrunner.presentation.theme.initialThemeValues
import com.example.trainrunner.presentation.ui.EditRouteScreen
import com.example.trainrunner.presentation.ui.HomeScreen
import com.example.trainrunner.presentation.ui.RoutesScreen
import com.example.trainrunner.presentation.ui.SettingsScreen
import com.example.trainrunner.presentation.ui.route.AddRouteScreen
import com.example.trainrunner.presentation.ui.station.StationSelectScreen
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberColumnState

@Composable
fun TrainRunnerApp (
    modifier: Modifier = Modifier,
    swipeDismissableNavController: NavHostController = rememberSwipeDismissableNavController()
){
    PopulateDatabase()
    var themeColors by remember { mutableStateOf(initialThemeValues.colors) }
    var routeId: Int = -1

    TrainRunnerTheme(colors = themeColors) {
        SwipeDismissableNavHost(
            navController = swipeDismissableNavController,
            startDestination = Screen.Home.route
        ){
            // Home screen
            composable(
                route = Screen.Home.route
            ){
                // Used for scroll state
                val columnState = rememberColumnState()

                ScreenScaffold(scrollState = columnState) {
                    HomeScreen(
                        columnState = columnState,
//                        onClickWatchList = {
//                            swipeDismissableNavController.navigate(Screen.WatchList.route)
//                        },
//                        proceedingTimeTextEnabled = showProceedingTextBeforeTime,
//                        onClickProceedingTimeText = {
//                            showProceedingTextBeforeTime = !showProceedingTextBeforeTime
//                        },
                        onNavigate = { swipeDismissableNavController.navigate(it) }
                    )
                }
            }

            // Settings screen
            composable(
                route = Screen.Settings.route
            ){
                val columnState = rememberColumnState()
                ScreenScaffold(scrollState = columnState) {
                    SettingsScreen(
                        columnState = columnState,
                        onNavigate = { swipeDismissableNavController.navigate(it) },
                    )
                }
            }

            // Routes screen
            composable(
                route = Screen.Routes.route
            ){
                val columnState = rememberColumnState()
                ScreenScaffold(scrollState = columnState) {
                    RoutesScreen(
                        columnState = columnState,
                        onNavigate = { swipeDismissableNavController.navigate(it) }
                    )
                }
            }

            // AddRoute screen
            composable(
                route = Screen.AddRoute.route
            ){
                val columnState = rememberColumnState()
                ScreenScaffold(scrollState = columnState) {
                    AddRouteScreen(
                        routeId = 1,
                        columnState = columnState,
                        onNavigate = { swipeDismissableNavController.navigate(it) },
                        onClickDestinationList = {
                            swipeDismissableNavController.navigate(Screen.StationSelect.route)
                        }
                    ){
                        swipeDismissableNavController.navigateUp()
                    }
                }
            }

            // EditRoute screen
            composable(
                route = Screen.EditRoute.route
            ){
                val columnState = rememberColumnState()
                ScreenScaffold(scrollState = columnState) {
                    EditRouteScreen(
                        columnState = columnState,
                        onNavigate = { swipeDismissableNavController.navigate(it) }
                    )
                }
            }

            // DestinationList screen
            composable(
                route = Screen.StationSelect.route
            ){
                val columnState = rememberColumnState()
                ScreenScaffold(scrollState = columnState) {
                    StationSelectScreen(
                        columnState = columnState,
                        onNavigate = {
                            swipeDismissableNavController.navigate(it)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun PopulateDatabase(){
    viewModel<StationModel>(factory = StationModelFactory())
    viewModel<MetlinkRouteModel>(factory = MetlinkRouteModelFactory())
}

@Composable
internal fun menuNameAndCallback(
    onNavigate: (String) -> Unit,
    menuNameResource: Int,
    screen: Screen
) = MenuItem(stringResource(menuNameResource)) { onNavigate(screen.route) }

data class MenuItem(val name: String, val clickHandler: () -> Unit)