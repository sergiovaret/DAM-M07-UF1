package es.lasalle.m07.data.remote

import es.lasalle.m07.data.local.RocketEntity
import retrofit2.http.GET

interface SpaceXApi {
    @GET("rockets")
    suspend fun getRockets(): List<RocketEntity>
}