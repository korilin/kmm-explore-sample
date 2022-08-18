package com.korilin.kmm.explore.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.korilin.kmm.explore.Greeting
import android.widget.TextView

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
