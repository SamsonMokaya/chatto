package com.example.chatto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatto.databinding.ActivityVideoCallBinding
import com.google.firebase.auth.FirebaseAuth

class videoCallActivity : AppCompatActivity() {

    private lateinit var videoCallBinding: ActivityVideoCallBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_video_call)
        videoCallBinding = ActivityVideoCallBinding.inflate(layoutInflater)
        setContentView(videoCallBinding.root)
        supportActionBar?.hide()
    }
}