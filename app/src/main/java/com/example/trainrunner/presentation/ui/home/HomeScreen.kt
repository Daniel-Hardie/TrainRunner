package com.example.trainrunner.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.trainrunner.R
import com.example.trainrunner.presentation.navigation.Screen
import com.example.trainrunner.presentation.ui.home.data.MetlinkLivePredictionModel
import com.example.trainrunner.presentation.ui.home.newShit.MetlinkApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.ButtonSize
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun HomeScreen(
    columnState: ScalingLazyColumnState,
    timeRemaining: String,
    timeRemainingOnChange: (String) -> Unit,
    liveTrackingTime: Date,
    liveTrackingTimeOnChange: (Date) -> Unit,
    isLoading: Boolean,
    isLoadingOnChange: (Boolean) -> Unit,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = viewModel.state

    var scheduledForMsg = "Scheduled: ${viewModel.state.day} ${viewModel.state.time24hr}"
    val livePrediction = remember { mutableStateOf(MetlinkLivePredictionModel()) }

    val predictionTimeRequest = sendRequest(
        stationId = homeState.stopIdSanitized,
        livePredictionState = livePrediction
    )






    LaunchedEffect(Unit) {
        while(true) {
            val departureList = livePrediction.component1().departures
            for (departure in departureList){
                val arrivalDate: Date = Date.from(OffsetDateTime.parse(departure.arrival.aimed, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant())
                if (departure.stopId == homeState.stopId && arrivalDate == viewModel.state.nextAlertDateTime) {
                    if(departure.arrival.expected != null){
                        var expectedTime = Date.from(OffsetDateTime.parse(departure.arrival.expected.toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant())
                        liveTrackingTimeOnChange(expectedTime)
                        isLoadingOnChange(false)

                        val nextAlertDateTime = viewModel.state.nextAlertDateTime
                        val currentDateTime = LocalDateTime.now()
                        val targetDateTime = LocalDateTime.ofInstant(expectedTime.toInstant(), ZoneId.systemDefault());

                        // find the difference in time to display
                        val timeDifference = Duration.between(currentDateTime, targetDateTime)
                        val daysDifference = timeDifference.toDays().toString().padStart(2, '0')
                        val hoursDifference = (timeDifference.toHours() % 24).toString().padStart(2, '0')
                        val minutesDifference = (timeDifference.toMinutes() % 60).toString().padStart(2, '0')
                        val secondsDifference = (timeDifference.seconds % 60).toString().padStart(2, '0')

                        val timeMessage = "${daysDifference} days, ${hoursDifference} : ${minutesDifference} : ${secondsDifference}"
                        timeRemainingOnChange(timeMessage)
                    }
                    break
                }
            }


            delay(10000)


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
                    text = isLoading.toString()
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
                    text = scheduledForMsg
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
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    text = liveTrackingTime.toString()
                )
            }
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

fun sendRequest(
    stationId: String,
    livePredictionState: MutableState<MetlinkLivePredictionModel>
) {

    val BASE_URL = "https://api.opendata.metlink.org.nz/v1/"
    val STOP_PREDICTION_ENDPOINT = "stop-predictions?stop_id=TAIT"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.opendata.metlink.org.nz/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(MetlinkApi::class.java)

    val call: Call<MetlinkLivePredictionModel?>? = api.getStopPredictionsByStationId(stationId);

    call!!.enqueue(object: Callback<MetlinkLivePredictionModel?> {
        override fun onResponse(call: Call<MetlinkLivePredictionModel?>, response: Response<MetlinkLivePredictionModel?>) {
            if(response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                livePredictionState.value = response.body()!!
            }
        }

        override fun onFailure(call: Call<MetlinkLivePredictionModel?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}