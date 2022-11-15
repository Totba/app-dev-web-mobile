package com.example.velo_nantes.adapter

import com.example.velo_nantes.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface StationApi {
    @GET("api/stations")
    suspend fun getStations(): Response<List<Station>>
}