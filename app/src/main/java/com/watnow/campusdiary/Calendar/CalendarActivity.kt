package com.watnow.campusdiary.Calendar

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
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
import com.watnow.campusdiary.RegulationActivity
import com.watnow.campusdiary.SchoolYearActivity
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.calendar_activity.*
import kotlinx.android.synthetic.main.layout_calendar_center.*
import kotlinx.android.synthetic.main.layout_calendar_item.*

class CalendarActivity : AppCompatActivity(), CalendarViewHolder.ItemClickListener, NavigationView.OnNavigationItemSelectedListener {

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
        calendarRecycleView.layoutManager = glmanager as RecyclerView.LayoutManager?
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
        testAdapter?.let {
            calendar_oneday_list.adapter = it
        }
        calendar_oneday_list.setOnItemClickListener { parent, view, position, id ->
        }

        // FloatingActionButtonのクリック処理を記述
        FloatingActionButton.setOnClickListener { // ここに処理を書く
            val intent = Intent(this@CalendarActivity, CalendarAddScheduleActivity::class.java)
            startActivity(intent)
        }

        // NavigationDrawerのクリック処理
        navigatoinView.setNavigationItemSelectedListener(this)

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.toString() == getString(R.string.nav_Calendar)) {
            Log.d("TAG", "item = ${item.toString()}")
            return true
        }

        when (item.itemId) {
            R.id.nav_school_year -> {
                val intent = Intent(this@CalendarActivity, SchoolYearActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_Todo -> {
                val intent = Intent(this@CalendarActivity, RegulationActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_agreement -> {
                val intent = Intent(this@CalendarActivity, RegulationActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}