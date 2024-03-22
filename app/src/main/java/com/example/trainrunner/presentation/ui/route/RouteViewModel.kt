package com.example.trainrunner.presentation.ui.route

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class RouteViewModel(
    private val routeId: Int,
    private val repository: Repository = Graph.repository,
) : ViewModel() {
    var state by mutableStateOf(RouteState())
        private set

    init {
        if (routeId != -1) {
            viewModelScope.launch {
                repository
                    .getRoute(routeId)
                    .collectLatest {
                        // Shouldnt really have problem in the end where we query something not in database
                        // TODO see if this can be taken out when routes id is not hardcoded in TrainRunnerApp.kt
                        if(it != null){
                            state = state.copy(
                                stationOneCode = it.stationOneCode,
                                stationTwoCode = it.stationTwoCode,
                                isActive = it.isActive
                            )
                        }
                    }
                repository
                    .getNumberRouteDaysTracked(routeId)
                    .collectLatest {
                        state = state.copy(
                            daysTrackedCount = it
                        )
                    }
            }
        }
        else{
            state = state.copy(
                stationOneCode = "Pick station to board train",
                stationTwoCode = "Pick station to arrive at",
                daysTrackedCount = 0,
                timeTracked = Date(),
                isActive = true,
            )
        }
    }

    fun onStationOneCodeChanged(newCode: String){
        state = state.copy(stationOneCode = newCode)
    }

    fun onStationTwoCodeChanged(newCode: String){
        state = state.copy(stationTwoCode = newCode)
    }

    fun onIsActiveChanged(active: Boolean){
        state = state.copy(isActive = active)
    }

    fun addRoute() {
        viewModelScope.launch {
            repository.insertRoute(
                Route(
                    trainLineId = 1,
                    stationOneId = 122,
                    stationTwoId = 123,
                    stationOneCode = "",
                    stationTwoCode = "",
                    toWellington = false,
                    isActive = true
                )
            )
        }
    }

    fun deleteRoute(routeId: Int){
        viewModelScope.launch {
            repository.deleteRoute(routeId)
            repository.deleteRouteNotification(routeId)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class RouteViewModelFactory(private  val id: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RouteViewModel(routeId = id) as T
    }
}

data class RouteState(
    val stationOneCode: String = "Plz select value",
    val stationTwoCode: String = "Plz select value",
    val daysTrackedCount: Int = 0,
    val timeTracked: Date = Date(),
    val isActive: Boolean = false,
)