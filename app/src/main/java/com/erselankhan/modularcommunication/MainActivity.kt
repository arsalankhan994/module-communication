package com.erselankhan.modularcommunication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.erselankhan.modularcommunication.Constants.BROADCAST_FROM_THIRD_MODULE
import com.erselankhan.second_module.SecondModuleNavigation
import com.erselankhan.third_module.Constants

class MainActivity : AppCompatActivity(), SecondModuleNavigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerBroadcast()
    }

    override fun shareDataFromSecondModule(data: String) {
        toast(data)
    }

    private fun registerBroadcast() {
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadCastReceiver, IntentFilter(BROADCAST_FROM_THIRD_MODULE))
    }

    private fun unregisterBroadcast() {
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(broadCastReceiver)
    }

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                BROADCAST_FROM_THIRD_MODULE -> {
                    toast(intent.getStringExtra(Constants.DATA) ?: "")
                }
            }
        }
    }

    override fun onDestroy() {
        unregisterBroadcast()
        super.onDestroy()
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

object Constants {
    const val BROADCAST_FROM_THIRD_MODULE = "data_from_third_module"
}