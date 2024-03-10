package com.example.trainrunner.presentation.ui

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
import com.google.android.horologist.compose.material.Chip

@Composable
fun SettingsScreen(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
){
    val routesInfo = menuNameAndCallback(
        onNavigate = onNavigate,
        menuNameResource = R.string.settings_button_label,
        screen = Screen.Routes
    )

    val settingsMenuItems = listOf(
        menuNameAndCallback(
            onNavigate = onNavigate,
            menuNameResource = R.string.routes_label,
            screen = Screen.Routes
        ),
        menuNameAndCallback(
            onNavigate = onNavigate,
            menuNameResource = R.string.notifications_label,
            screen = Screen.Notifications
        )
    )

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
                    text = "Settings"
                )
            }
            item {
//                ChipComponent(
//                    title = "Routes",
//                    secondaryLabel = "2 active routes",
//                    onClick = { routesInfo.clickHandler }
//                )
                Chip(
                    modifier = modifier,
                    label = "Routes",
                    secondaryLabel = "3 active routes",
                    onClick = routesInfo.clickHandler
                )
            }
//            item {
//                ChipComponent(
//                    title = "Notifications",
//                    secondaryLabel = "Enabled",
//                    onClick = { settingsMenuItems[1].clickHandler }
//                )
//            }
        }
    }
}