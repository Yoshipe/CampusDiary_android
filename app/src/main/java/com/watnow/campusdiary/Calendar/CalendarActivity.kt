package com.watnow.campusdiary.Calendar

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.*
import android.util.Log
import android.util.SparseBooleanArray
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.layout_calendar_center.*
import kotlinx.android.synthetic.main.layout_calendar_item.*

class CalendarActivity : AppCompatActivity(), CalendarViewHolder.ItemClickListener {
    private val TAG: String = "CalendarActivity"
    private val ACTIVITY_NUM: Int = 0
    private val mContext: Context = this
    private val dateList = CalendarDate().getAllDays()
    private val todayPosition = CalendarDate().todayPosition()
    private val glmanager = GridLayoutManager(this,7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        setupBottomNavigationView()
        calendarRecycleView.adapter = CalendarRecycleAdapter(this, this, dateList)
        calendarRecycleView.layoutManager = glmanager
        calendarRecycleView.addItemDecoration(CalenarDividerItemDecoration(7, 1, true, 0))
        calendarRecycleView.scrollToPosition(todayPosition - 7)
        val testitems :Array<String> = arrayOf<String>("Android","iOS","Windows","macOS","Unix")
        val testAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testitems)
        val testListView: ListView = calendar_oneday_list
        testListView.adapter = testAdapter
        testListView.setOnItemClickListener { parent, view, position, id ->
        }
    }

    override fun onItemClick(view: View, position: Int) {
        select_date.text = CalendarDate().getday(position)
        calendarRecycleView.adapter.notifyDataSetChanged()
        sliding_layout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
        calendarRecycleView.postDelayed(Runnable {
            run() {
                calendarRecycleView.smoothScrollBy(0,view.height*((position - glmanager.findFirstVisibleItemPosition())/7 -1 ))
            }
        },0)
    }

    /* *
    *  BottomNavigationView setup
    */
    private fun setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView")
        val bottomNavigationViewEx: BottomNavigationViewEx = findViewById(R.id.bottomNavViewBar)
        val bottomNavViewHelper: BottomNavigationViewHelper = BottomNavigationViewHelper()
        bottomNavViewHelper.setupBottomNavigationView(bottomNavigationViewEx)
        bottomNavViewHelper.enableNavigation(mContext, bottomNavigationViewEx)
        val menu: Menu = bottomNavigationViewEx.menu
        val menuItem: MenuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }
}