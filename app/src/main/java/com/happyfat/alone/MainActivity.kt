package com.happyfat.alone

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var selfContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selfContext = this
        val hiBtn : Button = findViewById(R.id.hi_btn)
        hiBtn.setOnClickListener {
            Toast.makeText(selfContext, "hi", Toast.LENGTH_SHORT).show()
        }
    }
}