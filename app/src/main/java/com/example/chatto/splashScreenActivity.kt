package com.example.chatto

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.chatto.databinding.ActivitySplashScreenBinding
import kotlin.system.exitProcess

class splashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if(isConnected() == false){
            Toast.makeText(this,"Please ensure you are on an Internet connection", Toast.LENGTH_SHORT).show()
            var builder = AlertDialog.Builder(applicationContext)
            builder.setTitle("No internet connection")
            builder.setMessage("Do you want to exit")

//            builder.setPositiveButton("Yes"){dialogInterface, which->

            builder.setPositiveButton("Yes"){_, _->
                finish()
                exitProcess(0)
            }

            builder.setNegativeButton("Check Wifi"){ _, _->
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                finish()
                exitProcess(0)
            }

            var alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }else{

            Handler(Looper.myLooper()!!).postDelayed(kotlinx.coroutines.Runnable {
                val intent = Intent(this, signUpActivity::class.java)
                finish()
                startActivity(intent)
            },4000)

        }



    }

    fun isConnected(): Boolean{
        var connectivityManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var network: NetworkInfo? = connectivityManager.activeNetworkInfo

        if(network != null){
            if(network.isConnected){
                return true
            }
        }
        return false
    }
}