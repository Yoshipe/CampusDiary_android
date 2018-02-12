package com.watnow.campusdiary.Calendar

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.layout_calendar_center.*

class CalendarActivity : AppCompatActivity() {

    private val TAG: String = "CalendarActivity"

    private val ACTIVITY_NUM: Int = 0

    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        setupBottomNavigationView()
        val gridview = gridView_layout
        val adapter = CalendarItemAdapter(this, R.layout.layout_calendar_item, data)
        gridview.adapter = adapter
    }

    val data :ArrayList<CalendarItems>
    get(){
        val item_lists:ArrayList<CalendarItems> = ArrayList<CalendarItems>()

        item_lists.add(CalendarItems("item 1"))
        item_lists.add(CalendarItems("item 2"))
        item_lists.add(CalendarItems("item 3"))
        item_lists.add(CalendarItems("item 4"))
        item_lists.add(CalendarItems("item 5"))
        item_lists.add(CalendarItems("item 6"))
        return item_lists
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
