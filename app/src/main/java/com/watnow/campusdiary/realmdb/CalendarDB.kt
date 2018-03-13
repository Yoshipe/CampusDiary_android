package com.watnow.campusdiary.realmdb

import io.realm.RealmObject

/**
 * Created by saitoushunsuke on 2018/03/10.
 */
open class CalendarDB: RealmObject() {
    // 予定Title
    var title: String = ""
    // テーマ
    var theme: String = ""
    // 詳細
    var detail: String = ""
    // 日付
    var date: String = ""
}