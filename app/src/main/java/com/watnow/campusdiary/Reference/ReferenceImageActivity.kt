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
        val content: String = intent.getStringExtra("content")
        // initializing Views
        notificationImage = findViewById(R.id.notificationImage)
        // ImageView
        val notification: String = "notification"
        val imageName: String = notification + when (content) {
            getString(R.string.Reference0) -> "Component.png"
            getString(R.string.Reference1) -> "Family.png"
            getString(R.string.Reference2) -> "Library.png"
            getString(R.string.Reference3) -> "Career.png"
            getString(R.string.Reference4) -> "Language.png"
            getString(R.string.Reference5) -> "General.png"
            getString(R.string.Reference6) -> "StudentLearning.png"
            getString(R.string.Reference7) -> "Activity.png"
            getString(R.string.Reference8),
            getString(R.string.Reference9),
            getString(R.string.Reference10) -> "LosingRainBike.png"
            getString(R.string.Reference11) -> "PC.png"
            getString(R.string.Reference12),
            getString(R.string.Reference13),
            getString(R.string.Reference14),
            getString(R.string.Reference15),
            getString(R.string.Reference16) -> "ManabiStation.png"
            getString(R.string.Reference17),
            getString(R.string.Reference18) -> "StudentsOffice.png"
            getString(R.string.Reference19),
            getString(R.string.Reference20) -> "StudentsOffice.png"
            getString(R.string.Reference21),
            getString(R.string.Reference22) -> "InjureHealth.png"
            getString(R.string.Reference23) -> "Future.png"
            getString(R.string.Reference24) -> "Disability.png"
            getString(R.string.Reference25) -> "Absent.png"
            getString(R.string.Reference26) -> "Absent.png"
            getString(R.string.Reference27) -> "TelNumbers.png"
            getString(R.string.Reference28) -> "AccessCampus.png"
            else -> Log.d("Position", "Else is selected")
        }
        Log.d("Position", imageName)
        val isStream: InputStream = resources.assets.open(imageName)
        val bitmap: Bitmap = BitmapFactory.decodeStream(isStream)
        notificationImage.setImageBitmap(null)
        notificationImage.setImageBitmap(bitmap)
    }

}
