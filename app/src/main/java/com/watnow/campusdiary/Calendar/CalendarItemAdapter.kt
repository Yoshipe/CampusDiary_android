package com.watnow.campusdiary.Calendar

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
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
class CalendarItemAdapter(private val getContext: Context, private val CustomLayoutId: Int, private val custom_item: ArrayList<CalendarItems>)
    : ArrayAdapter<CalendarItems>(getContext, CustomLayoutId, custom_item) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var row = convertView

        val Holder : ViewHolder

        if(row == null) {

            val inflater = (getContext as Activity).layoutInflater

            row = inflater.inflate(CustomLayoutId, parent, false)

            Holder = ViewHolder()

            Holder.txt = row!!.findViewById(R.id.test_txt) as TextView

            row.setTag(Holder)
        } else {
            Holder = row.getTag() as ViewHolder
        }

        val item = custom_item[position]

        Holder.txt!!.setText(item.text)

        return row
    }

    class ViewHolder
    {
        internal var txt: TextView? = null
    }
}