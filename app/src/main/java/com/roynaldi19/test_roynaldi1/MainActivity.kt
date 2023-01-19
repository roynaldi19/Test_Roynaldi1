package com.roynaldi19.test_roynaldi1

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextRecognizer
import com.roynaldi19.test_roynaldi1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var IMAGE_URI: Uri? = null
    var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTakePicture.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .start()
        }

        binding.btnEdit.setOnClickListener {
            copyToSecondActivity(binding.tvResult.text.toString())

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE && data != null) {
            IMAGE_URI = data.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, IMAGE_URI)
            getTextFromImage(bitmap!!)

        } else if (requestCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this@MainActivity, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this@MainActivity, "Take Image Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getTextFromImage(bitmap: Bitmap) {
        val textRecognizer = TextRecognizer.Builder(this@MainActivity).build()
        if (textRecognizer.isOperational){
            val frame = Frame.Builder().setBitmap(bitmap).build()
            val textBlockSparseArray = textRecognizer.detect(frame)
            val stringBuilder = StringBuilder()
            for (i in 0 until textBlockSparseArray.size()){
                val textBlock = textBlockSparseArray.valueAt(i)
                stringBuilder.append(textBlock.value)
                stringBuilder.append("\n")
            }
            binding.tvResult.setText(stringBuilder.toString())


        } else {
            Toast.makeText(this@MainActivity, "Failed load Text", Toast.LENGTH_SHORT).show()
        }
    }

    private fun copyToSecondActivity(resultText: String) {
        val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("copied Text", resultText)
        clipBoard.setPrimaryClip(clip)
        val intent = Intent(this@MainActivity, EditableActivity::class.java)
        intent.putExtra(EditableActivity.EXTRA_TEXT, resultText)
        startActivity(intent)
    }
}