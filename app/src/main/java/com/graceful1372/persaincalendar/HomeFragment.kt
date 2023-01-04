package com.graceful1372.persaincalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.graceful1372.persaincalendar.adapter.CalendarAdapter
import com.graceful1372.persaincalendar.databinding.FragmentHomeBinding
import com.graceful1372.persaincalendar.model.CalendarModel
import com.graceful1372.persaincalendar.utils.toPersianMonth
import com.graceful1372.persaincalendar.utils.toPersianNumber
import com.graceful1372.persaincalendar.viewmodel.CalendarViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentHomeBinding


    @Inject
    lateinit var today: CalendarModel


    private val viewModel: CalendarViewModel by viewModels()

    private val adapterCalendar: CalendarAdapter = CalendarAdapter {

        val y = it.iranianYear.toString()
        val m = it.iranianMonth.toString()
        val d = it.iranianDay.toString()

        val date = "$y/$m/$d"
        binding.txtDayOfWeek.text = date

        Toast.makeText(activity, date, Toast.LENGTH_SHORT).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitView
        binding.apply {

            viewModel.getMonthLiveData().observe(viewLifecycleOwner) {
                showCalendar(it.toMutableList())
            }


            recyclerCalendar.adapter = adapterCalendar
            recyclerCalendar.itemAnimator = null

            //button next month and previous
            imgNextMonth.setOnClickListener {
                viewModel.getNextMonth()
            }

            //button previous month
            imgPreviousMonth.setOnClickListener {
                viewModel.getPreviousMonth()
            }


        }

    }

    private fun showCalendar(list: List<CalendarModel>) {
        binding.txtMonthName.text =
            list.last().iranianMonth.toPersianMonth(requireContext())
        binding.txtYear.text = list.last().iranianYear.toPersianNumber()
        adapterCalendar.submitList(list)
    }
}



