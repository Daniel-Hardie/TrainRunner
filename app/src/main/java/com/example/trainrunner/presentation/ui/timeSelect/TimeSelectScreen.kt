package com.example.trainrunner.presentation.ui.timeSelect

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
import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Chip

@Composable
fun TimeSelectScreen(
    columnState: ScalingLazyColumnState,
    selectedScheduleTime: (MetlinkSchedule) -> Unit,
    selectedStationOneCode: String,
    selectedStationTwoCode: String,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
){
    val viewModel = viewModel<TimeSelectViewModel>(factory = TimeSelectViewModelFactory(selectedStationOneCode, selectedStationTwoCode))
    val list = viewModel.state.availableTimes

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
                    text = "Select time"
                )
            }
            items(list) { line ->
                Chip(
                    label = line.departTime,
                    onClick = {
                        selectedScheduleTime(line)
                        navigateUp.invoke()
                    }
                )
            }
        }
    }
}