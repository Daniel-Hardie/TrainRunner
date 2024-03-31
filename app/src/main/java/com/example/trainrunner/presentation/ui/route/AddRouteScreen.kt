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

@Composable
fun AddRouteScreen(
    routeId: Int,
    selectedTrainLine: String,
    selectedMetlinkRouteId: String,
    selectedStationOneCode: String,
    selectedStationTwoCode: String,
//    selectedDays: ListOfDays,
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    navigateUp: () -> Unit
) {
    val viewModel = viewModel<RouteViewModel>(factory = RouteViewModelFactory(routeId))

    RouteScreen(
        routeId = routeId,
        selectedMetlinkRouteId = selectedMetlinkRouteId,
        selectedTrainLine = selectedTrainLine,
        selectedStationOneCode = selectedStationOneCode,
        selectedStationTwoCode = selectedStationTwoCode,
//        selectedDays = selectedDays,
        state = viewModel.state,
        columnState = columnState,
        modifier = modifier,
        onNavigate = onNavigate,
        saveRoute = viewModel::addRoute,
        deleteRoute = { viewModel.deleteRoute(routeId) },
        onLineChanged = viewModel::onTrainLineChanged,
        onStationOneCodeChanged = viewModel::onStationOneCodeChanged,
        onStationTwoCodeChanged = viewModel::onStationTwoCodeChanged
    ){
        navigateUp.invoke()
    }
}

@Composable
fun RouteScreen(
    routeId: Int,
    selectedMetlinkRouteId: String,
    selectedTrainLine: String,
    selectedStationOneCode: String,
    selectedStationTwoCode: String,
//    selectedDays: ListOfDays,
    state: RouteState,
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    saveRoute: () -> Unit,
    deleteRoute: (Int) -> Unit,
    onLineChanged: (String, String) -> Unit,
    onStationOneCodeChanged: (String) -> Unit,
    onStationTwoCodeChanged: (String) -> Unit,
    navigateUp: () -> Unit
){
    val daysTracked = state.daysTrackedCount
    val timeTracked = state.timeTracked
    val isActive: String = if(state.isActive){
        "Enabled"
    }
    else{
        "Disabled"
    }

    val isStationOneChipActive: Boolean
    val isStationTwoChipActive: Boolean
    onLineChanged(selectedTrainLine, selectedMetlinkRouteId)

    if(selectedTrainLine != "Select a line"){
        onStationOneCodeChanged(selectedStationOneCode)
        isStationOneChipActive = true
    }
    else{
        isStationOneChipActive = false
    }

    if((selectedTrainLine != "Select a line") && (selectedStationOneCode != "Select entry station")){
        onStationTwoCodeChanged(selectedStationTwoCode)
        isStationTwoChipActive = true
    }
    else{
        isStationTwoChipActive = false
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
                    secondaryLabel = selectedTrainLine,
                    onClick = {onNavigate(Screen.LineSelect.route)}
                )
            }
            item{
                Chip(
                    label = "Station One",
                    secondaryLabel = selectedStationOneCode,
                    enabled = isStationOneChipActive,
                    onClick = { onNavigate(Screen.StationSelect.route + "/1") }
                )
            }
            item{
                Chip(
                    label = "Station Two",
                    secondaryLabel = selectedStationTwoCode,
                    enabled = isStationTwoChipActive,
                    onClick = { onNavigate(Screen.StationSelect.route + "/2") }
                )
            }
            item{
                Chip(
                    label = "Days Tracked",
                    secondaryLabel = "$daysTracked selected",
                    onClick = {onNavigate(Screen.DaysTracked.route)}
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