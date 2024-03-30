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
//    selectedDays: ListOfDays,
    selectedDays: MutableList<Day>,
//    selectedDaysOnChange: (ListOfDays) -> Unit,
    selectedDaysOnChange: (List<Day>) -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
){
//    val viewModel = viewModel(modelClass = DaysTrackedViewModel::class.java)
//    val viewModel = viewModel<DaysTrackedViewModel>(factory = DaysTrackedViewModelFactory(selectedDays))
    val viewModel = viewModel<DaysTrackedViewModel>(factory = DaysTrackedViewModelFactory(selectedDays))

//    if(!selectedDays.isEmpty()){
//        viewModel.populateDaysTracked(selectedDays)
//    }
//    val listOfPossibleDays = viewModel.state.listOfDays

    DaysTrackedList(
        state = viewModel.state,
        columnState = columnState,
        modifier = modifier,
//        listOfDays = listOfPossibleDays,
//        selectedDays = selectedDays,
        selectedDaysOnChange = selectedDaysOnChange,
        onDayChanged = viewModel::onDayChanged
    ){
        navigateUp.invoke()
    }
}

@Composable
fun DaysTrackedList(
    state: MutableList<Day>,
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
//    listOfDays: List<Day>,
//    selectedDays: ListOfDays,
//    selectedDays: MutableList<Day>,
//    selectedDaysOnChange: (ListOfDays) -> Unit,
    selectedDaysOnChange: (List<Day>) -> Unit,
    onDayChanged: (Day) -> Unit,
    navigateUp: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            columnState = columnState,
            modifier = modifier.fillMaxSize()
        ) {

            // using an outdated state when this shit happens
            for(day in state){
                item{
                    ToggleChip(
                        checked = day.isActive,
                        onCheckedChanged = {
                            onDayChanged(day)
//                            selectedDays[day.orderId] = selectedDays[day.orderId].copy(isActive = !day.isActive)

                            val returnList = mutableListOf<Day>()
                            for (dayTest in state){
                                returnList.add(
                                    Day(
                                        orderId = dayTest.orderId,
                                        text = dayTest.text,
                                        shortText = dayTest.shortText,
                                        isActive = dayTest.isActive
                                    )
                                )
                            }

                            selectedDaysOnChange(returnList)
                            // I dont think this is working because state is the same list/object?
//                            selectedDaysOnChange(state)
                        },
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
                        navigateUp.invoke()
                    },
                    buttonSize = ButtonSize.Small,
                )
            }

        }
    }
}