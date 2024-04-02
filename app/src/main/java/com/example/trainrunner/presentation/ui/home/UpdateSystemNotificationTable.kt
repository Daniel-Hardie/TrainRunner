//package com.example.trainrunner.presentation.ui.home
//
//import androidx.compose.runtime.Composable
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
//
//@Composable
//fun UpdateSystemNotificationTable(
//    selectedScheduleTime: MetlinkSchedule,
//    selectedScheduleTimeOnChange: (MetlinkSchedule) -> Unit,
//    uniqueRouteIdValue: Int,
//    uniqueRouteIdValueOnChange: (Int) -> Unit,
//    navigateUp: () -> Unit
//){
//    val viewModel = viewModel<UpdateSystemNotificationTableViewModel>(
//        factory = UpdateSystemNotificationTableViewModelFactory(uniqueRouteIdValue, selectedScheduleTime))
//    val state = viewModel.state
//
//    if(uniqueRouteIdValue != -1 && selectedScheduleTime.departTime != "Please select time"){
//        uniqueRouteIdValueOnChange(-1)
//        selectedScheduleTimeOnChange(MetlinkSchedule(departTime = "Please select time", toWellington = false))
//    }
//
//    swipeDismissableNavController.navigateUp()
//
//}