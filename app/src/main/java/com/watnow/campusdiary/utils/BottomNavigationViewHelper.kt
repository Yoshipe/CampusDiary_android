package com.watnow.campusdiary.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.*
import com.watnow.campusdiary.calendar.CalendarActivity
import com.watnow.campusdiary.map.MapActivity
import com.watnow.campusdiary.reference.ReferenceActivity
import com.watnow.campusdiary.timetable.TimeTableActivity

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
open class BottomNavigationViewHelper {
    // setup BottomNavView without any animations
    fun setupBottomNavigationView(bottomNavigationViewEx: BottomNavigationViewEx) {
        bottomNavigationViewEx.enableAnimation(false)
        bottomNavigationViewEx.enableItemShiftingMode(false)
        bottomNavigationViewEx.enableShiftingMode(false)
        bottomNavigationViewEx.setTextVisibility(false)
    }

    // enable Navigation by using Intent
    fun enableNavigation(context: Context, view: BottomNavigationViewEx) {
        view.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                // ACTIVITY_NUM = 0
                R.id.ic_calendar -> {
                    val intent1 = Intent(context, CalendarActivity::class.java)
                    context.startActivity(intent1)
                    true
                }
                // ACTIVITY_NUM = 1
                R.id.ic_location -> {
                    val intent2 = Intent(context, MapActivity::class.java)
                    context.startActivity(intent2)
                    true
                }
                // ACTIVITY_NUM = 2
                R.id.ic_timetable -> {
                    val intent3 = Intent(context, TimeTableActivity::class.java)
                    context.startActivity(intent3)
                    true
                }
                // ACTIVITY_NUM = 3
                R.id.ic_notification -> {
                    val intent4 = Intent(context, ReferenceActivity::class.java)
                    context.startActivity(intent4)
                    true
                }
                else -> {
                    Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show()
                    false
                }
            }
        }
    }
}