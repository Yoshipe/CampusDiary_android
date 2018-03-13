package com.watnow.campusdiary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_regulation.*
import java.io.BufferedReader
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
        val newLineTag = "***"
        try {
            val fileInput = assets.open("regulation.txt")
            val reader = BufferedReader(InputStreamReader(fileInput))
            tmp = reader.readLines()
        } catch (e: IOException) {
            Log.d("IOException", "ERROR LAUNCHING: $e")
        }

        var allText = ""
        tmp?.forEach {
            allText += if (it == newLineTag) {
                "\n\n"
            } else {
                it
            }
        }

        regulationContent.text = allText
    }
}
