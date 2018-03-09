package com.watnow.campusdiary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_regulation.*
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

class RegulationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regulation)

        buttonArrowBack.setOnClickListener {
            finish()
        }

        var tmp: List<String>? = null
        val newLineTag: String = "***"
        try {
            val fileInput = assets.open("regulation.txt")
            val reader = BufferedReader(InputStreamReader(fileInput))
            tmp = reader.readLines()
        } catch (e: IOException) {
            Log.d("IOException", "ERROR LAUNCHING: ${e.toString()}")
        }

        var allText: String = ""
        tmp?.forEach {
            if (it == newLineTag) {
                allText += "\n\n"
            } else {
                allText += it
            }
        }

        regulationContent.text = allText
    }
}
