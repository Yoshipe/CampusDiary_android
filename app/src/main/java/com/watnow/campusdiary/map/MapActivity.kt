package com.watnow.campusdiary.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.utils.BottomNavigationViewHelper
import java.io.IOException
import java.io.InputStream

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class MapActivity: AppCompatActivity() {
    private val activityNum: Int = 1
    private val mContext: Context = this
    // Properties
    private val bkc: String = "びわこ・くさつキャンパスBKC"
    private val oic: String = "大阪いばらきキャンパスOIC"
    private val kic: String = "衣笠キャンパスKIC"
    // These are the member used in this class
    private lateinit var spinner: Spinner
    private lateinit var mapContent: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)

        // Initializing variables which are late init
        spinner = findViewById(R.id.campusPicker)
        mapContent = findViewById(R.id.mapContent)

        // Make adapter to set Spinner(which is a campusName picker)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.add(bkc)
        adapter.add(oic)
        adapter.add(kic)
        // Set this adapter to Spinner
        spinner.adapter = adapter
        // initialize spinner to chose campus map
        setupSpinnerCampusMap(spinner, mapContent)

        // initialize BottomNavigationViewEx
        setupBottomNavigationView()
    }

    /* *
    *  BottomNavigationView setup
    */
    private fun setupBottomNavigationView() {
        val bottomNavigationViewEx: BottomNavigationViewEx = findViewById(R.id.bottomNavViewBar)
        val bottomNavViewHelper = BottomNavigationViewHelper()
        bottomNavViewHelper.setupBottomNavigationView(bottomNavigationViewEx)
        bottomNavViewHelper.enableNavigation(mContext, bottomNavigationViewEx)
        val menu: Menu = bottomNavigationViewEx.menu
        val menuItem: MenuItem = menu.getItem(activityNum)
        menuItem.isChecked = true
    }

    /**
     * Spinner Action Helper
     */
    private fun setupSpinnerCampusMap(spinner: Spinner, mapContent: ImageView) {
        // set ItemSelectedListener to spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener  {
            var isStream: InputStream? = null
            var bitmap: Bitmap? = null
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 when (position) {
                    0 -> { // Bitmap for BKC campus map is created
                        try {
                            isStream = resources.assets.open("bkc.png")
                        } catch (e: IOException) {
                        }
                    }
                    1 -> { // Bitmap for OIC campus map is created
                        try {
                            isStream = resources.assets.open("oic.png")
                        } catch (e: IOException) {
                        }
                    }
                    2 -> { // Bitmap for KIC campus map is created
                        try {
                            isStream = resources.assets.open("kic.png")
                        } catch (e: IOException) {
                        }
                    }
                    else -> {
                    }
                }
                bitmap = BitmapFactory.decodeStream(isStream)
                mapContent.setImageBitmap(null)
                mapContent.setImageBitmap(bitmap)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}