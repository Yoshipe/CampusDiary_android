package com.watnow.campusdiary.Notification

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView

/**
 * Created by saitoushunsuke on 2018/02/15.
 */
data class NotificationListViewAdapter(private val context: Context, private val listView: ListView) {
    private val notificationLists: List<String> = listOf("立命館学園の構成",
                                                        "入学から始まる立命館ファミリー",
                                                        "図書館を利用したい",
                                                        "卒業後のキャリア形成を考えたい、就職活動について知りたい",
                                                        "異文化・外国語に触れる機会が欲しい",
                                                        "「教養科目」について知りたい",
                                                        "学生同士で学びあいたい",
                                                        "課外自主活動がしたい",
                                                        "落し物をした・落し物を拾った",
                                                        "雨なのに傘がない",
                                                        "自転車・バイクで通学したい",
                                                        "パソコン・情報機器の利用について相談したい",
                                                        "授業や成績のこと・休学・転籍などについて相談したい",
                                                        "引っ越して現住所が変わった",
                                                        "通学定期券を買いたい",
                                                        "学生証が盗難にあった、落としてしまった",
                                                        "学割が欲しい・在学証明書や成績証明書を発行したい・証書を発行したい",
                                                        "トラブルや困りごとがある",
                                                        "正課・課外の活動中に怪我をしてしまった",
                                                        "財布を失くした、盗難にあったなどの緊急事態で一時的にお金を借りたい",
                                                        "奨学金について相談したい・家計が急変して学費が払えない・自分にあった奨学金を探したい",
                                                        "ケガをした・体調が悪くなった",
                                                        "からだのことやこころの健康について相談したい",
                                                        "大学生活の目標が見つからない・友達づきあいがうまくいかない・将来のことが不安…などの悩みを相談したい",
                                                        "障害学生支援について相談したい",
                                                        "「台風が近づいている」「大雪で電車が運行停止になるかも」そんなとき授業がどうなるのかを知りたい",
                                                        "公欠・出席停止などの授業の欠席に関する取り扱いについて知りたい",
                                                        "窓口時間・電話番号",
                                                        "アクセスマップ")
    private val notificationListAdapter: ArrayAdapter<String> = ArrayAdapter(this.context, android.R.layout.simple_list_item_1, notificationLists)
    init {
        this.listView.adapter = notificationListAdapter
    }
}