package com.watnow.campusdiary.calendar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.*
import com.watnow.campusdiary.R
import com.watnow.campusdiary.realmdb.CalendarDB
import com.watnow.campusdiary.utils.Constant
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_calendar_add_schedule.*
import kotlinx.android.synthetic.main.layout_calendar_add_alert_dialog.view.*

class CalendarAddScheduleActivity : AppCompatActivity() {
    lateinit var realm: Realm
    private var date: String = ""

    private var currentTitle: String = ""
    private var currentTheme: String = ""
    private var currentDetail: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_add_schedule)
        date = intent.extras.getString(Constant.INTENT_KEY_DATE.name)
        val updateDateNum: Int = intent.extras.getInt("listPosition")
        val isUpdate: Boolean = intent.hasExtra("listPosition")
        if (isUpdate) {
            setupInfo(updateDateNum)
        }

        new_event_theme.setOnClickListener {
            val customView = createCustomView(isUpdate, updateDateNum)
            val dialog = AlertDialog.Builder(this@CalendarAddScheduleActivity).apply {
                setView(customView)
                setPositiveButton("OK") { _, _ ->
                    val selectedButton: RadioButton = customView.findViewById(customView.radioGroup.checkedRadioButtonId)
                    updateUiColor(selectedButton)
                }
                show()
            }
        }

        discard_btn.setOnClickListener{
            val dialog = AlertDialog.Builder(this@CalendarAddScheduleActivity).apply {
                setMessage("この予定を削除しますか？")
                setPositiveButton("削除") { _, _ ->
                    discardDate(updateDateNum)
                    finish()
                    Toast.makeText(this@CalendarAddScheduleActivity, "削除しました", Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("キャンセル",null)
                show()
            }
        }

        new_event_cancel_btn.setOnClickListener {
            finish()
        }

        new_event_save_btn.setOnClickListener {
            currentTitle = new_event_title.text.toString()
            // currentThemeだけupdateUiColorで文字列を取得している
            // 文字列の取得にradioButtonがいるため、updateUiColorメソッドの引数より参照した
            currentDetail = new_event_detail.text.toString()
            realm.beginTransaction()
            val targetDB: CalendarDB
            targetDB = if (isUpdate) {
                val updateDate: String = intent.extras.getString("date")
                realm.where(CalendarDB::class.java).equalTo("date", updateDate).findAll()[updateDateNum]
            } else {
                realm.createObject(CalendarDB::class.java)
            }
            if (currentTitle == "") {
                realm.cancelTransaction()
                Toast.makeText(this@CalendarAddScheduleActivity, "タイトルが未入力です", Toast.LENGTH_SHORT).show()
            } else {
                targetDB.title = currentTitle
                targetDB.theme = currentTheme
                targetDB.detail = currentDetail
                targetDB.date = date
                realm.commitTransaction()
                finish()
                Toast.makeText(this@CalendarAddScheduleActivity, "登録しました", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        realm = Realm.getDefaultInstance()
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }

    private fun createCustomView(isUpdate: Boolean, updateDateNum: Int): View {
        val view = View.inflate(this, R.layout.layout_calendar_add_alert_dialog, null)
        if (currentTheme != "")
            view.radioGroup.check(convertToRadioButtonId(currentTheme))
        return view
    }

    private fun discardDate(i:Int) {
        realm.beginTransaction()
        val targetDB: CalendarDB
        val updateDate: String = intent.extras.getString("date")
        targetDB = realm.where(CalendarDB::class.java).equalTo("date", updateDate).findAll()[i]
        targetDB.deleteFromRealm()
        realm.commitTransaction()
    }

    private fun updateUiColor(selectedButton: RadioButton) {
        val colorId: Int = getColorFromString(selectedButton.text.toString())
        button_color_select.setBackgroundColor(colorId)
        new_event_navigation.setBackgroundColor(colorId)
        new_event_title.setBackgroundColor(colorId)
        discard_btn.setBackgroundColor(colorId)
        currentTheme = selectedButton.text.toString()
    }

    private fun setupInfo(updateDateNum: Int) {
        val updateDate: String = intent.extras.getString("date")
        realm = Realm.getDefaultInstance()
        val updateInfo = realm.where(CalendarDB::class.java).equalTo("date",updateDate).findAll()[updateDateNum]
        new_event_title.setText(updateInfo.title)
        button_color_select.setBackgroundColor(getColorFromString(updateInfo.theme))
        new_event_navigation.setBackgroundColor(getColorFromString(updateInfo.theme))
        new_event_title.setBackgroundColor(getColorFromString(updateInfo.theme))
        discard_btn.setBackgroundColor(getColorFromString(updateInfo.theme))
        new_event_detail.setText(updateInfo.detail)
        discard_btn.visibility = View.VISIBLE
        currentTheme = updateInfo.theme
    }

    private fun getColorFromString(colorName: String): Int {
        var selectedColorId = 0
        when (colorName) {
            getString(R.string.ruby) -> selectedColorId = ContextCompat.getColor(this, R.color.ruby)
            getString(R.string.sapphire) -> selectedColorId = ContextCompat.getColor(this, R.color.sapphire)
            getString(R.string.emerald) -> selectedColorId = ContextCompat.getColor(this, R.color.emerald)
            getString(R.string.gold) -> selectedColorId = ContextCompat.getColor(this, R.color.gold)
            getString(R.string.perl) -> selectedColorId = ContextCompat.getColor(this, R.color.perl)
            getString(R.string.amethyst) -> selectedColorId = ContextCompat.getColor(this, R.color.amethyst)
            getString(R.string.tigerEye) -> selectedColorId = ContextCompat.getColor(this, R.color.tigerEye)
            getString(R.string.topaz) -> selectedColorId = ContextCompat.getColor(this, R.color.topaz)
            getString(R.string.diamond) -> selectedColorId = ContextCompat.getColor(this, R.color.diamond)
        }
        return selectedColorId
    }
    private fun convertToRadioButtonId(colorName: String): Int {
        var radioButtonId = 0
        radioButtonId = when (colorName) {
            getString(R.string.ruby) -> R.id.radioRuby
            getString(R.string.sapphire) -> R.id.radioSapphire
            getString(R.string.emerald) -> R.id.radioEmerald
            getString(R.string.gold) -> R.id.radioGold
            getString(R.string.perl) -> R.id.radioPerl
            getString(R.string.amethyst) -> R.id.radioAmethyst
            getString(R.string.tigerEye) -> R.id.radioTigerEye
            getString(R.string.topaz) -> R.id.radioTopaz
            getString(R.string.diamond) -> R.id.radioDiamond
            else -> R.id.radioDiamond
        }
        return radioButtonId
    }

}
