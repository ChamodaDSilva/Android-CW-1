/**
 *
 * This class is for set the popup window width height and statring position
 *
 */
package com.example.androidcw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity

class PopUpWindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_window)//set the popup window layout

        var dm=DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(dm)//added metrics of the window the emulator screen got

        var width=dm.widthPixels//window width get
        var hight=dm.heightPixels//window height get

        window.setLayout((width*.7).toInt(),(hight*.5).toInt())
        var params=window.attributes
        params.gravity= Gravity.CENTER
        params.x=0
        params.y=-20
        window.attributes=params
    }
}