package com.example.trainrunner.presentation.ui.routeList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.data.room.models.RouteNotification
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RouteListViewModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {
    var state by mutableStateOf(RouteList())
        private set

    init {
        getAllRoutes()
        getAllRouteNotifications()
    }

    fun getAllRoutes(){
        viewModelScope.launch {
            repository
                .getAllRoutes
                .collectLatest {
                    state = state.copy(
                        routeList = it
                    )
                }
        }
    }

    fun getAllRouteNotifications() {
        viewModelScope.launch {
            repository
                .getAllRouteNotifications
                .collectLatest {
                    state = state.copy(
                        routeNotificationsList = it
                    )
                }
        }
    }
}


data class RouteList(
    val routeList: List<Route> = emptyList(),
    val routeNotificationsList: List<RouteNotification> = emptyList()
)