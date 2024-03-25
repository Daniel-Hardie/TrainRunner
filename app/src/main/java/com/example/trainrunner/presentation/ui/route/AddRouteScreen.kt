package com.example.trainrunner.presentation.ui.route

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.trainrunner.R
import com.example.trainrunner.presentation.navigation.Screen
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import com.google.android.horologist.compose.material.Chip
import kotlin.reflect.KFunction1

@Composable
fun AddRouteScreen(
    routeId: Int,
    selectedTrainLine: String,
    selectedStationOneCode: String,
    selectedStationTwoCode: String,
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    navigateUp: () -> Unit,
) {
    val viewModel = viewModel<RouteViewModel>(factory = RouteViewModelFactory(routeId))

    RouteScreen(
        routeId = routeId,
        selectedTrainLine = selectedTrainLine,
        selectedStationOneCode = selectedStationOneCode,
        selectedStationTwoCode = selectedStationTwoCode,
        state = viewModel.state,
        columnState = columnState,
        modifier = modifier,
        onNavigate = onNavigate,
        saveRoute = viewModel::addRoute,
        deleteRoute = viewModel::deleteRoute
    ){
        navigateUp.invoke()
    }
}

@Composable
fun RouteScreen(
    routeId: Int,
    selectedTrainLine: String,
    selectedStationOneCode: String,
    selectedStationTwoCode: String,
    state: RouteState,
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    saveRoute: () -> Unit,
    deleteRoute: KFunction1<Int, Unit>,
    navigateUp: () -> Unit
){
    val trainLine = selectedTrainLine
    val stationOneCode = selectedStationOneCode
    val stationTwoCode = selectedStationTwoCode
    val daysTracked = state.daysTrackedCount
    val timeTracked = state.timeTracked
    val isActive: String = if(state.isActive){
        "Enabled"
    }
    else{
        "Disabled"
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            columnState = columnState,
            modifier = modifier.fillMaxSize()
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    text = "Add Route"
                )
            }
            item{
                Chip(
                    label = "Train Line",
                    secondaryLabel = trainLine,
                    onClick = {onNavigate(Screen.LineSelect.route)}
                )
            }
            item{
                Chip(
                    label = "Station One",
                    secondaryLabel = stationOneCode,
                    onClick = {onNavigate(Screen.StationOneSelect.route)}
                )
            }
            item{
                Chip(
                    label = "Station Two",
                    secondaryLabel = stationTwoCode,
                    onClick = {onNavigate(Screen.StationTwoSelect.route)}
                )
            }
            item{
                Chip(
                    label = "Days Tracked",
                    secondaryLabel = "$daysTracked selected",
                    onClick = { }
                )
            }
            item{
                Chip(
                    label = "Time",
                    secondaryLabel = timeTracked.time.toString(),
                    onClick = { }
                )
            }
            item{
                Chip(
                    label = "Notifications",
                    secondaryLabel = isActive,
                    onClick = { }
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = modifier.padding(8.dp),
                        id = R.drawable.ic_done,
                        contentDescription = "Save",
                        onClick = {
                            saveRoute.invoke()
                            navigateUp.invoke()
                        },
                        buttonSize = ButtonSize.Small,
                    )
                    Button(
                        modifier = modifier.padding(8.dp),
                        id = R.drawable.ic_delete,
                        contentDescription = "Delete",
                        onClick = {
                            deleteRoute.invoke(routeId)
                            navigateUp.invoke()
                        },
                        buttonSize = ButtonSize.Small,
                    )
                }
            }
        }
    }
}