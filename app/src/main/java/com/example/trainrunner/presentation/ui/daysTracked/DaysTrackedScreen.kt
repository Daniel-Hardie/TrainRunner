package com.example.trainrunner.presentation.ui.daysTracked

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
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
    selectedDays: MutableList<Day>,
    selectedDaysOnChange: (List<Day>) -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
){
    if(selectedDays.isNullOrEmpty()){
        selectedDays.addAll(PopulateDayList())
    }

    DaysTrackedList(
        columnState = columnState,
        modifier = modifier,
        selectedDays = selectedDays,
        selectedDaysOnChange = selectedDaysOnChange
    ){
        navigateUp.invoke()
    }
}

@Composable
fun DaysTrackedList(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    selectedDays: MutableList<Day>,
    selectedDaysOnChange: (List<Day>) -> Unit,
    navigateUp: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            columnState = columnState,
            modifier = modifier.fillMaxSize()
        ) {

            for(day in selectedDays){
                item{
                    ToggleChip(
                        checked = day.isActive,
                        onCheckedChanged = {
                            val returnList = mutableStateListOf<Day>()
                            for (dayTest in selectedDays){
                                val isActive: Boolean = if(dayTest.orderId == day.orderId){
                                    !dayTest.isActive
                                } else{
                                    dayTest.isActive
                                }
                                returnList.add(
                                    Day(
                                        orderId = dayTest.orderId,
                                        text = dayTest.text,
                                        shortText = dayTest.shortText,
                                        isActive = isActive
                                    )
                                )
                            }

                            selectedDaysOnChange(returnList)
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

@Composable
fun PopulateDayList(): MutableList<Day> {
    var returnList: MutableList<Day> = mutableListOf()
    val dayText = listOf<String>(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday"
    )

    val dayShortText = listOf<String>(
        "M",
        "Tu",
        "W",
        "T",
        "F"
    )

    for (i in 0..4){
        returnList.add(
            Day(
                orderId = i,
                text = dayText[i],
                shortText = dayShortText[i],
                isActive = false
            )
        )
    }

    return returnList
}