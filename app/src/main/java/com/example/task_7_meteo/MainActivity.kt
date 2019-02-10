package com.example.task_7_meteo

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.example.task_7_meteo.fragments.LeftFragment
import com.example.task_7_meteo.fragments.RightFragment
import com.example.task_7_meteo.interfaces.ClickCallback
import com.example.task_7_meteo.objects.DayWeather

class MainActivity : FragmentActivity(), ClickCallback {
    override fun onClick(day: DayWeather) {
        val transaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.popBackStack()
        val fragment = RightFragment()
        val fragmentId = if (isPortraitOrientation())
            R.id.fragment
        else R.id.right_fragment
        transaction.replace(fragmentId, fragment)
        val bundle = Bundle()
        bundle.putSerializable(RightFragment.DAY_WEATHER_KEY, day)
        fragment.arguments = bundle
        transaction.addToBackStack("fullday")
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentId = if (isPortraitOrientation())
            R.id.fragment
        else R.id.left_fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentId, LeftFragment()).commit()
    }

    private fun isPortraitOrientation() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}
