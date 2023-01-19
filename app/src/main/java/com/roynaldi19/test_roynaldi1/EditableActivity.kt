package com.roynaldi19.test_roynaldi1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EditableActivity : AppCompatActivity() {
    private val TAG = "EditableActivity"

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editable)

        val textEditable: TextView = findViewById(R.id.edt_result)

        val textReceived = intent.getStringExtra(EXTRA_TEXT)
        Log.i(TAG, "hasilClip $clip")

        val text = "Result : $textReceived"
        textEditable.text = text


    }
}