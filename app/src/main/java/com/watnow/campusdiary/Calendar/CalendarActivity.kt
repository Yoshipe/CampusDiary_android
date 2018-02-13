package com.watnow.campusdiary.Calendar

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.layout_calendar_center.*

class CalendarActivity : AppCompatActivity(), CalendarViewHolder.ItemClickListener {

    private val TAG: String = "CalendarActivity"

    private val ACTIVITY_NUM: Int = 0

    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        setupBottomNavigationView()
        val hoges:List<String> = listOf("1","2")
        calendarRecycleView.adapter = CalendarRecycleAdapter(this, this, hoges)
        calendarRecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(applicationContext, "position$position was tapped", Toast.LENGTH_SHORT).show()
    }

    /* *
    *  BottomNavigationView setup
    */
    private fun setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView")
        val bottomNavigationViewEx: BottomNavigationViewEx = findViewById(R.id.bottomNavViewBar)
        var bottomNavViewHelper: BottomNavigationViewHelper = BottomNavigationViewHelper()
        bottomNavViewHelper.setupBottomNavigationView(bottomNavigationViewEx)
        bottomNavViewHelper.enableNavigation(mContext, bottomNavigationViewEx)
        val menu: Menu = bottomNavigationViewEx.menu
        val menuItem: MenuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }
}
