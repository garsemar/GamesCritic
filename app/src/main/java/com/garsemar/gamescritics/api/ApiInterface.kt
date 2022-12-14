package com.garsemar.gamescritics.api

import com.garsemar.gamescritics.model.Games
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    //Aquí posem les operacions GET,POST, PUT i DELETE vistes abans
    companion object {
        val BASE_URL = "https://swapi.dev/api/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
    @GET()
    fun getData(@Url url: String): Call<Games>
}
