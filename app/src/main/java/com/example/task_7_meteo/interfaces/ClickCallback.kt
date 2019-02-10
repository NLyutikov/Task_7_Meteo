package com.example.task_7_meteo.interfaces

import com.example.task_7_meteo.objects.DayWeather

interface ClickCallback {
    fun onClick(day: DayWeather)
}
