package com.example.androidcw1
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnAbout=findViewById<Button>(R.id.btnAbout)
        var btnNewGame=findViewById<Button>(R.id.btnNewGame)

        btnAbout.setOnClickListener{
            openPopUpWindow()
        }
        btnNewGame.setOnClickListener{
            var gameWindow= Intent(this,GameActivity::class.java)
            startActivity(gameWindow)
        }
    }

    fun openPopUpWindow(){
        var popupWindow= Intent(this,PopUpWindow::class.java)
        startActivity(popupWindow)
    }
}