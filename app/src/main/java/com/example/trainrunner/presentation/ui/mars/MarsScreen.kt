package com.example.trainrunner.presentation.ui.mars

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.ToggleChip
import com.google.android.horologist.compose.material.ToggleChipToggleControl
import java.util.Random

@Composable
fun MarsScreen(
    columnState: ScalingLazyColumnState,
//    marsDays: MutableList<Mars>, // Change to MutableList
    marsObjectDays: MutableList<MarsObject>,
//    marsDaysOnChange: (List<Mars>) -> Unit,
    marsObjectDaysOnChange: (List<MarsObject>) -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
){
    if(marsObjectDays.isNullOrEmpty()){
        marsObjectDays.add(
            MarsObject(
                name = "sadf",
                isOn = false
            )
        )
    }

    ScalingLazyColumn(
        columnState = columnState,
        modifier = modifier.fillMaxSize()
    ) {
        item{
            // Example usage of selectedDays
            // You can replace it with your UI code
//            Text("Selected days: ${marsDays.joinToString(", ")}")
        }
        item{
            // Example usage of selectedDays
            // You can replace it with your UI code
            Text("Selected days: ${marsObjectDays.joinToString(", ")}")
        }
//        item{
//            Chip(
//                label = "Click me",
//                onClick = {
//                    val updatedSelectedObjectDays = mutableStateListOf<MarsObject>()
//                    for(i in 0..1){
//                        updatedSelectedObjectDays.add(
//                            MarsObject(
//                                Mars.entries.toList().shuffled().first().toString(),
//                                Random().nextBoolean()
//                            )
//                        )
//                    }
//                    marsObjectDaysOnChange(updatedSelectedObjectDays)
//                }
//            )
//        }
        item{
            ToggleChip(
                checked = marsObjectDays[0].isOn,
                onCheckedChanged = {
                    val updatedSelectedObjectDays = mutableStateListOf<MarsObject>()
                    for(i in 0..1){
                        updatedSelectedObjectDays.add(
                            MarsObject(
                                Mars.entries.toList().shuffled().first().toString(),
                                Random().nextBoolean()
                            )
                        )
                    }
                    marsObjectDaysOnChange(updatedSelectedObjectDays)
                },
                label = marsObjectDays[0].name,
                toggleControl = ToggleChipToggleControl.Switch
            )
        }
    }
}

enum class Mars {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

data class MarsObject (
    var name: String = "",
    var isOn: Boolean = false
)