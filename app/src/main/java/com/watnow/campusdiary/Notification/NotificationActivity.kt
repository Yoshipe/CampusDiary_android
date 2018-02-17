package com.watnow.campusdiary.Notification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
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

    // parameters
    private lateinit var notificationList: ListView
    private lateinit var searchTxt: TextView
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_activity)
        Log.d(TAG, "onCreate: starting")

        // initializing all parameters
        this.notificationList = findViewById(R.id.notifications_container)
        this.searchTxt = findViewById(R.id.search_txt)
        this.searchButton = findViewById(R.id.search_button)

        // setup BottomNavigationView
        setupBottomNavigationView()
    }

    override fun onStart() {
        super.onStart()
        searchButton.setOnClickListener {
            Toast.makeText(this, searchTxt.text, Toast.LENGTH_SHORT).show()
        }
        // ListViewを設定
        NotificationListViewAdapter(mContext, notificationList)
        leadImageFromListClicked(notificationList)
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

    /**
     * ListView StartActivity
     */
    private fun leadImageFromListClicked(listView: ListView) {
        val intent: Intent = Intent(mContext, NotificationImageActivity::class.java)
        listView.setOnItemClickListener { parent, view, position, id ->
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }

}