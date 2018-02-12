package com.watnow.campusdiary.Map

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class MapActivity: AppCompatActivity() {

    private val TAG: String = "MapActivity"

    private val ACTIVITY_NUM: Int = 1

    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)
        Log.d(TAG, "onCreate: starting")

        // make adapter to set Spinner(which is a campusName picker)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.add("BKC")
        adapter.add("OIC")
        adapter.add("KIC")
        // set this adapter to Spinner
        val spinner: Spinner = findViewById<Spinner>(R.id.campusPicker)
        spinner.adapter = adapter

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
        val menu: Menu = bottomNavigationViewEx.menu
        val menuItem: MenuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }

}