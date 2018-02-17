package com.watnow.campusdiary.Map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import java.io.IOException
import java.io.InputStream

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class MapActivity: AppCompatActivity() {

    // TAGs for debug
    private val TAG: String = "MapActivity"
    private val SPINNER: String = "Spinner"

    private val ACTIVITY_NUM: Int = 1

    private val mContext: Context = this
    // Properties
    private val BKC: String = "びわこ・くさつキャンパスBKC"
    private val OIC: String = "大阪いばらきキャンパスOIC"
    private val KIC: String = "衣笠キャンパスKIC"

    // These are the member used in this class
    private lateinit var spinner: Spinner
    private lateinit var mapContent: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)
        Log.d(TAG, "onCreate: starting")

        // Initializing variables which are late init
        spinner = findViewById(R.id.campusPicker)
        mapContent = findViewById(R.id.mapContent)

        // Make adapter to set Spinner(which is a campusName picker)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.add(BKC)
        adapter.add(OIC)
        adapter.add(KIC)
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
                            Log.d(SPINNER, e.toString())
                        }
                    }
                    1 -> { // Bitmap for OIC campus map is created
                        try {
                            isStream = resources.assets.open("oic.png")
                        } catch (e: IOException) {
                            Log.d(SPINNER, e.toString())
                        }
                    }
                    2 -> { // Bitmap for KIC campus map is created
                        try {
                            isStream = resources.assets.open("kic.png")
                        } catch (e: IOException) {
                            Log.d(SPINNER, e.toString())
                        }
                    }
                    else -> {
                        Log.d(SPINNER, "Else is selected")
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