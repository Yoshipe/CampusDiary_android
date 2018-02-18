package com.watnow.campusdiary.Calendar

import me.mattak.moment.Duration
import me.mattak.moment.Moment
import me.mattak.moment.TimeUnit
import java.util.*

/**
 * Created by Shogo on 2018/02/15.
 */
class CalendarDate {
    // momentは現在時刻
    val moment = Moment(locale = Locale.JAPAN)
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

    // カレンダーに表示する全ての日を５年前から、5年後までの10年間分のListにして返すメソッド
    public fun getAllDays(): MutableList<String> {
        val dateList: MutableList<String> = mutableListOf()
        var tmpdate = firstDate
        while (tmpdate != endDate.add(1, TimeUnit.DAYS)) {
            dateList.add(tmpdate.day.toString())
            tmpdate = tmpdate.add(1, TimeUnit.DAYS)
        }
        return dateList
    }

    // positionを引数にとり、そのyyyy年MM月dd日を返すメソッド
    public fun getday(position: Int): String {
        var tmpdate = firstDate
        tmpdate = tmpdate.add(position.toLong(), TimeUnit.DAYS)
        return tmpdate.format("yyyy年MM月dd日")
    }

    // 今日の日付が何ブロック目かを返すメソッド
    public fun todayPosition(): Int {
        val diffTime = moment.intervalSince(firstDate).toString()
        val diffDate = diffTime.toLong() / (1000 * 60 * 60 * 24)
        return diffDate.toInt()
    }

    // positionを引数にとり、そのddを返すメソッド
    public fun getOnlyDate(position: Int): String {
        var tmpdate = firstDate
        tmpdate = tmpdate.add(position.toLong(), TimeUnit.DAYS)
        return tmpdate.format("dd")
    }
    // positionを引数にとり、そのMMを返すメソッド
    public fun getMonth(position: Int): String {
        var tmpdate = firstDate
        tmpdate = tmpdate.add(position.toLong(), TimeUnit.DAYS)
        return tmpdate.format("MM")
    }
}