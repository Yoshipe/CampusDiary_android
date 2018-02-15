package com.watnow.campusdiary.Notification

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
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
        val searchButton: Button = findViewById(R.id.search_button)
        val searchTxt: EditText = findViewById(R.id.search_txt)
        searchButton.setOnClickListener {
            Toast.makeText(this, searchTxt.text, Toast.LENGTH_SHORT).show()
        }
        // ListViewを設定
        val notificationList: ListView = findViewById(R.id.notifications_container)
        val listMaker: NotificationListViewAdapter = NotificationListViewAdapter(mContext, notificationList)

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