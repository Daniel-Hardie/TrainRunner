package com.example.trainrunner.presentation.ui.station

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.trainrunner.presentation.data.room.models.Station
import com.example.trainrunner.presentation.navigation.Screen
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Chip


// https://developer.android.com/training/wearables/compose/lists

@Composable
fun StationSelectScreen(
    columnState: ScalingLazyColumnState,
    stopSelect: Int,
    lineSelected: String,
    selectedStationOneCode: (String) -> Unit,
    selectedStationTwoCode: (String) -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
) {
    val viewModel = viewModel<StationSelectViewModel>(factory = StationSelectViewModelFactory(lineSelected))
    val stations = viewModel.state.stations

    StationSelectScreen(
        columnState = columnState,
        stopSelect = stopSelect,
        selectedStationOneCode = selectedStationOneCode,
        selectedStationTwoCode = selectedStationTwoCode,
        modifier = modifier,
        stations = stations,
        navigateUp = navigateUp
    )
}


@Composable
fun StationSelectScreen(
    columnState: ScalingLazyColumnState,
    stopSelect: Int,
    selectedStationOneCode: (String) -> Unit,
    selectedStationTwoCode: (String) -> Unit,
    modifier: Modifier = Modifier,
    stations: List<Station>,
    navigateUp: () -> Unit,
){
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
                    text = "Initial Station"
                )
            }
            items(stations) { station ->
                Chip(
                    label = station.metlinkStopId,
                    onClick = {
                        Screen.AddRoute.route
                    }
                )
                Chip(
                    label = station.metlinkStopId,
                    secondaryLabel = station.metlinkStopCode,
                    onClick = {
                        if(stopSelect == 1){
                            selectedStationOneCode(station.metlinkStopCode)
                        }
                        else{
                            selectedStationTwoCode(station.metlinkStopCode)
                        }
                        navigateUp.invoke()
                    }
                )
            }
        }
    }
}