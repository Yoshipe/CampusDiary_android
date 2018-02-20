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
import android.widget.AbsListView
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
    private val calendarDate = CalendarDate()
    private val dateList = calendarDate.getAllDays()
    private val todayPosition = calendarDate.todayPosition()
    private val glmanager = GridLayoutManager(this,7)
    private val testitems :Array<String> = arrayOf<String>("Android","iOS","Windows","macOS","Unix")
    private var testAdapter: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        setupBottomNavigationView()
        select_date.text = calendarDate.getScrollTerm(todayPosition)
        calendarRecycleView.adapter = CalendarRecycleAdapter(this, this, dateList)
        calendarRecycleView.layoutManager = glmanager
        calendarRecycleView.addItemDecoration(CalenarDividerItemDecoration(7, 1, true, 0))
        calendarRecycleView.scrollToPosition(todayPosition - 7)
        calendarRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val leftPosition: Int = glmanager.findFirstVisibleItemPosition()
                select_date.text = calendarDate.getScrollTerm(leftPosition)
            }
        })
        testAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testitems)
        calendar_oneday_list.adapter = testAdapter
        calendar_oneday_list.setOnItemClickListener { parent, view, position, id ->
        }

    }

    override fun onItemClick(view: View, position: Int) {
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