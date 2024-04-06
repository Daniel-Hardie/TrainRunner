package com.example.trainrunner.presentation.ui.home.data

import retrofit2.http.GET
interface MetlinkApi2 {
    @GET(ApiConstants.STOP_PREDICTION_ENDPOINT)
    suspend fun getLiveTrackingData(): MetlinkLivePredictionModel
}

object ApiConstants {
    const val BASE_URL = "https://api.opendata.metlink.org.nz/v1/"
    const val STOP_PREDICTION_ENDPOINT = "stop-predictions?stop_id=TAIT"
}