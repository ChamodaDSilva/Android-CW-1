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

        var txtCorrectNum =findViewById<TextView>(R.id.txtCorrectNum)//ref for correct answer textview
        var textIncorrectNum =findViewById<TextView>(R.id.textIncorrectNum)//ref for incorrect answer textview
        var textQuestionsNum= findViewById<TextView>(R.id.textQuestionsNum)//ref for number of questions textview
        var textFeedback=findViewById<TextView>(R.id.textFeedback)//ref for feedback textview

        var intent=getIntent()
        var extras=intent.getExtras()//getting data from the intent

        var correctnum= extras!!.getInt("correct").toString()//getting values
        var incorrectnum= extras!!.getInt("incorrect").toString()

        txtCorrectNum.text =correctnum//setting values passed to the varibaes
        textIncorrectNum.text =incorrectnum
        textQuestionsNum.text=(correctnum.toInt()+incorrectnum.toInt()).toString()

        if(correctnum.toInt()<4){//feedback logic
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
        /**
         * to be on main window when back button clicked
         */
        super.onBackPressed()
        var mainWindow= Intent(this,MainActivity::class.java)
        startActivity(mainWindow)
        finish()
    }

}