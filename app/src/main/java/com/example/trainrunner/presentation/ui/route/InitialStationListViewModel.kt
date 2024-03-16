package com.example.trainrunner.presentation.ui.route

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trainrunner.data.StationRepository
import com.example.trainrunner.presentation.BaseApplication

class InitialStationListViewModel(initialStationRepository: StationRepository) : ViewModel() {
    private val _initialStations: MutableState<List<String>> = mutableStateOf(initialStationRepository.stations)
    val initialStations: State<List<String>>
        get() = _initialStations

    // This factory is used when we want to load values
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val baseApplication =
                    this[APPLICATION_KEY] as BaseApplication
                InitialStationListViewModel(
                    initialStationRepository = baseApplication.stationRepository
                )
            }
        }
    }
}