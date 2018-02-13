package com.watnow.campusdiary.Calendar

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.watnow.campusdiary.R
import kotlinx.android.synthetic.main.layout_calendar_item.view.*

/**
 * Created by Shogo on 2018/02/14.
 */
class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val itemTextView: TextView = view.date_txt
}