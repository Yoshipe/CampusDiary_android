package com.watnow.campusdiary.Calendar

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseBooleanArray
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.layout_calendar_center.*
import kotlinx.android.synthetic.main.layout_calendar_item.*

class CalendarActivity : AppCompatActivity(), CalendarViewHolder.ItemClickListener {
    private val TAG: String = "CalendarActivity"
    private val ACTIVITY_NUM: Int = 0
    private val mContext: Context = this
    private val dateList = CalendarDate().getAllDays()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        setupBottomNavigationView()
        var hoges: List<String>? = null
        val tmpList: MutableList<String> = mutableListOf()
        val todayPosition = CalendarDate().todayPosition()
        calendarRecycleView.adapter = CalendarRecycleAdapter(this, this, dateList)
        calendarRecycleView.layoutManager = GridLayoutManager(this, 7) as RecyclerView.LayoutManager?
        calendarRecycleView.addItemDecoration(CalenarDividerItemDecoration(7, 1, true, 0))
        calendarRecycleView.scrollToPosition(todayPosition.toInt())
    }


    override fun onItemClick(view: View, position: Int) {
        select_date.text = CalendarDate().getday(position)
        calendarRecycleView.adapter.notifyDataSetChanged()
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
