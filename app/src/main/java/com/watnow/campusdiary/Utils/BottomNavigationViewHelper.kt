package com.watnow.campusdiary.Utils

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.*

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
open class BottomNavigationViewHelper {
    private val TAG: String = "BottomNavigationViewHelper"

    // setup BottomNavView without any animations
    fun setupBottomNavigationView(bottomNavigationViewEx: BottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView")
        bottomNavigationViewEx.enableAnimation(false)
        bottomNavigationViewEx.enableItemShiftingMode(false)
        bottomNavigationViewEx.enableShiftingMode(false)
        bottomNavigationViewEx.setTextVisibility(false)
    }

    // enable Navigation by using Intent
    fun enableNavigation(context: Context, view: BottomNavigationViewEx) {
        view.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.ic_calendar -> {
                    val intent1: Intent = Intent(context, CalendarActivity::class.java)
                    context.startActivity(intent1)
                    true
                }
                R.id.ic_location -> {
                    val intent2: Intent = Intent(context, MapActivity::class.java)
                    context.startActivity(intent2)
                    true
                }
                R.id.ic_timetable -> {
                    val intent3: Intent = Intent(context, TimeTableActivity::class.java)
                    context.startActivity(intent3)
                    true
                }
                R.id.ic_notification -> {
                    val intent4: Intent = Intent(context, NotificationActivity::class.java)
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