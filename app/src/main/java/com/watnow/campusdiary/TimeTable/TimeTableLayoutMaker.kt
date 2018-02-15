package com.watnow.campusdiary.TimeTable

import android.app.Activity
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by saitoushunsuke on 2018/02/13.
 */
class TimeTableLayoutMaker(private val activity: Activity) {

    private val TAG: String = "TimeTableLayoutMaker"

    private val nullTextView: TextView = TextView(activity)
    private val MON: TextView = TextView(activity)
    private val TUE: TextView = TextView(activity)
    private val WED: TextView = TextView(activity)
    private val THU: TextView = TextView(activity)
    private val FRI: TextView = TextView(activity)
    private val SUT: TextView = TextView(activity)
    private val date: Array<String> = arrayOf("月", "火", "水", "木", "金", "土")
    private val metrics: DisplayMetrics = this.activity.resources.displayMetrics

    // initで、全てのTextViewに曜日のテキストをセット
    init {
        this.MON.text = "月"
        this.TUE.text = "火"
        this.WED.text = "水"
        this.THU.text = "木"
        this.FRI.text = "金"
        this.SUT.text = "土"
    }

    fun setDateInTimeTable(layout: LinearLayout, nullText: Boolean) {
        var eachWidth: Int = when (nullText) {
            true -> (this.metrics.widthPixels - this.metrics.density * 20).toInt() / date.size
            false -> (this.metrics.widthPixels / date.size)
        }
        // each Height is defined as dp (current == 20dp)
        val eachHeight: Int = (this.metrics.density * 20).toInt()

        if (nullText) {
            layout.addView(nullTextView, (this.metrics.density * 20).toInt(), eachHeight)
        }

        layout.addView(MON, eachWidth, eachHeight)
        layout.addView(TUE, eachWidth, eachHeight)
        layout.addView(WED, eachWidth, eachHeight)
        layout.addView(THU, eachWidth, eachHeight)
        layout.addView(FRI, eachWidth, eachHeight)
        layout.addView(SUT, eachWidth, eachHeight)
    }
}