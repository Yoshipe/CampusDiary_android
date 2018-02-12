package com.watnow.campusdiary.Notification

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class NotificationActivity : AppCompatActivity() {

    private val TAG: String = "NotificationActivity"

    private val ACTIVITY_NUM: Int = 3

    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_activity)
        Log.d(TAG, "onCreate: starting")

        setupBottomNavigationView()
    }

    override fun onStart() {
        super.onStart()
        val search_button: Button = findViewById(R.id.search_button)
        val search_txt: TextView = findViewById(R.id.search_txt)
        search_button.setOnClickListener {
            Toast.makeText(this, search_txt.text, Toast.LENGTH_SHORT).show()
        }
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