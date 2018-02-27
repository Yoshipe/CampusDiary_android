package com.watnow.campusdiary.TimeTable

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper
import io.realm.Realm
import kotlinx.android.synthetic.main.layout_time_table_contents.*

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class TimeTableActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "TimeTableActivity"

    private val ACTIVITY_NUM: Int = 2

    private val mContext: Context = this@TimeTableActivity

    lateinit var realm: Realm

    // 画面立ち上げ時に１回だけ実行する
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_table_activity)
        Log.d(TAG, "onCreate: starting")

        setupBottomNavigationView()
        // ボタン全てにクリック処理を書く (コールバックメソッドを使って同一処理)
        button1_1.setOnClickListener(this)
        button1_2.setOnClickListener(this)
        button1_3.setOnClickListener(this)
        button1_4.setOnClickListener(this)
        button1_5.setOnClickListener(this)
        button1_6.setOnClickListener(this)
        button1_7.setOnClickListener(this)
        button2_1.setOnClickListener(this)
        button2_2.setOnClickListener(this)
        button2_3.setOnClickListener(this)
        button2_4.setOnClickListener(this)
        button2_5.setOnClickListener(this)
        button2_6.setOnClickListener(this)
        button2_7.setOnClickListener(this)
        button3_1.setOnClickListener(this)
        button3_2.setOnClickListener(this)
        button3_3.setOnClickListener(this)
        button3_4.setOnClickListener(this)
        button3_5.setOnClickListener(this)
        button3_6.setOnClickListener(this)
        button3_7.setOnClickListener(this)
        button4_1.setOnClickListener(this)
        button4_2.setOnClickListener(this)
        button4_3.setOnClickListener(this)
        button4_4.setOnClickListener(this)
        button4_5.setOnClickListener(this)
        button4_6.setOnClickListener(this)
        button4_7.setOnClickListener(this)
        button5_1.setOnClickListener(this)
        button5_2.setOnClickListener(this)
        button5_3.setOnClickListener(this)
        button5_4.setOnClickListener(this)
        button5_5.setOnClickListener(this)
        button5_6.setOnClickListener(this)
        button5_7.setOnClickListener(this)
    }

    // 画面を表示する直前に実行する
    override fun onResume() {
        super.onResume()
        Log.d("onResume", ": Started")
        // Realmのインスタンス取得
        realm = Realm.getDefaultInstance()

        // DBより時間割を作成
        setButtonText()
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }

    /* *
    *  BottomNavigationView setup
    */
    private fun setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView")
        val bottomNavigationViewEx: BottomNavigationViewEx = findViewById(R.id.bottomNavViewBar)
        var bottomNavViewHelper: BottomNavigationViewHelper = BottomNavigationViewHelper()
        bottomNavViewHelper.setupBottomNavigationView(bottomNavigationViewEx)
        bottomNavViewHelper.enableNavigation(mContext, bottomNavigationViewEx)
        val menu: Menu = bottomNavigationViewEx.menu
        val menuItem: MenuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }

    override fun onClick(view: View?) {
        // 受け取ったビューをボタンにキャスト
        val button: Button = view as Button
        if (button.text.toString() == "") {
            addSubject(button)
        } else {
            changeSubject()
        }
    }


    private fun changeSubject() {
        Toast.makeText(this@TimeTableActivity, "changeSubjec is called", Toast.LENGTH_SHORT).show()
    }

    private fun addSubject(button: Button) {
        val customLayout = layoutInflater.inflate(R.layout.component_time_table_dialog, null)

        // inflateしたレイアウトから各ビューを紐付け
        val subject: EditText = customLayout.findViewById(R.id.subjectName)
        val classRoom: EditText = customLayout.findViewById(R.id.classRoom)

        // AlertDialogを生成する
        AlertDialog.Builder(this@TimeTableActivity).apply {
            setView(customLayout)
            setTitle("時間割登録")
            setPositiveButton("登録", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(this@TimeTableActivity, "登録", Toast.LENGTH_SHORT).show()
                // 登録ボタンを押したときのDBへの登録
                // realm開始
                realm.beginTransaction()
                val timeTableDB = realm.createObject(TimeTableDB::class.java)
                timeTableDB.strId = getString(button.id)
                timeTableDB.strSubject = subject.text.toString()
                timeTableDB.strClassRoom = classRoom.text.toString()
                // realm終了
                realm.commitTransaction()
                // 該当するボタンのテキストに登録
                setButtonText()
            })
            setNegativeButton("取り消し", null)
            show()
        }
    }

    private fun setButtonText() {
        val result = realm.where(TimeTableDB::class.java).findAll().sort("strId")
        result.forEach {
            // 全てのデータをみて、合致する場所にテキストをセットする
            when (it.strId) {
                getString(button1_1.id) -> button1_1.text = it.strSubject + "\n" + it.strClassRoom
                getString(button1_2.id) -> button1_2.text = it.strSubject + "\n" + it.strClassRoom
                getString(button1_3.id) -> button1_3.text = it.strSubject + "\n" + it.strClassRoom
                getString(button1_4.id) -> button1_4.text = it.strSubject + "\n" + it.strClassRoom
                getString(button1_5.id) -> button1_5.text = it.strSubject + "\n" + it.strClassRoom
                getString(button1_6.id) -> button1_6.text = it.strSubject + "\n" + it.strClassRoom
                getString(button1_7.id) -> button1_7.text = it.strSubject + "\n" + it.strClassRoom
                getString(button2_1.id) -> button2_1.text = it.strSubject + "\n" + it.strClassRoom
                getString(button2_2.id) -> button2_2.text = it.strSubject + "\n" + it.strClassRoom
                getString(button2_3.id) -> button2_3.text = it.strSubject + "\n" + it.strClassRoom
                getString(button2_4.id) -> button2_4.text = it.strSubject + "\n" + it.strClassRoom
                getString(button2_5.id) -> button2_5.text = it.strSubject + "\n" + it.strClassRoom
                getString(button2_6.id) -> button2_6.text = it.strSubject + "\n" + it.strClassRoom
                getString(button2_7.id) -> button2_7.text = it.strSubject + "\n" + it.strClassRoom
                getString(button3_1.id) -> button3_1.text = it.strSubject + "\n" + it.strClassRoom
                getString(button3_2.id) -> button3_2.text = it.strSubject + "\n" + it.strClassRoom
                getString(button3_3.id) -> button3_3.text = it.strSubject + "\n" + it.strClassRoom
                getString(button3_4.id) -> button3_4.text = it.strSubject + "\n" + it.strClassRoom
                getString(button3_5.id) -> button3_5.text = it.strSubject + "\n" + it.strClassRoom
                getString(button3_6.id) -> button3_6.text = it.strSubject + "\n" + it.strClassRoom
                getString(button3_7.id) -> button3_7.text = it.strSubject + "\n" + it.strClassRoom
                getString(button4_1.id) -> button4_1.text = it.strSubject + "\n" + it.strClassRoom
                getString(button4_2.id) -> button4_2.text = it.strSubject + "\n" + it.strClassRoom
                getString(button4_3.id) -> button4_3.text = it.strSubject + "\n" + it.strClassRoom
                getString(button4_4.id) -> button4_4.text = it.strSubject + "\n" + it.strClassRoom
                getString(button4_5.id) -> button4_5.text = it.strSubject + "\n" + it.strClassRoom
                getString(button4_6.id) -> button4_6.text = it.strSubject + "\n" + it.strClassRoom
                getString(button4_7.id) -> button4_7.text = it.strSubject + "\n" + it.strClassRoom
                getString(button5_1.id) -> button5_1.text = it.strSubject + "\n" + it.strClassRoom
                getString(button5_2.id) -> button5_2.text = it.strSubject + "\n" + it.strClassRoom
                getString(button5_3.id) -> button5_3.text = it.strSubject + "\n" + it.strClassRoom
                getString(button5_4.id) -> button5_4.text = it.strSubject + "\n" + it.strClassRoom
                getString(button5_5.id) -> button5_5.text = it.strSubject + "\n" + it.strClassRoom
                getString(button5_6.id) -> button5_6.text = it.strSubject + "\n" + it.strClassRoom
                getString(button5_7.id) -> button5_7.text = it.strSubject + "\n" + it.strClassRoom
                else -> Toast.makeText(this@TimeTableActivity, "ERROR REGISTERING", Toast.LENGTH_SHORT).show()
            }
        }
    }
}