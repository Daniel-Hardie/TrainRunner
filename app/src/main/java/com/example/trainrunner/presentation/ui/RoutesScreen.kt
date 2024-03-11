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
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import com.google.android.horologist.compose.material.Chip
import com.google.android.horologist.images.base.paintable.DrawableResPaintable

@Composable
fun RoutesScreen(
    columnState: ScalingLazyColumnState,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
){
    val editRouteInfo = menuNameAndCallback(
        onNavigate = onNavigate,
        menuNameResource = R.string.editroute_button_label,
        screen = Screen.EditRoute
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
                    text = "Routes"
                )
            }
            item{
                Chip(
                    label = "TAIT -> WELL",
                    secondaryLabel = "6:57am M,T,W,T,F",
                    icon = DrawableResPaintable(R.drawable.ic_train),
                    onClick = { /*onClickWatch*/ }
                )
            }
            item{
                Chip(
                    label = "WELL -> TAIT",
                    secondaryLabel = "4:17pm M,T,W,T,F",
                    icon = DrawableResPaintable(R.drawable.ic_train),
                    onClick = { /*onClickWatch*/ }
                )
            }
            item {
                Button(
                    id = R.drawable.ic_add,
                    contentDescription = "",
                    onClick = editRouteInfo.clickHandler,
                    buttonSize = ButtonSize.Small,
                )
            }
        }
    }
}