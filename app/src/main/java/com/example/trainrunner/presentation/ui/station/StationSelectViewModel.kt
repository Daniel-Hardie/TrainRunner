package com.example.trainrunner.presentation.ui.station

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.data.room.models.Station
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StationSelectViewModel(
    private val stationRepository: Repository = Graph.repository
) : ViewModel() {
    var state by mutableStateOf(StationListState())
        private set

    init {
        getStations()
    }

    private fun getStations(){
        viewModelScope.launch {
            stationRepository.getAllStations.collectLatest {
                state = state.copy(
                    stations = it
                )
            }
        }
    }

    fun addStation(station: Station){
        viewModelScope.launch {
            stationRepository.insertStation(station)
        }
    }

    // If you ever want to add filtering for stations, e.g. by line?
    // https://youtu.be/-Kx9D54laqU?list=PLUPcj46QWTDWlxtIwE3A6VEWUFEO8nh0Z&t=413


}

data class StationListState(
    val stations: List<Station> = emptyList()
)