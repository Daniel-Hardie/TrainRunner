package com.example.trainrunner.presentation.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.trainrunner.presentation.navigation.Screen
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Chip

@Composable
fun SettingsScreen(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
){
    val viewModel = viewModel(modelClass = SettingsViewModel::class.java)
    val numberOfRoutes = viewModel.state.numberOfRoutes
    val routeLabel = if (numberOfRoutes == 1){
        "$numberOfRoutes active route"
    }
    else{
        "$numberOfRoutes active routes"
    }

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
                Chip(
                    modifier = modifier,
                    label = "Routes",
                    secondaryLabel = routeLabel,
                    onClick = { onNavigate(Screen.Routes.route) }
                )
            }
        }
    }
}