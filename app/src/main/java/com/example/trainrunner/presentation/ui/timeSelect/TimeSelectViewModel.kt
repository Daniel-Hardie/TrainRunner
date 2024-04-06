package com.example.trainrunner.presentation.ui.timeSelect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TimeSelectViewModel(
    private val repository: Repository = Graph.repository,
    private val selectedStationOneCode: String,
    private val selectedStationTwoCode: String,
    private val selectedMetlinkRouteId: Int
) : ViewModel(){
    var state by mutableStateOf(TimeSelectState())
        private set

    init {
        getScheduleTimes(selectedStationOneCode, selectedStationTwoCode, selectedMetlinkRouteId)
    }
    private fun getScheduleTimes(
        selectedStationOneCode: String,
        selectedStationTwoCode: String,
        selectedMetlinkRouteId: Int
    ){
        viewModelScope.launch {
            repository
                .getAvailableTimesForStop(selectedStationOneCode, selectedStationTwoCode, selectedMetlinkRouteId)
                .collectLatest {
                    state = state.copy(
                        availableTimes = it
                    )
                }
        }
    }
}


@Suppress("UNCHECKED_CAST")
class TimeSelectViewModelFactory(private val stationOneCode: String, private val stationTwoCode: String, private val selectedMetlinkRouteId: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TimeSelectViewModel(selectedStationOneCode = stationOneCode, selectedStationTwoCode = stationTwoCode, selectedMetlinkRouteId = selectedMetlinkRouteId) as T
    }
}


data class TimeSelectState(
    val availableTimes: List<MetlinkSchedule> = emptyList()
)