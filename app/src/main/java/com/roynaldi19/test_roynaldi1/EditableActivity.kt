package com.roynaldi19.test_roynaldi1

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.roynaldi19.test_roynaldi1.databinding.ActivityEditableBinding

class EditableActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditableBinding
    private val TAG = "EditableActivity"

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textReceived = intent.getStringExtra(EXTRA_TEXT)
        Log.i(TAG, "edtResult $textReceived")

        binding.edtResult.setText(textReceived)

    }
}