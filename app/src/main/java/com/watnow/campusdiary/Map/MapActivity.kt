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

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class MapActivity: AppCompatActivity() {

    private val TAG: String = "MapActivity"

    private val ACTIVITY_NUM: Int = 1

    private val mContext: Context = this
    // Properties
    private val BKC: String = "びわこ・くさつキャンパスBKC"
    private val OIC: String = "大阪いばらきキャンパスOIC"
    private val KIC: String = "衣笠キャンパスKIC"

    // these are the member used in this class
    private lateinit var spinner: Spinner
    private lateinit var chosenCampusName: String
    private lateinit var mapImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)
        Log.d(TAG, "onCreate: starting")

        // make adapter to set Spinner(which is a campusName picker)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.add(BKC)
        adapter.add(OIC)
        adapter.add(KIC)
        // set this adapter to Spinner
        spinner = findViewById<Spinner>(R.id.campusPicker)
        spinner.adapter = adapter
        this.chosenCampusName = spinner.selectedItem.toString()

        /*
            ToDo1: initialize ImageView
         */
        mapImage = findViewById(R.id.mapImage)
        mapImage.setImageResource(R.drawable.bkc_map)

//        val option: BitmapFactory.Options = BitmapFactory.Options()
//        option.inJustDecodeBounds = true
//        BitmapFactory.decodeResource(resources, R.drawable.bkc_map, option)
//        val imageWidth: Int = option.outWidth
//        val imageHeight: Int = option.outHeight
//        val imageType: String = option.outMimeType
//        var inSampleSize: Int = 4
//        option.inSampleSize = inSampleSize
//        option.inJustDecodeBounds = false
//        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bkc_map, option)
//        mapImage.setImageBitmap(bitmap)


        // initialize BottomNavigationViewEx
        setupBottomNavigationView()
    }

    override fun onStart() {
        super.onStart()

        /*
            ToDo2: Adjust ImageView by campusName Picker
         */
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        Toast.makeText(mContext, BKC, Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(mContext, OIC, Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(mContext, KIC, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(mContext, "Else", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
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