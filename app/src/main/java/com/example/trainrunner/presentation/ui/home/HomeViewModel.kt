package com.example.trainrunner.presentation.ui.home

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
import java.util.Date

class HomeViewModel(
    private val isLoading: Boolean,
    private val repository: Repository = Graph.repository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        state = state.copy(
            isLoading = isLoading
        )
        getNextTrainTime()
    }

    fun updateExpiredTrainTimes(){
        viewModelScope.launch {
            repository.updateExpiredSystemNotifications()
        }
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
                            savedScheduleTime = it.nextAlertDateTime,
                            day = it.day,
                            time24hr = it.time24hr
                        )
                    }
                }
        }
    }

    fun updateIsLoading(isLoading: Boolean){
        state = state.copy(
            isLoading = isLoading
        )
    }
}


@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val isLoading: Boolean): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(isLoading = isLoading) as T
    }
}


data class HomeState(
    val stationFullName: String = "",
    val stopId: String = "",
    val stopIdSanitized: String = "",
    val toWellington: Boolean = false,
    val savedScheduleTime: Date = Date(),
    val day: String = "",
    val time24hr: String = "",
    val isLoading: Boolean = true
)