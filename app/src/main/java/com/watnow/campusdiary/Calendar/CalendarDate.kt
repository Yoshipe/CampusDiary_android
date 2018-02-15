package com.watnow.campusdiary.Calendar

import me.mattak.moment.Moment
import me.mattak.moment.TimeUnit

/**
 * Created by Shogo on 2018/02/15.
 */
class CalendarDate {
    // momentは現在時刻
    val moment = Moment()
    var firstDate = moment.subtract(5, TimeUnit.YEARS)
    var endDate = moment.add(5, TimeUnit.YEARS)
    init {
        firstDate = firstDate.subtract((moment.day - 1).toLong(), TimeUnit.DAYS)
        endDate = endDate.add(1, TimeUnit.MONTHS)
        endDate = endDate.subtract(moment.day.toLong(), TimeUnit.DAYS)
        when (firstDate.weekdayName) {
            "月曜日" -> firstDate = firstDate.subtract(0, TimeUnit.DAYS)
            "火曜日" -> firstDate = firstDate.subtract(1, TimeUnit.DAYS)
            "水曜日" -> firstDate = firstDate.subtract(2, TimeUnit.DAYS)
            "木曜日" -> firstDate = firstDate.subtract(3, TimeUnit.DAYS)
            "金曜日" -> firstDate = firstDate.subtract(4, TimeUnit.DAYS)
            "土曜日" -> firstDate = firstDate.subtract(5, TimeUnit.DAYS)
            "日曜日" -> firstDate = firstDate.subtract(6, TimeUnit.DAYS)
            else -> println("error")
        }
        when (endDate.weekdayName) {
            "月曜日" -> endDate = endDate.add(6, TimeUnit.DAYS)
            "火曜日" -> endDate = endDate.add(5, TimeUnit.DAYS)
            "水曜日" -> endDate = endDate.add(4, TimeUnit.DAYS)
            "木曜日" -> endDate = endDate.add(3, TimeUnit.DAYS)
            "金曜日" -> endDate = endDate.add(2, TimeUnit.DAYS)
            "土曜日" -> endDate = endDate.add(1, TimeUnit.DAYS)
            "日曜日" -> endDate = endDate.add(0, TimeUnit.DAYS)
            else -> println("error")
        }
    }
    public fun getAllDays(): MutableList<String> {
        val dateList: MutableList<String> = mutableListOf()
        var tmpdate = firstDate
        while (tmpdate != endDate.add(1,TimeUnit.DAYS)) {
            dateList.add(tmpdate.day.toString())
            tmpdate = tmpdate.add(1, TimeUnit.DAYS)
        }
        return dateList
    }
}