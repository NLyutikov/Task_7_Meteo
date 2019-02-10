package com.example.task_7_meteo.objects

data class Meteo(
    var date: String,     //день прогноза
    val tod: String,      //период суток: 0 - ночь, 1 - утро, 2 - день, 3 - вечер (относительно date)
    val pressure: String, //атмосферное давление
    val temp: String,     //температура воздуха
    val feel: String,     //ощущаемая температура
    val humidity: String, //влажность воздуха
    val wind: String,     //направление и скорость ветра
    val cloud: String     //осадки
)
