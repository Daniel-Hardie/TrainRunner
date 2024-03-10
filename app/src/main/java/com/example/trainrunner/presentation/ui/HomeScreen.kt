package com.example.trainrunner.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

@Composable
fun HomeScreen(
    columnState: ScalingLazyColumnState,
//    onClickSettingsList: () -> Unit,
    onNavigate: (String) -> Unit,
//    proceedingTimeTextEnabled: Boolean,
//    onClickProceedingTimeText: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val settingsInfo = menuNameAndCallback(
        onNavigate = onNavigate,
        menuNameResource = R.string.settings_button_label,
        screen = Screen.Settings
    )

    Box(modifier = modifier.fillMaxSize()) {
        ScalingLazyColumn(
            columnState = columnState,
            modifier = modifier.fillMaxSize()
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    text = stringResource(R.string.next_train)
                )
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    text = "Taita Station"
                )
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    text = "14 hours 8 mins"
                )
            }
            item {
                Button(
                    id = R.drawable.ic_settings,
                    contentDescription = "",
                    onClick = settingsInfo.clickHandler,
                    buttonSize = ButtonSize.Small,
                )
            }
        }
    }
}