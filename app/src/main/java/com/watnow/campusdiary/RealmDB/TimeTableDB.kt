package com.watnow.campusdiary.TimeTable

import io.realm.RealmObject

/**
 * Created by saitoushunsuke on 2018/02/28.
 */
open class TimeTableDB: RealmObject() {
    /* データベースのパラメータ */
    // どの時間に対応するかのID
    var intId: Int = 0
    // 教科名を保存するプロパティ
    var strSubject: String = ""
    // 教室名を保存するプロパティ
    var strClassRoom: String = ""
}