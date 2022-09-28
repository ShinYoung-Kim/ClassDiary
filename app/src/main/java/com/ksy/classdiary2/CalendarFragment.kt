package com.ksy.classdiary2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.class_by_day_item.*
import kotlinx.android.synthetic.main.fragment_calender.*
import java.util.*

class CalendarFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*
        calendar.setOnDateChangeListener{
                view, year, month, dayOfMonth ->
            val oneDayOfMonth = dayOfMonth % 10
            if(oneDayOfMonth / 2 == 1) {
                day = "월"
            } else {
                day = "화"
            }
        }

         */
        val view:View = inflater.inflate(R.layout.fragment_calender, container, false)

        /*
        val calendar = view.findViewById<CalendarView>(R.id.calendar)
        val dayInInt : Int = calendar.get(Calendar.DAY_OF_WEEK)


        val day = when(dayInInt) {
            2 -> "월"
            3 -> "화"
            4 -> "수"
            5 -> "목"
            6 -> "금"
            else -> "nothing"
        }

         */

        //MainActivity().setDay(day)

        return view
    }
}