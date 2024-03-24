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
import com.example.trainrunner.R
import com.example.trainrunner.presentation.data.room.models.Station
import com.example.trainrunner.presentation.menuNameAndCallback
import com.example.trainrunner.presentation.navigation.Screen
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Chip


// https://developer.android.com/training/wearables/compose/lists

@Composable
fun StationSelectScreen(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
) {
//    val viewModel: InitialStationListViewModel = viewModel(
//        factory = InitialStationListViewModel.Factory
//    )
//    val stations by viewModel.initialStations

    val viewModel = viewModel(modelClass = StationSelectViewModel::class.java)
    val stationState = viewModel.state

    val stations = stationState.stations

//    val stations = listOf<String>("blah", "blah2")
    StationSelectScreen(
        columnState = columnState,
        modifier = modifier,
        onNavigate = onNavigate,
        stations = stations
    )
}


@Composable
fun StationSelectScreen(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    stations: List<Station>
){
    val addRouteInfo = menuNameAndCallback(
        onNavigate = onNavigate,
        menuNameResource = R.string.add_route_button_label,
        screen = Screen.AddRoute
    )

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
                    onClick = addRouteInfo.clickHandler
                )
            }
        }
    }
}