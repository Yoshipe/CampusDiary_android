package com.watnow.campusdiary.Calendar

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.util.SparseBooleanArray
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
    private val todayPosition = CalendarDate().todayPosition()
    private var selectedItem: SparseBooleanArray = SparseBooleanArray()
    private var myRecyclerView: RecyclerView? = null
    public var prepostion = CalendarDate().todayPosition()
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
            // 今日の場合背景を変える処理
            if (this.todayPosition.toInt() == position) {
                it.itemTextView.setBackgroundColor(Color.RED)
            } else {
                it.itemTextView.setBackgroundResource(R.drawable.color_calendar_selector)
            }
            it.itemTextView.text = itemList.get(position)
            it.itemTextView.isSelected = selectedItem.get(position, false)
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
                val tmpPosition = it.getChildAdapterPosition(view)
                selectedItem.put(prepostion,false)
                selectedItem.put(tmpPosition,true)
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
                prepostion = tmpPosition
            }
        }
        return CalendarViewHolder(myView)
    }
}