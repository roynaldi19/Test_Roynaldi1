package com.roynaldi19.test_roynaldi1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.roynaldi19.test_roynaldi1.databinding.ActivityEditableBinding

class EditableActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditableBinding
    private lateinit var db: DatabaseReference
    private val TAG = "EditableActivity"
    private var id = 0

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

        db = FirebaseDatabase.getInstance().getReference("text")

        binding.btnSendToDatabase.setOnClickListener {
            sendToDatabase()
        }

    }

    private fun sendToDatabase() {
        val textToDataBase = binding.edtResult.text.toString()
        db.child(id.toString()).setValue(textToDataBase)
            .addOnCompleteListener {
                Toast.makeText(this@EditableActivity, "Text Send", Toast.LENGTH_SHORT).show()
                binding.edtResult.text.clear()
            }
            .addOnFailureListener {
                Toast.makeText(this@EditableActivity, "Failed Send", Toast.LENGTH_SHORT).show()

            }

    }
}