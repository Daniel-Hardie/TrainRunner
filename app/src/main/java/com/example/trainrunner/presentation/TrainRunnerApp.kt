package com.example.trainrunner.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
import com.example.trainrunner.presentation.data.room.sources.route.MetlinkRouteModel
import com.example.trainrunner.presentation.data.room.sources.route.MetlinkRouteModelFactory
import com.example.trainrunner.presentation.data.room.sources.schedule.MetlinkScheduleModel
import com.example.trainrunner.presentation.data.room.sources.schedule.ScheduleModelFactory
import com.example.trainrunner.presentation.data.room.sources.station.StationModel
import com.example.trainrunner.presentation.data.room.sources.station.StationModelFactory
import com.example.trainrunner.presentation.navigation.STATION_SELECT_NAV_ARGUMENT
import com.example.trainrunner.presentation.navigation.Screen
import com.example.trainrunner.presentation.theme.TrainRunnerTheme
import com.example.trainrunner.presentation.theme.initialThemeValues
import com.example.trainrunner.presentation.ui.HomeScreen
import com.example.trainrunner.presentation.ui.daysTracked.Day
import com.example.trainrunner.presentation.ui.daysTracked.DaysTrackedScreen
import com.example.trainrunner.presentation.ui.line.LineSelectScreen
import com.example.trainrunner.presentation.ui.route.AddRouteScreen
import com.example.trainrunner.presentation.ui.route.EditRouteScreen
import com.example.trainrunner.presentation.ui.routeList.RouteListScreen
import com.example.trainrunner.presentation.ui.settings.SettingsScreen
import com.example.trainrunner.presentation.ui.station.StationSelectScreen
import com.example.trainrunner.presentation.ui.timeSelect.TimeSelectScreen
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberColumnState

@Composable
fun TrainRunnerApp (
    modifier: Modifier = Modifier,
    swipeDismissableNavController: NavHostController = rememberSwipeDismissableNavController()
){
    PopulateDatabase()
    var themeColors by remember { mutableStateOf(initialThemeValues.colors) }

    // Information to pass between screens
    var routeId by remember { mutableIntStateOf(-1) }
    var selectedMetlinkRouteId by remember  { mutableStateOf("") }
    var selectedTrainLine by remember  { mutableStateOf("Select a line") }
    var selectedStationOneCode by remember { mutableStateOf("Select first station") }
    var selectedStationTwoCode by remember { mutableStateOf("Select second station") }
    val selectedDays = remember { mutableStateListOf<Day>() }
    var selectedScheduleTime by remember { mutableStateOf(MetlinkSchedule(departTime = "Please select time", toWellington = false))}

    TrainRunnerTheme(colors = themeColors) {
        SwipeDismissableNavHost(
            navController = swipeDismissableNavController,
            startDestination = Screen.Home.route
        ){
            // This is probably a hack, but seem to only be able to get data from tables at init time in a ViewModel
            // Make a "page" to do this and then reset these two values back to default
//            composable(
//                route = Screen.Hack.route
//            ){
//                UpdateSystemNotificationTable(
//                    selectedScheduleTime = selectedScheduleTime,
//                    selectedScheduleTimeOnChange = {selectedScheduleTime = it},
//                    uniqueRouteIdValue = uniqueRouteIdValue,
//                    uniqueRouteIdValueOnChange = {uniqueRouteIdValue = it}
//                )
//            }

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
                    RouteListScreen(
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
                        routeId = routeId,
                        selectedMetlinkRouteId = selectedMetlinkRouteId,
                        selectedTrainLine = selectedTrainLine,
                        selectedStationOneCode = selectedStationOneCode,
                        selectedStationTwoCode = selectedStationTwoCode,
                        selectedStationOneCodeGlobalChange = {selectedStationOneCode = it},
                        selectedStationTwoCodeGlobalChange = {selectedStationTwoCode = it},
                        selectedDays = selectedDays,
                        selectedTrainLineOnChange = { selectedTrainLine = it},
                        selectedDaysOnChange = {selectedDays.clear(); selectedDays.addAll(it)},
                        selectedScheduleTime = selectedScheduleTime,
                        selectedScheduleTimeOnChange = {selectedScheduleTime = it},
                        columnState = columnState,
                        onNavigate = { swipeDismissableNavController.navigate(it) },
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

            // StationOneSelect screen
            composable(
                route = Screen.StationSelect.route + "/{$STATION_SELECT_NAV_ARGUMENT}",
                arguments = listOf(
                    navArgument(STATION_SELECT_NAV_ARGUMENT) {
                        type = NavType.IntType
                    }
                )
            ){
                val stationSelectDropdown: Int = it.arguments!!.getInt(STATION_SELECT_NAV_ARGUMENT)
                val columnState = rememberColumnState()

                ScreenScaffold(scrollState = columnState) {
                    StationSelectScreen(
                        columnState = columnState,
                        stopSelect = stationSelectDropdown,
                        lineSelected = selectedTrainLine,
                        selectedStationOneCode = {selectedStationOneCode = it},
                        selectedStationTwoCode = {selectedStationTwoCode = it}
                    ){
                        swipeDismissableNavController.navigateUp()
                    }
                }
            }

            // LineSelect screen
            composable(
                route = Screen.LineSelect.route
            ){
                val columnState = rememberColumnState()
                ScreenScaffold(scrollState = columnState) {
                    LineSelectScreen(
                        columnState = columnState,
                        selectedMetlinkRouteId = {selectedMetlinkRouteId = it},
                        selectedTrainLine = { selectedTrainLine = it}
                    ) {
                        swipeDismissableNavController.navigateUp()
                    }
                }
            }

            // DaysTracked screen
            composable(
                route = Screen.DaysTracked.route
            ){
                val columnState = rememberColumnState()
                ScreenScaffold(scrollState = columnState) {
                    DaysTrackedScreen(
                        columnState = columnState,
                        selectedDays = selectedDays,
                        selectedDaysOnChange = {selectedDays.clear(); selectedDays.addAll(it)}
                    ) {
                        swipeDismissableNavController.navigateUp()
                    }
                }
            }

            // TimeSelect screen
            composable(
                route = Screen.TimeSelect.route
            ){
                val columnState = rememberColumnState()
                ScreenScaffold(scrollState = columnState) {
                    TimeSelectScreen(
                        columnState = columnState,
                        selectedScheduleTimeOnChange = {selectedScheduleTime = it},
                        selectedStationOneCode = selectedStationOneCode,
                        selectedStationTwoCode = selectedStationTwoCode
                    ) {
                        swipeDismissableNavController.navigateUp()
                    }
                }
            }
        }
    }
}

@Composable
fun PopulateDatabase(){
    viewModel<StationModel>(factory = StationModelFactory())
    viewModel<MetlinkRouteModel>(factory = MetlinkRouteModelFactory())
    viewModel<MetlinkScheduleModel>(factory = ScheduleModelFactory())
}

@Composable
internal fun menuNameAndCallback(
    onNavigate: (String) -> Unit,
    menuNameResource: Int,
    screen: Screen
) = MenuItem(stringResource(menuNameResource)) { onNavigate(screen.route) }

data class MenuItem(val name: String, val clickHandler: () -> Unit)