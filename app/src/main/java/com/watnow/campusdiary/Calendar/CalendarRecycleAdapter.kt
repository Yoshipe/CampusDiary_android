package com.watnow.campusdiary.Calendar

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextClock
import android.widget.TextView
import com.watnow.campusdiary.R
import kotlinx.android.synthetic.main.layout_calendar_item.view.*
import java.util.ArrayList

/**
 * Created by Shogo on 2018/02/12.
 */
class CalendarRecycleAdapter(private val context: Context, private val itemClickListener: CalendarViewHolder.ItemClickListener , private val itemList: List<String>)
    : RecyclerView.Adapter<CalendarViewHolder>() {
    val todayPosition = CalendarDate().todayPosition()
    private var myRecyclerView: RecyclerView? = null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        myRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        myRecyclerView = null
    }

    // １ブロック分の処理
    override fun onBindViewHolder(holder: CalendarViewHolder?, position: Int) {
        holder?.let {
            it.itemTextView.text = itemList.get(position)
            // 今日の場合背景を変える処理
            if (this.todayPosition.toInt() == position) {
                it.itemTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CalendarViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val myView = layoutInflater.inflate(R.layout.layout_calendar_item, parent, false)
        myView.setOnClickListener { view ->
            myRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }
        return CalendarViewHolder(myView)
    }
}