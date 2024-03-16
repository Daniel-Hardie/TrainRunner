package com.example.trainrunner.presentation.ui.route

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.trainrunner.R
import com.example.trainrunner.presentation.menuNameAndCallback
import com.example.trainrunner.presentation.navigation.Screen
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import com.google.android.horologist.compose.material.Chip

@Composable
fun AddRouteScreen(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    initialStation: String,
    finalStation: String,
    onClickDestinationList: () -> Unit,
){
    val destinationListInfo = menuNameAndCallback(
        onNavigate = onNavigate,
        menuNameResource = R.string.add_route_button_label,
        screen = Screen.InitialStationList
    )

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            columnState = columnState,
            modifier = modifier.fillMaxSize()
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    text = "Add Route"
                )
            }
            item{
                Chip(
                    label = "Initial Station",
                    secondaryLabel = initialStation,
                    onClick = onClickDestinationList
                )
            }
            item{
                Chip(
                    label = "Destination Station",
                    secondaryLabel = finalStation,
                    onClick = onClickDestinationList
                )
            }
            item{
                Chip(
                    label = "Days Tracked",
                    secondaryLabel = "3 selected",
                    onClick = destinationListInfo.clickHandler
                )
            }
            item{
                Chip(
                    label = "Days Tracked",
                    secondaryLabel = "3 selected",
                    onClick = destinationListInfo.clickHandler
                )
            }
            item{
                Chip(
                    label = "Time",
                    secondaryLabel = "6:57am",
                    onClick = destinationListInfo.clickHandler
                )
            }
            item {
                Button(
                    id = R.drawable.ic_settings,
                    contentDescription = "",
                    onClick = { },
                    buttonSize = ButtonSize.Small,
                )
            }
            item {
                Button(
                    id = R.drawable.ic_settings,
                    contentDescription = "",
                    onClick = {},
                    buttonSize = ButtonSize.Small,
                )
            }
        }
    }
}