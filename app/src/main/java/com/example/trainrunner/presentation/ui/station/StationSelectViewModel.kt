package com.example.trainrunner.presentation.ui.station

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StationSelectViewModel(
    private val lineShortName: String,
    private val repository: Repository = Graph.repository
) : ViewModel() {
    var state by mutableStateOf(StationListState())
        private set

    init {
        getStationsByLineCode(lineShortName)
    }

    // If you ever want to add filtering for stations, e.g. by line?
    // https://youtu.be/-Kx9D54laqU?list=PLUPcj46QWTDWlxtIwE3A6VEWUFEO8nh0Z&t=413
    private fun getStationsByLineCode(lineShortName: String){
        viewModelScope.launch {
            repository
                .getAllStationsOnLineByLineShortName(lineShortName)
                .collectLatest {
                    state = state.copy(
                        stations = it
                    )
                }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class StationSelectViewModelFactory(private val lineShortName: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StationSelectViewModel(lineShortName = lineShortName) as T
    }
}

data class StationListState(
    val stations: List<String> = emptyList()
)