package com.example.task_7_meteo.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.task_7_meteo.objects.DayWeather
import com.example.task_7_meteo.interfaces.ClickCallback
import com.example.task_7_meteo.R

internal class CommonWeatherAdapter : RecyclerView.Adapter<CommonWeatherAdapter.ViewHolder>() {

    var data: List<DayWeather>? = null

    fun addData(list: List<DayWeather>) {
        this.data = list.sortedWith(compareBy {it.date})
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_a, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(view: ViewHolder, pos: Int) {
        view.date.text = data!![pos].date
        view.dayTemp.text = view.itemView.context.resources.getString(R.string.day_temp) + " " +
                data!![pos].getInfo(DAY_TEMP)!!.temp.replace ("&minus;","-")
        view.nightTemp.text = view.itemView.context.resources.getString(R.string.night_temp) + " " +
                data!![pos].getInfo(NIGHT_TEMP)!!.temp.replace("&minus;", "-")
        view.itemView.setOnClickListener {
            (view.view.context as ClickCallback).onClick(data!![pos])
        }
    }

    internal class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView
        val dayTemp: TextView
        val nightTemp: TextView

        init {
            date = view.findViewById(R.id.date_a)
            dayTemp = view.findViewById(R.id.temp_day)
            nightTemp = view.findViewById(R.id.temp_night)
        }
    }
}

private const val NIGHT_TEMP = "0"
private const val DAY_TEMP = "2"
