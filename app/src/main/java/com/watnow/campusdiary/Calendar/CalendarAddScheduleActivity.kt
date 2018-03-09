package com.watnow.campusdiary.Calendar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.*
import com.watnow.campusdiary.R
import kotlinx.android.synthetic.main.activity_calendar_add_schedule.*

class CalendarAddScheduleActivity : AppCompatActivity() {

    var colorId: Int = R.color.ruby

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_add_schedule)
        new_event_theme.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.layout_calendar_add_alert_dialog, null)
            val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
            val dialog = AlertDialog.Builder(this@CalendarAddScheduleActivity).apply {
                setView(view)
                setPositiveButton("OK") {dialogInterface, i ->
                    val selectedButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                    colorId = getSelectedColor(selectedButton)
                    buttonColorSelect.setBackgroundResource(colorId)
                    new_event_navigation.setBackgroundResource(colorId)
                    new_event_title.setBackgroundResource(colorId)
                }
                show()
            }
        }
        new_event_cancel_btn.setOnClickListener {
            finish()
        }
        new_event_save_btn.setOnClickListener {
            // Todo Realmに保存
            finish()
            Toast.makeText(this@CalendarAddScheduleActivity, "登録しました", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSelectedColor(selectedButton: RadioButton): Int {
        var selectedColorId: Int = 0
        when (selectedButton.id) {
            R.id.radioDiamond -> selectedColorId = R.color.diamond
            R.id.radioPerl -> selectedColorId = R.color.perl
            R.id.radioRuby -> selectedColorId = R.color.ruby
            R.id.radioSapphire -> selectedColorId = R.color.sapphire
            R.id.radioTopaz -> selectedColorId = R.color.topaz
            R.id.radioEmerald -> selectedColorId = R.color.emerald
            R.id.radioGold -> selectedColorId = R.color.gold
            R.id.radioAmethyst -> selectedColorId = R.color.amethyst
            R.id.radioTigerEye -> selectedColorId = R.color.tigerEye
        }
        return selectedColorId
    }
}
