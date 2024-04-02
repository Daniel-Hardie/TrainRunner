//package com.example.trainrunner.presentation.ui.home
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import com.example.trainrunner.presentation.Graph
//import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
//import com.example.trainrunner.presentation.data.room.models.Route
//import com.example.trainrunner.presentation.data.room.models.RouteNotification
//import com.example.trainrunner.presentation.data.room.models.Station
//import com.example.trainrunner.presentation.data.room.models.SystemNotification
//import com.example.trainrunner.presentation.repository.Repository
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//import java.util.Date
//
//class UpdateSystemNotificationTableViewModel(
//    private val uniqueRouteId: Int,
//    private val selectedScheduleTime: MetlinkSchedule,
//    private val repository: Repository = Graph.repository,
//
//) : ViewModel() {
//    var state by mutableStateOf(SystemNotificationObject())
//        private set
//
//    init{
//        viewModelScope.launch {
//            repository
//                .getRouteByUniqueId(uniqueRouteId)
//                .collectLatest {
//                    state = state.copy(
//                        route = it
//                    )
//                }
//
//            repository
//                .getRouteNotificationsByUniqueId(uniqueRouteId)
//                .collectLatest {
//                    state = state.copy(
//                        routeNotificationList = it
//                    )
//                }
//
//            repository
//                .getStopIdByParentNameAndDirection(state.route.stationOneCode, selectedScheduleTime.toWellington)
//                .collectLatest {
//                    state = state.copy(
//                        stationList = it
//                    )
//                }
//        }
//
//        var listToPersist: MutableList<SystemNotification> = mutableListOf()
//        for (routeNotification in state.routeNotificationList){
//            listToPersist.add(
//                SystemNotification(
//                    uniqueValueForRouteNotification = uniqueRouteId,
//                    metlinkRouteShortName = state.route.metlinkRouteShortName,
//                    stopId = "",//stationStopId,  // from station table
//                    stopIdSanitized = state.route.stationOneCode,
//                    stationFullName = "",//stationFullName,   // from station table
//                    toWellington = selectedScheduleTime.toWellington,
//                    day = routeNotification.dayText,
//                    time24hr = routeNotification.time,
//                    nextAlertDateTime = Date()    // need to work out next date
//                )
//            )
//        }
//
//        state = state.copy(
//            systemNotificationList = listToPersist
//        )
//    }
//}
//
//
//data class SystemNotificationObject(
//    var systemNotificationList: MutableList<SystemNotification> = mutableListOf()
//    var route: Route = Route(toWellington = false),
//    var routeNotificationList: List<RouteNotification> = emptyList(),
//    var stationList: List<Station> = emptyList(),
//    var stationStopId: String = "",
//    var stationFullName: String = ""
//)
//
//@Suppress("UNCHECKED_CAST")
//class UpdateSystemNotificationTableViewModelFactory(private val uniqueRouteId: Int, private val selectedScheduleTime: MetlinkSchedule): ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return UpdateSystemNotificationTableViewModel(uniqueRouteId = uniqueRouteId, selectedScheduleTime = selectedScheduleTime) as T
//    }
//}