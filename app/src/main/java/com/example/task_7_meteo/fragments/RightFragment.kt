package com.example.task_7_meteo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task_7_meteo.R
import com.example.task_7_meteo.adapters.ExtendedWeatherAdapter
import com.example.task_7_meteo.objects.DayWeather
import kotlinx.android.synthetic.main.fullday_layout.view.*

class RightFragment : Fragment() {
    private var adapter = ExtendedWeatherAdapter()

    companion object {
        const val DAY_WEATHER_KEY = "day_weather"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewB = inflater.inflate(R.layout.fullday_layout, container, false)

        val fullDayInfo = viewB.findViewById<RecyclerView>(R.id.fullday_items)
        fullDayInfo.adapter = adapter
        fullDayInfo.layoutManager = LinearLayoutManager(activity, 0, false)

        val dayWeather = arguments?.getSerializable(DAY_WEATHER_KEY) as? DayWeather

        viewB.date_b.text = dayWeather?.date

        adapter.addData(dayWeather)

        return viewB
    }
}
