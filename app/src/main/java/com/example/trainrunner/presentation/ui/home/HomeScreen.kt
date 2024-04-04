package com.example.trainrunner.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun HomeScreen(
    columnState: ScalingLazyColumnState,
    timeRemaining: String,
    timeRemainingOnChange: (String) -> Unit,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = viewModel.state

    LaunchedEffect(Unit) {
        while(true) {
            val nextAlertDateTime = viewModel.state.nextAlertDateTime
            val currentDateTime = LocalDateTime.now()
            val targetDateTime = LocalDateTime.ofInstant(nextAlertDateTime.toInstant(), ZoneId.systemDefault());

            // find the difference in time to display
            val timeDifference = Duration.between(currentDateTime, targetDateTime)
            val daysDifference = timeDifference.toDays().toString().padStart(2, '0')
            val hoursDifference = (timeDifference.toHours() % 24).toString().padStart(2, '0')
            val minutesDifference = (timeDifference.toMinutes() % 60).toString().padStart(2, '0')
            val secondsDifference = (timeDifference.seconds % 60).toString().padStart(2, '0')

            val timeMessage = "${daysDifference} days, ${hoursDifference} : ${minutesDifference} : ${secondsDifference}"
            timeRemainingOnChange(timeMessage)
            delay(1000)
        }
    }

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
                    text = homeState.stationFullName
                )
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    text = timeRemaining
                )
            }
//            item {
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    color = MaterialTheme.colors.primary,
//                    text = blah.toString()
//                )
//            }
            item {
                Button(
                    id = R.drawable.ic_settings,
                    contentDescription = "",
                    onClick = {
                        onNavigate(Screen.Settings.route)
                    },
                    buttonSize = ButtonSize.Small,
                )
            }
        }
    }
}