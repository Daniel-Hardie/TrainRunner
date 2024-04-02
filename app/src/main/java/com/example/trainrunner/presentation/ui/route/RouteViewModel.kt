package com.example.trainrunner.presentation.ui.route

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.data.room.models.RouteNotification
import com.example.trainrunner.presentation.data.room.models.Station
import com.example.trainrunner.presentation.data.room.models.SystemNotification
import com.example.trainrunner.presentation.repository.Repository
import com.example.trainrunner.presentation.ui.daysTracked.Day
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
                    .getRouteById(routeId)
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
                isActive = true,
            )
        }

        getAllStations()
    }

    fun getAllStations(){
        viewModelScope.launch {
            repository.getAllStations.collectLatest {
                state = state.copy(
                    stations = it
                )
            }
        }
    }

    fun onTrainLineChanged(lineShortName: String, selectedMetlinkRouteId: String){
        state = state.copy(
            metlinkRouteShortName = lineShortName,
            selectedMetlinkRouteId = selectedMetlinkRouteId
        )
    }

    fun onStationOneCodeChanged(newCode: String){
        state = state.copy(stationOneCode = newCode)
    }

    fun onStationTwoCodeChanged(newCode: String){
        state = state.copy(stationTwoCode = newCode)
    }

    fun onSelectedDaysChanged(selectedDays: List<Day>){
        var daysActiveCount = 0
        for (day in selectedDays){
            if (day.isActive){
                daysActiveCount++
            }
        }
        state = state.copy(daysTrackedCount = daysActiveCount)
    }

    fun onDaysTrackedCountChanged(newNumberDaysTracked: Int){
        state = state.copy(daysTrackedCount = newNumberDaysTracked)
    }

    fun saveRoute(uniqueRouteId: Int) {
        viewModelScope.launch {
            repository.insertRoute(
                Route(
                    uniqueValueForRouteNotification = uniqueRouteId,
                    metlinkRouteId = state.selectedMetlinkRouteId,
                    metlinkRouteShortName = state.metlinkRouteShortName,
                    stationOneCode = state.stationOneCode,
                    stationTwoCode = state.stationTwoCode,
                    toWellington = false,
                    isActive = true
                )
            )
        }
    }

//    fun saveRouteNotifications(listToPersist: List<RouteNotification>){
    fun saveRouteNotifications(routeId: Int, time: String, savedDays: List<Day>){
        var listToPersist: MutableList<RouteNotification> = mutableListOf()
        for(day in savedDays){
            if(day.isActive){
                listToPersist.add(
                    RouteNotification(
                        routeUniqueId = routeId,
                        orderId = day.orderId,
                        dayText = day.text,
                        dayShortText = day.shortText,
                        time = time,
                        isActive = true
                    )
                )
            }
        }

        viewModelScope.launch {
            repository.insertRouteNotificationList(listToPersist)
        }
    }

    fun saveSystemNotification(
        uniqueId: Int,
        selectedScheduleTime: MetlinkSchedule,
        routeNotificationList: List<RouteNotification>,
        stationStopId: String,
        stationFullName: String
    ){
        var systemNotificationList: MutableList<SystemNotification> = mutableListOf()   // I am making this to save
        for (routeNotification in routeNotificationList){
            systemNotificationList.add(
                SystemNotification(
                    uniqueValueForRouteNotification = uniqueId,
                    metlinkRouteShortName = state.metlinkRouteShortName,
                    stopId = stationStopId,  // from station table
                    stopIdSanitized = state.stationOneCode,
                    stationFullName = stationFullName,   // from station table
                    toWellington = selectedScheduleTime.toWellington,
                    day = routeNotification.dayText,
                    time24hr = routeNotification.time,
                    nextAlertDateTime = Date()
                )
            )
        }

        viewModelScope.launch {
            repository.insertSystemNotification(systemNotificationList)
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
class RouteViewModelFactory(private val id: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RouteViewModel(routeId = id) as T
    }
}

data class RouteState(
    val selectedMetlinkRouteId: String = "",
    val metlinkRouteShortName: String = "",
    val stationOneCode: String = "Plz select value",
    val stationTwoCode: String = "Plz select value",
    val daysTrackedCount: Int = 0,
    val isActive: Boolean = false,
    val stations: List<Station> = emptyList(),
    val testRoute: List<Route> = emptyList()
)