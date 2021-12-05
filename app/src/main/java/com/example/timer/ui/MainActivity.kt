package com.example.timer.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.timer.MainViewModel
import com.example.timer.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.Dispatchers




class MainActivity : AppCompatActivity() {

    private val mainVM by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_time)

        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        ).launch {
            mainVM.ticker.collect {
                textView.text = it
            }
        }

        findViewById<Button>(R.id.button_start).setOnClickListener {
            mainVM.start()
        }
        findViewById<Button>(R.id.button_pause).setOnClickListener {
            mainVM.pause()
        }
        findViewById<Button>(R.id.button_stop).setOnClickListener {
            mainVM.stop()
        }

    }
}











