/**
 *
 * This class for logic of showing scores of the game that player got
 */
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
        var textQuestionsNum= findViewById<TextView>(R.id.textQuestionsNum)
        var textFeedback=findViewById<TextView>(R.id.textFeedback)

        var intent=getIntent()
        var extras=intent.getExtras()

        var correctnum= extras!!.getInt("correct").toString()
        var incorrectnum= extras!!.getInt("incorrect").toString()

        txtCorrectNum.text =correctnum
        textIncorrectNum.text =incorrectnum
        textQuestionsNum.text=(correctnum.toInt()+incorrectnum.toInt()).toString()

        if(correctnum.toInt()<4){
            textFeedback.text="Try to do more correct answers!"
        }else if (incorrectnum.toInt()>4){
            textFeedback.text="To much incorrect answers!"
        }else if(incorrectnum.toInt()+correctnum.toInt()<5){
            textFeedback.text="Try to do more questions answers!"
        }else{
            textFeedback.text="Good progress!"
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var mainWindow= Intent(this,MainActivity::class.java)
        startActivity(mainWindow)
        finish()
    }

}