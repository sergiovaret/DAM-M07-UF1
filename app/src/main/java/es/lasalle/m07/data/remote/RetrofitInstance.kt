package es.lasalle.m07.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    val api: SpaceXApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceXApi::class.java)
    }
}