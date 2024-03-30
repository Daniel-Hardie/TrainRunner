package com.example.trainrunner.presentation.ui.daysTracked

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trainrunner.R
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import com.google.android.horologist.compose.material.ToggleChip
import com.google.android.horologist.compose.material.ToggleChipToggleControl

@Composable
fun DaysTrackedScreen(
    columnState: ScalingLazyColumnState,
//    selectedDaysToTrack: (String) -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
){
    val viewModel = viewModel(modelClass = DaysTrackedViewModel::class.java)
    val listOfPossibleDays = viewModel.state.listOfDays

    DaysTrackedList(
        columnState = columnState,
        modifier = modifier,
        listOfDays = listOfPossibleDays
    ){
        navigateUp.invoke()
    }
}

@Composable
fun DaysTrackedList(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    listOfDays: List<Day>,
    navigateUp: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            columnState = columnState,
            modifier = modifier.fillMaxSize()
        ) {
            for(day in listOfDays){
                item{
                    ToggleChip(
                        checked = day.isActive,
                        onCheckedChanged = {},
                        label = day.text,
                        toggleControl = ToggleChipToggleControl.Switch
                    )
                }

            }
            item {
                Button(
                    id = R.drawable.ic_done,
                    contentDescription = "Save",
                    onClick = {
//                        saveRoute.invoke()
                        navigateUp.invoke()
                    },
                    buttonSize = ButtonSize.Small,
                )
            }

        }
    }
}