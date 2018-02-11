package com.watnow.campusdiary

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper

class CalendarActivity : AppCompatActivity() {

    private val TAG: String = "CalendarActivity"

    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigationView()
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
        }
}
