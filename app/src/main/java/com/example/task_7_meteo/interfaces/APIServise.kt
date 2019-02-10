package com.example.task_7_meteo.interfaces

import com.example.task_7_meteo.objects.Meteo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIServise {
    @GET("inf/meteo.php")
    fun getMeteo(@Query("tid") tid: Int): Call<List<Meteo>>
}
