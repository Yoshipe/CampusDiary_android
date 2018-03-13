package com.watnow.campusdiary.calendar

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
    fun getAllDays(): MutableList<String> {
        val dateList: MutableList<String> = mutableListOf()
        var tmpdate = firstDate
        while (tmpdate != endDate.add(1, TimeUnit.DAYS)) {
            if (tmpdate.day.toString() == "1"){
                dateList.add(tmpdate.format("M/d"))
            } else {
                dateList.add(tmpdate.day.toString())
            }
            tmpdate = tmpdate.add(1, TimeUnit.DAYS)
        }
        return dateList
    }

    // positionを引数にとり、そのyyyy年MM月dd日を返すメソッド
    fun getday(position: Int): String {
        var tmpdate = firstDate
        tmpdate = tmpdate.add(position.toLong(), TimeUnit.DAYS)
        return tmpdate.format("yyyy年MM月dd日")
    }

    // 今日のpositionを返すメソッド
    fun todayPosition(): Int {
        val diffTime = moment.intervalSince(firstDate).toString()
        val diffDate = diffTime.toLong() / (1000 * 60 * 60 * 24)
        return diffDate.toInt()
    }

    // positionを引数にとり、そのddを返すメソッド
    fun getOnlyDate(position: Int): String {
        var tmpdate = firstDate
        tmpdate = tmpdate.add(position.toLong(), TimeUnit.DAYS)
        return tmpdate.format("dd")
    }

    // positionを引数にとり、そのMMを返すメソッド
    fun getMonth(position: Int): String {
        var tmpdate = firstDate
        tmpdate = tmpdate.add(position.toLong(), TimeUnit.DAYS)
        return tmpdate.format("MM")
    }

    // positionを引数にとり、そのyyyy年MM月を返すメソッド
    fun getYearMonth(position: Int): String {
        var tmpdate = firstDate
        tmpdate = tmpdate.add(position.toLong(), TimeUnit.DAYS)
        return tmpdate.format("yyyy年MM月")
    }

    // positionを引数にとり、何列目かを返すメソッド
    fun getRow(position: Int): Int {
        return position % 7 + 1
    }

    //position(カレンダーの一番左の時のみ)を引数にとり、その行のYYYY年MM月を返すメソッド
    fun getScrollTerm(position: Int): String {
        var tmpPosition = position
        do {
            if (getOnlyDate(tmpPosition).toInt() == 1){
                return getYearMonth(position+6)
            }
            tmpPosition += 1
        }while (position+7 > tmpPosition)
        return getYearMonth(position)
    }

    //positionからその日の学年暦、祝日の配列を返す
    fun getPublicDataNames(position: Int): MutableList<String> {
        val publicDataNames: MutableList<String> = mutableListOf()
        val day: String = getday(position)
        when (day) {
            "2018年04月01日" -> {
                publicDataNames.add("春学期開始")
                publicDataNames.add("オリエンテーション")
            }
            "2018年04月02日" -> publicDataNames.add("入学式(衣笠・BKC・OIC)")
            "2018年04月03日" -> publicDataNames.add("オリエンテーション")
            "2018年04月04日" -> publicDataNames.add("オリエンテーション")
            "2018年04月05日" -> publicDataNames.add("オリエンテーション")
            "2018年04月06日" -> publicDataNames.add("春セメスター授業開始")
            "2018年04月21日" -> publicDataNames.add("統一補講日①")
            "2018年04月29日" -> publicDataNames.add("昭和の日(休日)")
            "2018年04月30日" -> publicDataNames.add("昭和の日振替休日(授業日)")

            "2018年05月03日" -> publicDataNames.add("憲法記念日(休日)")
            "2018年05月04日" -> publicDataNames.add("みどりの日(休日)")
            "2018年05月05日" -> publicDataNames.add("こどもの日(休日)")
            "2018年05月12日" -> publicDataNames.add("授業日(木曜日分)")
            "2018年05月19日" -> publicDataNames.add("本学創立記念日(統一補講日②)")

            "2018年06月09日" -> publicDataNames.add("統一補講日③")
            "2018年06月30日" -> publicDataNames.add("統一補講日④")

            "2018年07月16日" -> publicDataNames.add("海の日(授業日)")
            "2018年07月20日" -> publicDataNames.add("春セメスター授業終了")
            "2018年07月21日" -> publicDataNames.add("統一補講日⑤")
            "2018年07月23日" -> publicDataNames.add("春セメスター定期試験開始")
            "2018年07月24日" -> publicDataNames.add("レポート試験統一締切日")
            "2018年07月28日" -> publicDataNames.add("定期試験日")

            "2018年08月01日" -> publicDataNames.add("春セメスター定期試験終了")
            "2018年08月02日" -> {
                publicDataNames.add("定期試験予備日")
                publicDataNames.add("夏季休暇開始")
            }
            "2018年08月07日" -> publicDataNames.add("追試験日")
            "2018年08月08日" -> publicDataNames.add("追試験日")
            "2018年08月27日" -> publicDataNames.add("夏季集中講義(第1週)開始")

            "2018年09月01日" -> publicDataNames.add("夏季集中講義(第1週)終了")
            "2018年09月03日" -> publicDataNames.add("夏季集中講義(第2週)開始")
            "2018年09月06日" -> publicDataNames.add("前期卒業合否発表日")
            "2018年09月08日" -> publicDataNames.add("夏季集中講義(第2週)終了")
            "2018年09月21日" -> publicDataNames.add("秋入学者オリエンテーション")
            "2018年09月23日" ->  {
                publicDataNames.add("春学期卒業式")
                publicDataNames.add("秋分の日")
            }
            "2018年09月25日" -> {
                publicDataNames.add("秋季入学式")
                publicDataNames.add("夏季休暇終了")
                publicDataNames.add("春学期終了")
            }
            "2018年09月26日" -> {
                publicDataNames.add("秋学期開始")
                publicDataNames.add("秋セメスター授業開始")
            }

            "2018年10月08日" -> publicDataNames.add("体育の日(授業日)")
            "2018年10月20日" -> publicDataNames.add("統一補講日①")
            "2018年10月27日" -> publicDataNames.add("授業日(月曜日分)")

            "2018年11月03日" -> publicDataNames.add("文化の日(休日)")
            "2018年11月10日" -> publicDataNames.add("統一補講日②")
            "2018年11月23日" -> publicDataNames.add("勤労感謝の日(授業日)")

            "2018年12月01日" -> publicDataNames.add("統一補講日③")
            "2018年12月22日" -> publicDataNames.add("統一補講日④")
            "2018年12月23日" -> publicDataNames.add("天皇誕生日(休日)")
            "2018年12月24日" -> publicDataNames.add("天皇誕生日振替休日(授業日)")
            "2018年12月26日" -> publicDataNames.add("冬期休暇開始")

            "2019年01月06日" -> publicDataNames.add("冬期休暇終了")
            "2019年01月07日" -> publicDataNames.add("秋セメスター授業再開")
            "2019年01月14日" -> publicDataNames.add("成人の日(休日)")
            "2019年01月18日" -> publicDataNames.add("秋セメスター授業終了")
            "2019年01月21日" -> publicDataNames.add("統一補講日⑤")
            "2019年01月22日" -> {
                publicDataNames.add("秋セメスター定期試験開始")
                publicDataNames.add("定期試験日")
                publicDataNames.add("レポート試験統一締切日")
            }
            "2019年01月26日" -> publicDataNames.add("定期試験日")
            "2019年01月30日" -> publicDataNames.add("秋セメスター定期試験終了")
            "2019年01月31日" -> {
                publicDataNames.add("定期試験予備日")
                publicDataNames.add("春期休暇開始")
            }
            "2019年02月06日" -> publicDataNames.add("追試験日")
            "2019年02月07日" -> publicDataNames.add("追試験日")

            "2019年03月06日" -> publicDataNames.add("卒業合否発表日")
            "2019年03月20日" -> publicDataNames.add("卒業式(衣笠)")
            "2019年03月21日" -> {
                publicDataNames.add("卒業式(朱雀・OIC)")
                publicDataNames.add("春分の日")
            }
            "2019年03月22日" -> publicDataNames.add("卒業式(BKC)")
            "2019年03月31日" -> {
                publicDataNames.add("春期休暇終了")
                publicDataNames.add("秋学期終了")
            }
        }
        return publicDataNames
    }
}