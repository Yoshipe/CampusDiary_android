package com.watnow.campusdiary.calendar

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Shogo on 2018/02/14.
 */
class CalenarDividerItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean, private val headerNum: Int): RecyclerView.ItemDecoration() {
    val calendarDate: CalendarDate = CalendarDate()
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) - headerNum
        val isFirstDate: String = calendarDate.getOnlyDate(position)
        if (position >= 0) {
            val column = position % spanCount
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
                    outRect.top = spacing
                }
            }

            when (isFirstDate) {
                "01", "02", "03", "04", "05", "06", "07" -> {
                    outRect.top = 4
                }
            }

            if (isFirstDate == "01" && column != 0) {
                outRect.left = 4
            }

        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }
    }
}