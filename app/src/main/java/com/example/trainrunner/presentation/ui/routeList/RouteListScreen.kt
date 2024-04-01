package com.example.trainrunner.presentation.ui.routeList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.trainrunner.R
import com.example.trainrunner.presentation.navigation.Screen
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import com.google.android.horologist.compose.material.Chip
import com.google.android.horologist.images.base.paintable.DrawableResPaintable

@Composable
fun RouteListScreen(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
){
    val viewModel = viewModel(modelClass = RouteListViewModel::class.java)
    val routes = viewModel.state.routeList
    val routeNotifications = viewModel.state.routeNotificationsList


    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            columnState = columnState,
            modifier = modifier.fillMaxSize()
        ) {
            item{
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    text = "Routes"
                )
            }
            if(routes.isNullOrEmpty()){
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary,
                        text = "No saved routes!"
                    )
                }

            }
            else{
                for (route in routes){
                    val currentRouteUniqueId = route.uniqueValueForRouteNotification
                    val relevantRouteNotifications = routeNotifications.filter { it.routeUniqueId == currentRouteUniqueId }
                    var daysTrackedList = ""
                    for(notification in relevantRouteNotifications){
                        daysTrackedList += notification.dayShortText + ","
                    }

                    var timeSelected = ""
                    if(relevantRouteNotifications.isNotEmpty()){
                        timeSelected = relevantRouteNotifications[0].time
                    }

                    daysTrackedList = daysTrackedList.dropLast(1)

                    item{
                        Chip(
                            label = "${route.stationOneCode} -> ${route.stationTwoCode}",
                            secondaryLabel = "${timeSelected} ${daysTrackedList}",
                            icon = DrawableResPaintable(R.drawable.ic_train),
                            onClick = { onNavigate(Screen.EditRoute.route) }
                        )
                    }
                }
            }
            item {
                Button(
                    id = R.drawable.ic_add,
                    contentDescription = "",
                    onClick = { onNavigate(Screen.AddRoute.route) },
                    buttonSize = ButtonSize.Small,
                )
            }
        }
    }
}