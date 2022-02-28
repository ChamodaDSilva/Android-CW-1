package com.example.androidcw1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        var txtCorrectNum =findViewById<TextView>(R.id.txtCorrectNum)
        var textIncorrectNum =findViewById<TextView>(R.id.textIncorrectNum)

        var intent=getIntent()
        var extras=intent.getExtras()

        var correctnum= extras!!.getInt("correct").toString()
        var incorrectnum= extras!!.getInt("incorrect").toString()

        txtCorrectNum.text ="correct - "+correctnum
        textIncorrectNum.text = "incorrect- "+incorrectnum
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var mainWindow= Intent(this,MainActivity::class.java)
        startActivity(mainWindow)
        finish()
    }
}