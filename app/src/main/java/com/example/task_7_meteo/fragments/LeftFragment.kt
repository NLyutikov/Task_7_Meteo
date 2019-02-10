package com.example.task_7_meteo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.task_7_meteo.R
import com.example.task_7_meteo.adapters.CommonWeatherAdapter
import com.example.task_7_meteo.interfaces.APIServise
import com.example.task_7_meteo.objects.DayWeather
import com.example.task_7_meteo.objects.Meteo
import kotlinx.android.synthetic.main.days_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LeftFragment: Fragment() {

    private val KALININGRAD_ID = 2
    private var map: HashMap<String, DayWeather>? = null

    private var adapter = CommonWeatherAdapter()
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewA = inflater.inflate(R.layout.days_layout, container, false)

        val dayList = viewA.findViewById<RecyclerView>(R.id.recycler_days)
        dayList.adapter = adapter
        dayList.layoutManager = LinearLayoutManager(activity)
        refreshLayout = viewA.refresh
        if (map == null) {
            request()
            refreshLayout.isRefreshing = true
        }
        refreshLayout.setOnRefreshListener {
            request()
        }

        return viewA
    }

    private fun request()  {
        map = hashMapOf()
        val retrofit = Retrofit.Builder()
            .baseUrl(resources.getString(R.string.url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiServise = retrofit.create(APIServise::class.java)
        val callMeteo = apiServise.getMeteo(KALININGRAD_ID)
        callMeteo.enqueue(object : Callback<List<Meteo>> {
            override fun onFailure(call: Call<List<Meteo>>, t: Throwable) {
                Toast.makeText(activity, resources.getString(R.string.error), Toast.LENGTH_LONG).show()
                refreshLayout.isRefreshing = false
            }

            override fun onResponse(call: Call<List<Meteo>>, response: Response<List<Meteo>>) {
                response.body()?.forEach { weather ->
                    val tmp = map!![weather.date]
                    if (tmp == null) {
                        map!![weather.date] = DayWeather(weather.date).apply { addMeteo(weather) }
                    } else {
                        tmp.addMeteo(weather)
                    }
                }
                adapter.addData(map!!.values.toList())
                refreshLayout.isRefreshing = false
            }
        })
    }

}
