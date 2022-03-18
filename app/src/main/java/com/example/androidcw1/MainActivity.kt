/**
 *
 * This class has contain the logic of the start window of the game
 *
 */
package com.example.androidcw1
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnAbout=findViewById<Button>(R.id.btnAbout)//referenced for the about button
        var btnNewGame=findViewById<Button>(R.id.btnNewGame)//referenced for the new game button

        btnAbout.setOnClickListener{
            openPopUpWindow()//open popup window if the about button clicked
        }
        btnNewGame.setOnClickListener{
            var gameWindow= Intent(this,GameActivity::class.java)//open game window if the new game button clicked
            startActivity(gameWindow)
        }
    }



    fun openPopUpWindow(){
        /**
         * moving logic for the popup window
         */
        var popupWindow= Intent(this,PopUpWindow::class.java)
        startActivity(popupWindow)
    }
}