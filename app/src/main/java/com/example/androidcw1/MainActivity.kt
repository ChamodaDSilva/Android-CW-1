package com.example.androidcw1

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.PopupWindow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnAbout=findViewById<Button>(R.id.btnAbout)
        var btnNewGame=findViewById<Button>(R.id.btnNewGame)

        btnAbout.setOnClickListener{
//            var mDialog=Dialog(this)
//            mDialog.setContentView(R.layout.popup)
//            //mDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
/////            mDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            openPopUpWindow()

        }
    }

    fun openPopUpWindow(){
        var popupWindow= Intent(this,PopUpWindow::class.java)
        startActivity(popupWindow)
    }
}