package com.example.trainrunner.presentation.data.room.sources.station

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.launch

class StationModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository
                .deleteAllStations()

            StationsSource.stations.forEach { station ->
                repository
                    .insertStation(station)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class StationModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StationModel() as T
    }
}