package com.example.trainrunner.presentation.ui.daysTracked

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class DaysTrackedViewModel(): ViewModel(){
    var state by mutableStateOf(ListOfDays())
        private set

    init {
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
            "Th",
            "F"
        )

        val listOfDaysPopulation: MutableList<Day> = mutableListOf()

        for (i in 0..4){
            listOfDaysPopulation.add(
                Day(
                    orderId = i+1,
                    text = dayText[i],
                    shortText = dayShortText[i],
                    isActive = false
                )
            )
        }

        state = state.copy(
            listOfDays = listOfDaysPopulation
        )
    }
}

data class ListOfDays(
    val listOfDays: List<Day> = emptyList()
)
data class Day(
    val orderId: Int = -1,
    val text: String = "",
    val shortText: String = "",
    val isActive: Boolean = false
)