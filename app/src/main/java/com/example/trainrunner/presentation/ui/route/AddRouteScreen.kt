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
import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
import com.example.trainrunner.presentation.navigation.Screen
import com.example.trainrunner.presentation.ui.daysTracked.Day
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import com.google.android.horologist.compose.material.Chip
import kotlin.random.Random

@Composable
fun AddRouteScreen(
    routeId: Int,
    selectedTrainLine: String,
    selectedMetlinkRouteId: String,
    selectedStationOneCode: String,
    selectedStationTwoCode: String,
    selectedStationOneCodeGlobalChange: (String) -> Unit,
    selectedStationTwoCodeGlobalChange: (String) -> Unit,
    selectedDays: List<Day>,
    selectedDaysOnChange: (List<Day>) -> Unit,
    selectedScheduleTime: MetlinkSchedule,
    selectedScheduleTimeOnChange: (MetlinkSchedule) -> Unit,
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
        selectedStationOneCodeGlobalChange = selectedStationOneCodeGlobalChange,
        selectedStationTwoCodeGlobalChange = selectedStationTwoCodeGlobalChange,
        selectedDays = selectedDays,
        selectedDaysOnChange = selectedDaysOnChange,
        selectedScheduleTime = selectedScheduleTime,
        selectedScheduleTimeOnChange = selectedScheduleTimeOnChange,
        state = viewModel.state,
        columnState = columnState,
        modifier = modifier,
        onNavigate = onNavigate,
        saveRoute = viewModel::saveRoute,
        saveRouteNotifications = viewModel::saveRouteNotifications,
        deleteRoute = { viewModel.deleteRoute(routeId) },
        onLineChanged = viewModel::onTrainLineChanged,
        onStationOneCodeChanged = viewModel::onStationOneCodeChanged,
        onStationTwoCodeChanged = viewModel::onStationTwoCodeChanged,
        onSelectedDaysChanged = viewModel::onSelectedDaysChanged,
        onDaysTrackedCountChanged = viewModel::onDaysTrackedCountChanged
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
    selectedStationOneCodeGlobalChange: (String) -> Unit,
    selectedStationTwoCodeGlobalChange: (String) -> Unit,
    selectedDays: List<Day>,
    selectedDaysOnChange: (List<Day>) -> Unit,
    selectedScheduleTime: MetlinkSchedule,
    selectedScheduleTimeOnChange: (MetlinkSchedule) -> Unit,
    state: RouteState,
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    saveRoute: (Int) -> Unit,
    saveRouteNotifications: (Int, String, List<Day>) -> Unit,
    deleteRoute: (Int) -> Unit,
    onLineChanged: (String, String) -> Unit,
    onStationOneCodeChanged: (String) -> Unit,
    onStationTwoCodeChanged: (String) -> Unit,
    onSelectedDaysChanged: (List<Day>) -> Unit,
    onDaysTrackedCountChanged: (Int) -> Unit,
    navigateUp: () -> Unit
) {
    val isActive: String = if (state.isActive) {
        "Enabled"
    } else {
        "Disabled"
    }

    // Populate the state based on what has been passed in
    val isStationOneChipActive: Boolean
    val isStationTwoChipActive: Boolean
    onLineChanged(selectedTrainLine, selectedMetlinkRouteId)

    if (selectedTrainLine != "Select a line") {
        onStationOneCodeChanged(selectedStationOneCode)
        isStationOneChipActive = true
    } else {
        isStationOneChipActive = false
    }

    if ((selectedTrainLine != "Select a line") && (selectedStationOneCode != "Select first station")) {
        onStationTwoCodeChanged(selectedStationTwoCode)
        isStationTwoChipActive = true
    } else {
        isStationTwoChipActive = false
    }

    if (!selectedDays.isNullOrEmpty()) {
        onSelectedDaysChanged(selectedDays)
    }

    val isDaysTrackedActive = selectedTrainLine != "Select a line" &&
            selectedStationOneCode != "Select first station" &&
            selectedStationTwoCode != "Select second station"

    val isTimeButtonActive = isDaysTrackedActive
            && state.daysTrackedCount > 0

    val addRouteButtonEnabled = isStationOneChipActive &&
            isStationTwoChipActive &&
            selectedDays.isNotEmpty() &&
            state.daysTrackedCount > 0 &&
            selectedScheduleTime.departTime != "Please select time"

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
            item {
                Chip(
                    label = "Train Line",
                    secondaryLabel = selectedTrainLine,
                    onClick = {
                        selectedStationOneCodeGlobalChange("Select first station")
                        selectedStationTwoCodeGlobalChange("Select second station")
                        selectedDaysOnChange(emptyList<Day>())
                        onDaysTrackedCountChanged(0)  // Makes the AddRoute page display = 0
                        selectedScheduleTimeOnChange(MetlinkSchedule(departTime = "Please select time", toWellington = false))
                        onNavigate(Screen.LineSelect.route)
                    }
                )
            }
            item {
                Chip(
                    label = "Station One",
                    secondaryLabel = selectedStationOneCode,
                    enabled = isStationOneChipActive,
                    onClick = {
                        selectedStationTwoCodeGlobalChange("Select second station")
                        selectedDaysOnChange(emptyList<Day>())
                        onDaysTrackedCountChanged(0)  // Makes the AddRoute page display = 0
                        selectedScheduleTimeOnChange(MetlinkSchedule(departTime = "Please select time", toWellington = false))
                        onNavigate(Screen.StationSelect.route + "/1")
                    }
                )
            }
            item {
                Chip(
                    label = "Station Two",
                    secondaryLabel = selectedStationTwoCode,
                    enabled = isStationTwoChipActive,
                    onClick = {
                        selectedDaysOnChange(emptyList<Day>())
                        onDaysTrackedCountChanged(0)  // Makes the AddRoute page display = 0
                        selectedScheduleTimeOnChange(MetlinkSchedule(departTime = "Please select time", toWellington = false))
                        onNavigate(Screen.StationSelect.route + "/2")
                    }
                )
            }
            item {
                Chip(
                    label = "Days Tracked",
                    secondaryLabel = "${state.daysTrackedCount} selected",
                    enabled = isDaysTrackedActive,
                    onClick = {
                        onDaysTrackedCountChanged(0)  // Makes the AddRoute page display = 0
                        selectedScheduleTimeOnChange(MetlinkSchedule(departTime = "Please select time", toWellington = false))
                        onNavigate(Screen.DaysTracked.route)
                    }
                )
            }
            item {
                Chip(
                    label = "Time",
                    secondaryLabel = selectedScheduleTime.departTime,
                    enabled = isTimeButtonActive,
                    onClick = {
                        onNavigate(Screen.TimeSelect.route)
                    }
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
                            val uniqueRouteId = Random.nextInt(0, 9999999)
                            saveRoute(uniqueRouteId)
                            println(selectedScheduleTime)
                            for(d in selectedDays){
                                println(d)
                            }
                            saveRouteNotifications.invoke(uniqueRouteId, selectedScheduleTime.departTime, selectedDays)
                            navigateUp.invoke()
                        },
                        buttonSize = ButtonSize.Small,
                        enabled = addRouteButtonEnabled
                    )
                    Button(
                        modifier = modifier.padding(8.dp),
                        id = R.drawable.ic_cancel,
                        contentDescription = "Cancel",
                        onClick = {
                            deleteRoute.invoke(routeId)
                            navigateUp.invoke()
                        },
                        buttonSize = ButtonSize.Small
                    )
                }
            }
        }
    }
}