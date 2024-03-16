package com.example.backgroundchecking

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.content.getSystemService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class BackGroundService : Service() {


    private var layout_flag=WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY

    var view:View?=null
    var windowManager: WindowManager?=null
    @SuppressLint("ResourceAsColor", "MissingInflatedId")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {



        val layoutParams =WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            450,
            layout_flag,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
            )
       view =LayoutInflater.from(this).inflate(R.layout.bg_layout,null)
        layoutParams.gravity=Gravity.BOTTOM
       windowManager =getSystemService(WINDOW_SERVICE) as WindowManager
//        view.setBackgroundColor(R.color.black)
        windowManager!!.addView(view,layoutParams)
        view!!.findViewById<ImageView>(R.id.clear).setOnClickListener{
            stopSelf()
        }
        view!!.findViewById<View>(R.id.layout).setOnClickListener {
         var intent =Intent(applicationContext,MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            stopSelf()
            startActivity(intent)
        }




        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        if(view !=null){
            windowManager!!.removeView(view)
        }

    }
}