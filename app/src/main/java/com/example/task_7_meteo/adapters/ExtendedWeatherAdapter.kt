package com.example.task_7_meteo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.task_7_meteo.objects.DayWeather
import com.example.task_7_meteo.objects.Meteo
import com.example.task_7_meteo.R
import java.lang.IllegalStateException

@Suppress("DEPRECATION")
internal class ExtendedWeatherAdapter : RecyclerView.Adapter<ExtendedWeatherAdapter.ViewHolder>() {
    var dayWeather: DayWeather? = null
    var data: List<Meteo>? = null

    fun addData(dayWeather: DayWeather?) {
        this.dayWeather = dayWeather
        this.data = dayWeather!!.listMeteo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_b, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(view: ViewHolder, pos: Int) {
        
        val posTxt = pos.toString()
        view.tod.text = when (pos) {
            NIGHT ->  dayWeather?.getInfo(posTxt)!!.tod
                .replace(posTxt, view.itemView.context.resources.getString(R.string.night))
            MORNING -> dayWeather?.getInfo(posTxt)!!.tod
                .replace(posTxt, view.itemView.context.resources.getString(R.string.morning))
            DAY -> dayWeather?.getInfo(posTxt)!!.tod
                .replace(posTxt, view.itemView.context.resources.getString(R.string.day))
            EVENING -> dayWeather?.getInfo(posTxt)!!.tod
                .replace(posTxt, view.itemView.context.resources.getString(R.string.evening))
            else -> throw  IllegalStateException("wrong tod is $pos")
        }
        view.pressure.text = dayWeather?.getInfo(posTxt)?.pressure
        view.temp.text = dayWeather?.getInfo(posTxt)?.temp?.replace("&minus;", "-")
        view.feel.text = dayWeather?.getInfo(posTxt)?.feel?.replace("&minus;", "-")
        view.humidity.text = dayWeather?.getInfo(posTxt)?.humidity
        view.wind.text = dayWeather?.getInfo(posTxt)?.wind
        view.cloud.text = dayWeather?.getInfo(posTxt)?.cloud
        view.itemB.background = when(pos) {
            NIGHT -> view.itemView.context.resources.getDrawable(R.drawable.night)
            MORNING -> view.itemView.context.resources.getDrawable(R.drawable.morning)
            DAY -> view.itemView.context.resources.getDrawable(R.drawable.day)
            EVENING -> view.itemView.context.resources.getDrawable(R.drawable.evening)
            else -> throw  IllegalStateException("wrong tod is $pos")
        }

    }


    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tod: TextView
        val pressure: TextView
        val temp: TextView
        val feel: TextView
        val humidity: TextView
        val wind: TextView
        val cloud: TextView
        val itemB: LinearLayout

        init {
            tod = view.findViewById(R.id.tod)
            pressure = view.findViewById(R.id.pressure)
            temp = view.findViewById(R.id.temp)
            feel = view.findViewById(R.id.feel)
            humidity = view.findViewById(R.id.humidity)
            wind = view.findViewById(R.id.wind)
            cloud = view.findViewById(R.id.cloud)
            itemB = view.findViewById(R.id.item_b)
        }
    }
}

private const val NIGHT = 0
private const val MORNING = 1
private const val DAY = 2
private const val EVENING = 3
