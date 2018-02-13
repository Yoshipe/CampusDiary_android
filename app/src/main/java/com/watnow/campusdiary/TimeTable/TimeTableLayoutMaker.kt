package com.watnow.campusdiary.TimeTable

import android.app.Activity
import android.graphics.Point
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by saitoushunsuke on 2018/02/13.
 */
class TimeTableLayoutMaker(activity: Activity) {

    private val TAG: String = "TimeTableLayoutMaker"

    private val MON: TextView = TextView(activity)
    private val TUE: TextView = TextView(activity)
    private val WED: TextView = TextView(activity)
    private val THU: TextView = TextView(activity)
    private val FRI: TextView = TextView(activity)
    private val SUT: TextView = TextView(activity)
    private val date: Array<String> = arrayOf("月", "火", "水", "木", "金", "土")
    private val screen = activity.windowManager.defaultDisplay
    private var screenWidth: Int = 1080

    fun setScreenWidth() {
        val point: Point = Point()
        this.screen.getSize(point)
        screenWidth = point.x
    }

    fun setDateInTextView() {
        MON.text = date[0]
        TUE.text = date[1]
        WED.text = date[2]
        THU.text = date[3]
        FRI.text = date[4]
        SUT.text = date[5]
    }

    fun setViewInLayout(layout: LinearLayout) {
        setScreenWidth()
        setDateInTextView()
        val eachWidth = screenWidth / date.size
        Log.d(TAG, eachWidth.toString())
        layout.addView(MON, eachWidth, 50)
        layout.addView(TUE, eachWidth, 50)
        layout.addView(WED, eachWidth, 50)
        layout.addView(THU, eachWidth, 50)
        layout.addView(FRI, eachWidth, 50)
        layout.addView(SUT, eachWidth, 50)
    }
}