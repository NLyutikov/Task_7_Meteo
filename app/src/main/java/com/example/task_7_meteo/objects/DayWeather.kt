package com.example.task_7_meteo.objects

import java.io.Serializable

class DayWeather(val date: String): Serializable{
    var listMeteo: ArrayList<Meteo> = ArrayList(4)

    fun getInfo(tod: String): Meteo?{
        return listMeteo.find { it.tod == tod }
    }

    fun addMeteo(meteo: Meteo) {
        listMeteo.add(meteo)
    }

}
