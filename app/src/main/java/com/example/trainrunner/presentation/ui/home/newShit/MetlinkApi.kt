package com.example.trainrunner.presentation.ui.home.newShit

import com.example.trainrunner.presentation.ui.home.data.MetlinkLivePredictionModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

public interface MetlinkApi {
    @Headers(
        "Accept: application/json",
        "x-api-key: "
    )
    @GET("stop-predictions")
    fun getStopPredictionsByStationId(
        @Query("stop_id") stationId: String
    ): Call<MetlinkLivePredictionModel?>?
}