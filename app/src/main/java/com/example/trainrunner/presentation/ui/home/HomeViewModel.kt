package com.example.trainrunner.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getNextTrainTime()
    }

    fun getNextTrainTime(){
        viewModelScope.launch {
            repository
                .getNextSystemNotification
                .collectLatest {
                    if(it != null){
                        state = state.copy(
                            stationFullName = it.stationFullName,
                            stopId = it.stopId,
                            stopIdSanitized = it.stopIdSanitized,
                            toWellington = it.toWellington,
                            nextAlertDateTime = it.nextAlertDateTime,
                            day = it.day,
                            time24hr = it.time24hr
                        )
                    }
                }
        }
    }
}


data class HomeState(
    val stationFullName: String = "",
    val stopId: String = "",
    val stopIdSanitized: String = "",
    val toWellington: Boolean = false,
    val nextAlertDateTime: Date = Date(),
    val day: String = "",
    val time24hr: String = ""
)