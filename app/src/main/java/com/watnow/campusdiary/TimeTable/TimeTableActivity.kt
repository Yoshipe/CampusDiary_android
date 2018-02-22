package com.watnow.campusdiary.TimeTable

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Display
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.layout_time_table_contents.*

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class TimeTableActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "TimeTableActivity"

    private val ACTIVITY_NUM: Int = 2

    private val mContext: Context = this

    // 画面立ち上げ時に１回だけ実行する
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_table_activity)
        Log.d(TAG, "onCreate: starting")

        setupBottomNavigationView()
        // ボタン全てにクリック処理を書く (コールバックメソッドを使って同一処理)
        button1_1.setOnClickListener(this)
        button1_2.setOnClickListener(this)
        button1_3.setOnClickListener(this)
        button1_4.setOnClickListener(this)
        button1_5.setOnClickListener(this)
        button1_6.setOnClickListener(this)
        button1_7.setOnClickListener(this)
        button2_1.setOnClickListener(this)
        button2_2.setOnClickListener(this)
        button2_3.setOnClickListener(this)
        button2_4.setOnClickListener(this)
        button2_5.setOnClickListener(this)
        button2_6.setOnClickListener(this)
        button2_7.setOnClickListener(this)
        button3_1.setOnClickListener(this)
        button3_2.setOnClickListener(this)
        button3_3.setOnClickListener(this)
        button3_4.setOnClickListener(this)
        button3_5.setOnClickListener(this)
        button3_6.setOnClickListener(this)
        button3_7.setOnClickListener(this)
        button4_1.setOnClickListener(this)
        button4_2.setOnClickListener(this)
        button4_3.setOnClickListener(this)
        button4_4.setOnClickListener(this)
        button4_5.setOnClickListener(this)
        button4_6.setOnClickListener(this)
        button4_7.setOnClickListener(this)
        button5_1.setOnClickListener(this)
        button5_2.setOnClickListener(this)
        button5_3.setOnClickListener(this)
        button5_4.setOnClickListener(this)
        button5_5.setOnClickListener(this)
        button5_6.setOnClickListener(this)
        button5_7.setOnClickListener(this)
    }

    // 画面を表示する直前に実行する
    override fun onResume() {
        super.onResume()
        Log.d("onResume", ": Started")
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

    override fun onClick(view: View?) {
        Toast.makeText(mContext, "$view.toString() is clicked", Toast.LENGTH_SHORT).show()
    }

}