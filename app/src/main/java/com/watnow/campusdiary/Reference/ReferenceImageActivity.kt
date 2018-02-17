package com.watnow.campusdiary.Reference

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.watnow.campusdiary.R
import java.io.InputStream

class ReferenceImageActivity : AppCompatActivity() {

    private val TAG: String = "ReferenceImageActivity"

    private lateinit var notificationImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reference_image)
        val intent: Intent = this.intent
        val position: Int = intent.getIntExtra("position", 0)
        // initializing Views
        notificationImage = findViewById(R.id.notificationImage)
        // ImageView
        val notification: String = "notification"
        val imageName: String = notification + when (position) {
            0 -> "Component.png"
            1 -> "Family.png"
            2 -> "Library.png"
            3 -> "Career.png"
            4 -> "Language.png"
            5 -> "General.png"
            6 -> "StudentLearning.png"
            7 -> "Activity.png"
            8, 9, 10 -> "LosingRainBike.png"
            11 -> "PC.png"
            12, 13, 14, 15, 16 -> "ManabiStation.png"
            17, 18 -> "StudentsOffice.png"
            19, 20 -> "StudentsOffice.png"
            21, 22 -> "InjureHealth.png"
            23 -> "Future.png"
            24 -> "Disability.png"
            25 -> "Absent.png"
            26 -> "Absent.png"
            27 -> "TelNumbers.png"
            28 -> "AccessCampus.png"
            else -> Log.d("Position", "Else is selected")
        }
        Log.d("Position", imageName)
        val isStream: InputStream = resources.assets.open(imageName)
        val bitmap: Bitmap = BitmapFactory.decodeStream(isStream)
        notificationImage.setImageBitmap(null)
        notificationImage.setImageBitmap(bitmap)
    }

}
