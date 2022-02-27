package com.example.androidcw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity

class PopUpWindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_window)

        var dm=DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(dm)

        var width=dm.widthPixels
        var hight=dm.heightPixels

        window.setLayout((width*.7).toInt(),(hight*.5).toInt())
        var params=window.attributes
        params.gravity= Gravity.CENTER
        params.x=0
        params.y=-20
        window.attributes=params
    }
}