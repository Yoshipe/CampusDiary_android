package com.watnow.campusdiary.calendar

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_calendar_item.view.*

/**
 * Created by Shogo on 2018/02/14.
 */
class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val parentLayout: LinearLayout = view.calendar_item_linear_layout
    val itemTextView: TextView = view.date_txt
    val showEventlayout: LinearLayout = view.calendar_item_show_event

}