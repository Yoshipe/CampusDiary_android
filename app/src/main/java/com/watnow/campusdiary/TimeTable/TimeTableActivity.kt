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
import com.watnow.campusdiary.RealmDB.TimeTableDB
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
        // Realmのインスタンス取得
        realm = Realm.getDefaultInstance()

        // DBより時間割を作成
        setButtonText()
    }

    override fun onPause() {
        super.onPause()
        // Realmの終了
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
            // 授業の新規追加
            addSubject(button)
        } else {
            // 授業の修正
            changeSubject(button)
        }
    }


    private fun changeSubject(button: Button) {
        val customLayout = layoutInflater.inflate(R.layout.component_time_table_dialog, null)

        // inflateしたレイアウトから各ビューを紐付け
        val subject: EditText = customLayout.findViewById(R.id.subjectName)
        val classRoom: EditText = customLayout.findViewById(R.id.classRoom)

        // 登録済みの情報をEditTextにセット
        val result = realm.where(TimeTableDB::class.java).equalTo("intId", button.id).findAll()
        val selectedDB = result[0]
        subject.setText(selectedDB.strSubject)
        classRoom.setText(selectedDB.strClassRoom)

        // AlertDialogを生成する
        AlertDialog.Builder(this@TimeTableActivity).apply {
            setView(customLayout)
            setTitle("時間割登録")
            setPositiveButton("登録", DialogInterface.OnClickListener { _, _ ->
                // 修正後、登録ボタンを押したとき
                // realm開始
                realm.beginTransaction()
                selectedDB.strSubject = subject.text.toString()
                selectedDB.strClassRoom = classRoom.text.toString()
                // realm終了
                realm.commitTransaction()
                // 該当するボタンのテキストに登録
                setButtonText()
                Toast.makeText(this@TimeTableActivity, "修正完了", Toast.LENGTH_SHORT).show()
            })
            setNegativeButton("取り消し", null)
            show()
        }
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
            setPositiveButton("登録", DialogInterface.OnClickListener { _, _ ->
                // 登録ボタンを押したときのDBへの登録
                // realm開始
                realm.beginTransaction()
                val timeTableDB = realm.createObject(TimeTableDB::class.java)
                timeTableDB.intId = button.id
                timeTableDB.strSubject = subject.text.toString()
                timeTableDB.strClassRoom = classRoom.text.toString()
                // realm終了
                realm.commitTransaction()
                // 該当するボタンのテキストに登録
                setButtonText()
                Toast.makeText(this@TimeTableActivity, "登録完了", Toast.LENGTH_SHORT).show()
            })
            setNegativeButton("取り消し", null)
            show()
        }
    }

    private fun setButtonText() {
        val result = realm.where(TimeTableDB::class.java).findAll().sort("intId")
        result.forEach {
            // 全てのデータをみて、合致する場所にテキストをセットする
            when (it.intId) {
                button1_1.id -> {
                    subjectName1_1.text = it.strSubject
                    classRoom1_1.text = it.strClassRoom
                }
                button1_2.id -> {
                    subjectName1_2.text = it.strSubject
                    classRoom1_2.text = it.strClassRoom
                }
                button1_3.id -> {
                    subjectName1_3.text = it.strSubject
                    classRoom1_3.text = it.strClassRoom
                }
                button1_4.id -> {
                    subjectName1_4.text = it.strSubject
                    classRoom1_4.text = it.strClassRoom
                }
                button1_5.id -> {
                    subjectName1_5.text = it.strSubject
                    classRoom1_5.text = it.strClassRoom
                }
                button1_6.id -> {
                    subjectName1_6.text = it.strSubject
                    classRoom1_6.text = it.strClassRoom
                }
                button1_7.id -> {
                    subjectName1_7.text = it.strSubject
                    classRoom1_7.text = it.strClassRoom
                }
                button2_1.id -> {
                    subjectName2_1.text = it.strSubject
                    classRoom2_1.text = it.strClassRoom
                }
                button2_2.id -> {
                    subjectName2_2.text = it.strSubject
                    classRoom2_2.text = it.strClassRoom
                }
                button2_3.id -> {
                    subjectName2_3.text = it.strSubject
                    classRoom2_3.text = it.strClassRoom
                }
                button2_4.id -> {
                    subjectName2_4.text = it.strSubject
                    classRoom2_4.text = it.strClassRoom
                }
                button2_5.id -> {
                    subjectName2_5.text = it.strSubject
                    classRoom2_5.text = it.strClassRoom
                }
                button2_6.id -> {
                    subjectName2_6.text = it.strSubject
                    classRoom2_6.text = it.strClassRoom
                }
                button2_7.id -> {
                    subjectName2_7.text = it.strSubject
                    classRoom2_7.text = it.strClassRoom
                }
                button3_1.id -> {
                    subjectName3_1.text = it.strSubject
                    classRoom3_1.text = it.strClassRoom
                }
                button3_2.id -> {
                    subjectName3_2.text = it.strSubject
                    classRoom3_2.text = it.strClassRoom
                }
                button3_3.id -> {
                    subjectName3_3.text = it.strSubject
                    classRoom3_3.text = it.strClassRoom
                }
                button3_4.id -> {
                    subjectName3_4.text = it.strSubject
                    classRoom3_4.text = it.strClassRoom
                }
                button3_5.id -> {
                    subjectName3_5.text = it.strSubject
                    classRoom3_5.text = it.strClassRoom
                }
                button3_6.id -> {
                    subjectName3_6.text = it.strSubject
                    classRoom3_6.text = it.strClassRoom
                }
                button3_7.id -> {
                    subjectName3_7.text = it.strSubject
                    classRoom3_7.text = it.strClassRoom
                }
                button4_1.id -> {
                    subjectName4_1.text = it.strSubject
                    classRoom4_1.text = it.strClassRoom
                }
                button4_2.id -> {
                    subjectName4_2.text = it.strSubject
                    classRoom4_2.text = it.strClassRoom
                }
                button4_3.id -> {
                    subjectName4_3.text = it.strSubject
                    classRoom4_3.text = it.strClassRoom
                }
                button4_4.id -> {
                    subjectName4_4.text = it.strSubject
                    classRoom4_4.text = it.strClassRoom
                }
                button4_5.id -> {
                    subjectName4_5.text = it.strSubject
                    classRoom4_5.text = it.strClassRoom
                }
                button4_6.id -> {
                    subjectName4_6.text = it.strSubject
                    classRoom4_6.text = it.strClassRoom
                }
                button4_7.id -> {
                    subjectName4_7.text = it.strSubject
                    classRoom4_7.text = it.strClassRoom
                }
                button5_1.id -> {
                    subjectName5_1.text = it.strSubject
                    classRoom5_1.text = it.strClassRoom
                }
                button5_2.id -> {
                    subjectName5_2.text = it.strSubject
                    classRoom5_2.text = it.strClassRoom
                }
                button5_3.id -> {
                    subjectName5_3.text = it.strSubject
                    classRoom5_3.text = it.strClassRoom
                }
                button5_4.id -> {
                    subjectName5_4.text = it.strSubject
                    classRoom5_4.text = it.strClassRoom
                }
                button5_5.id -> {
                    subjectName5_5.text = it.strSubject
                    classRoom5_5.text = it.strClassRoom
                }
                button5_6.id -> {
                    subjectName5_6.text = it.strSubject
                    classRoom5_6.text = it.strClassRoom
                }
                button5_7.id -> {
                    subjectName5_7.text = it.strSubject
                    classRoom5_7.text = it.strClassRoom
                }
                else -> Toast.makeText(this@TimeTableActivity, "ERROR REGISTERING", Toast.LENGTH_SHORT).show()
            }
        }
    }
}