package com.example.trainrunner.presentation.ui.mars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class DaysTrackedViewModel(
//    private val listofDaysFromApp: ListOfDays
    private val listofDaysFromApp: MutableList<Day>
): ViewModel(){
//    var state by mutableStateOf(ListOfDays())
//        private set

//    var state by mutableStateOf(emptyList<Day>())   //  ListOfDays()
//        private set

    var state = mutableListOf<Day>()   //  ListOfDays()
        private set

    init {
        if (listofDaysFromApp.isEmpty()){
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
                        orderId = i,
                        text = dayText[i],
                        shortText = dayShortText[i],
                        isActive = false
                    )
                )
            }

//            state = state.copy(
//                listOfDays = listOfDaysPopulation
//            )

            state = listOfDaysPopulation
        }
        else{
            state = listofDaysFromApp
//            state = state.copy(
//                listOfDays = listofDaysFromApp
//            )
        }
    }


//    fun populateDaysTracked(listofDays: ListOfDays){
//        state = listofDays
//    }


    fun onDayChanged(dayChanged: Day){
//        var daysList = state.listOfDays.toMutableList()
//
//        var modifiedDay = daysList[dayChanged.orderId]
//        modifiedDay.isActive = !modifiedDay.isActive
//
//        daysList[dayChanged.orderId] = modifiedDay
//
//        state = state.copy(
//            listOfDays = daysList
//        )

//        var modifiedDay = state[dayChanged.orderId].copy(isActive = !dayChanged.isActive)
        val newActiveState = !state[dayChanged.orderId].isActive
        state[dayChanged.orderId] = state[dayChanged.orderId].copy(isActive = newActiveState)

    }
}

@Suppress("UNCHECKED_CAST")
class DaysTrackedViewModelFactory(private val listofDaysFromApp: MutableList<Day>): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DaysTrackedViewModel(listofDaysFromApp = listofDaysFromApp) as T
    }
}

data class ListOfDays(
//    val listOfDays: List<Day> = emptyList()
    val listOfDays: MutableList<Day>
) {
    fun isEmpty(): Boolean {
        return listOfDays.isEmpty()
    }
}

data class Day(
    val orderId: Int = -1,
    val text: String = "",
    val shortText: String = "",
    val isActive: Boolean = false
)