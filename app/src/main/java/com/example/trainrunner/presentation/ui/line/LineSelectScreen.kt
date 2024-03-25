package com.example.trainrunner.presentation.ui.line

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
import com.example.trainrunner.presentation.data.room.models.MetlinkRoute
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Chip

@Composable
fun LineSelectScreen(
    columnState: ScalingLazyColumnState,
    selectedTrainLine: (String) -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
){
    val viewModel = viewModel(modelClass = LineSelectViewModel::class.java)
    val metlinkLines = viewModel.state.lineList
    LineSelectScreen(
        metlinkLines = metlinkLines,
        columnState = columnState,
        selectedTrainLine = selectedTrainLine,
        modifier = modifier
    ) {
        navigateUp.invoke()
    }
}

@Composable
fun LineSelectScreen(
    metlinkLines: List<MetlinkRoute>,
    columnState: ScalingLazyColumnState,
    selectedTrainLine: (String) -> Unit,
    modifier: Modifier = Modifier,
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
                    text = "Select line"
                )
            }
            items(metlinkLines) { line ->
                Chip(
                    label = line.metlinkRouteShortName,
                    secondaryLabel = line.metlinkRouteLongName,
                    onClick = {
                        selectedTrainLine(line.metlinkRouteShortName)
                        navigateUp.invoke()
                    }
                )
            }
        }
    }
}