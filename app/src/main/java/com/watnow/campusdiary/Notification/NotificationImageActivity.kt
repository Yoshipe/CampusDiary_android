package com.watnow.campusdiary.Notification

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.watnow.campusdiary.R
import java.io.InputStream

class NotificationImageActivity : AppCompatActivity() {

    private val TAG: String = "NotificationImageActivity"

    private lateinit var notificationImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_image)
        val intent: Intent = this.intent
        val position: Int = intent.getIntExtra("position", 0)
        // initializing Views
        notificationImage = findViewById(R.id.notificationImage)
        // ImageView
        val notification: String = "notification"
        val imageName: String = notification + when (position) {
            0 -> "Absent.png"
            1 -> "Absent.png"
            2 -> "Absent.png"
            3 -> "Career.png"
            4 -> "Language.png"
            5 -> "General.png"
            6 -> "Activity.png"
            7 -> "Activity.png"
            8, 9, 10 -> "LosingRainBike.png"
            11 -> "PC.png"
            12 -> "Absent.png"
            13 -> "Absent.png"
            14 -> "Absent.png"
            15 -> "Absent.png"
            16 -> "Absent.png"
            17 -> "Future.png"
            18 -> "InjureHealth.png"
            19 -> "Absent.png"
            20 -> "Absent.png"
            21 -> "InjureHealth.png"
            22 -> "InjureHealth.png"
            23 -> "Future.png"
            24 -> "Absent.png"
            25 -> "Absent.png"
            26 -> "Absent.png"
            27 -> "Absent.png"
            28 -> "Absent.png"
            else -> Log.d("Position", "Else is selected")
        }
        Log.d("Position", imageName)
        val isStream: InputStream = resources.assets.open(imageName)
        val bitmap: Bitmap = BitmapFactory.decodeStream(isStream)
        notificationImage.setImageBitmap(null)
        notificationImage.setImageBitmap(bitmap)
    }

}
