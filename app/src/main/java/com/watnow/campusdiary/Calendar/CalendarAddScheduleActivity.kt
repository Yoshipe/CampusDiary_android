package com.watnow.campusdiary.Calendar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.watnow.campusdiary.R
import kotlinx.android.synthetic.main.activity_calendar_add_schedule.*

class CalendarAddScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_add_schedule)
        new_event_theme.setOnClickListener { object : View.OnClickListener{
            override fun onClick(p0: View?) {
            }
        }}
        new_event_cancel_btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                finish()
            }
        })
        new_event_save_btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@CalendarAddScheduleActivity, CalendarActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
