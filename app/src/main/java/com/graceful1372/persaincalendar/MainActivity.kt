package com.graceful1372.persaincalendar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.graceful1372.persaincalendar.adapter.CalendarAdapter
import com.graceful1372.persaincalendar.databinding.ActivityMainBinding
import com.graceful1372.persaincalendar.databinding.PersianCalendarBinding
import com.graceful1372.persaincalendar.model.CalendarModel
import com.graceful1372.persaincalendar.utils.toPersianMonth
import com.graceful1372.persaincalendar.utils.toPersianNumber
import com.graceful1372.persaincalendar.viewmodel.CalendarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Binding
    private lateinit var binding: ActivityMainBinding



    private val adapterCalendar: CalendarAdapter = CalendarAdapter {

        val y = it.iranianYear.toString()
        val m = it.iranianMonth.toString()
        val d = it.iranianDay.toString()

        val date = "$y/$m/$d"

        Toast.makeText(this, date, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}