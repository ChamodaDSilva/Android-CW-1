package com.example.androidcw1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.schedule

class GameActivity : AppCompatActivity() {
    var counter = 50//counter time
    var numberOfCorrectAnswers=0
    var numberOfIncorrectAnswers=0
    //variables use both expressions
    var expressionAnswer=0.0
    var expression=""

    var answer1=0.0
    var answer2=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        startTimeCounter()

        var txtExpression1=findViewById<TextView>(R.id.txtExpression1)//left expression
        var txtExpression2=findViewById<TextView>(R.id.txtExpression2)//right expression
        var txtCorrectness=findViewById<TextView>(R.id.txtCorrectness)//correct or incorrect indicate

        var btnGreater=findViewById<Button>(R.id.btnGreater)//buttons
        var btnEqual=findViewById<Button>(R.id.btnEqual)
        var btnLess=findViewById<Button>(R.id.btnLess)




        startGame(txtExpression1,txtExpression2)//starting game first time


        btnGreater.setOnClickListener{
            if (answer1>answer2){
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#caffbf"))
                numberOfCorrectAnswers++
                bonusTime()
            }else{
                txtCorrectness.text="Incorrect"
                txtCorrectness.setTextColor(Color.parseColor("#ffc09f"))
                numberOfIncorrectAnswers++
            }
            Handler().postDelayed({
                txtCorrectness.text=""
                startGame(txtExpression1,txtExpression2)

            }, 1500)
        }
        btnEqual.setOnClickListener{
            if (answer1==answer2){
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#caffbf"))
                numberOfCorrectAnswers++
                bonusTime()
            }else{
                txtCorrectness.text="Incorrect"
                txtCorrectness.setTextColor(Color.parseColor("#ffc09f"))
                numberOfIncorrectAnswers++
            }
            Handler().postDelayed({
                txtCorrectness.text=""
                startGame(txtExpression1,txtExpression2)

            }, 1500)
        }
        btnLess.setOnClickListener{
            if (answer1<answer2){
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#caffbf"))
                numberOfCorrectAnswers++
                bonusTime()
            }else{
                txtCorrectness.text="Incorrect"
                txtCorrectness.setTextColor(Color.parseColor("#ffc09f"))
                numberOfIncorrectAnswers++
            }
            Handler().postDelayed({
                txtCorrectness.text=""
                startGame(txtExpression1,txtExpression2)

            }, 1500)
        }



    }

    fun startGame(txtException1: TextView,txtException2: TextView){
        val rand = Random()
        val numOfOperations1 = rand.nextInt(4) + 1
        val numOfOperations2 = rand.nextInt(4) + 1
        val firstNum1 = rand.nextInt(20) + 1
        val firstNum2 = rand.nextInt(20) + 1


        //generating first expression
        expressionAnswer = firstNum1.toDouble()
        genarate(firstNum1.toString(), numOfOperations1)
        answer1=expressionAnswer
        txtException1.text=expression+",\n$answer1"

        //generating second expression
        expression=""
        expressionAnswer=firstNum2.toDouble()
        genarate(firstNum2.toString(), numOfOperations2)
        answer2=expressionAnswer
        txtException2.text=expression+",\n$answer2"
    }
    fun genarate(firstNum:String,numOfOperations: Int){
        var operations= mutableListOf<String>("+","-","*","/")
        var rand= Random()

        var operationIndex=rand.nextInt(4)
        var secondNum=rand.nextInt(20)+1

        while (!inRange(expressionAnswer,secondNum.toDouble(), operations[operationIndex])) {//making answer always in range 100 to 0
            operationIndex=rand.nextInt(4)
            secondNum=rand.nextInt(20)+1
        }
        //about the answer of arithmetic operation
        if (operationIndex == 0) {
            expressionAnswer = expressionAnswer + secondNum;
        } else if (operationIndex == 1) {
            expressionAnswer = expressionAnswer - secondNum;
        } else if (operationIndex == 2) {
            expressionAnswer = expressionAnswer * secondNum;
        } else if (operationIndex == 3) {//for division
            //to get integers only
            while (!isWhole(expressionAnswer / secondNum)) {
                secondNum = rand.nextInt(20) + 1
            }
            expressionAnswer = expressionAnswer / secondNum;
            //
        }
        //////

        var term=firstNum+operations[operationIndex]+secondNum
        expression=term

        var newNumOfOperations=numOfOperations-1
        if(newNumOfOperations>0){
            genarate("($term)",newNumOfOperations);
        }

    }
    fun isWhole(value: Double):Boolean {
        //to check number is a whole number
        return value - value.toInt() == 0.0
    }
    fun inRange(value1: Double, value2: Double, operation:String): Boolean {
        //to check a operation is an range of 0 to 100 including 0 and 100
        if(operation=="+"){
           return value1+value2 <= 100.0 && value1+value2 >=0.0
        }else if(operation=="-"){
            return value1-value2 <= 100.0 && value1-value2 >=0.0
        }else if(operation=="*"){
            return value1*value2 <= 100.0 && value1*value2 >=0.0
        }else if(operation=="/"){
            return value1/value2 <= 100.0 && value1/value2 >=0.0
        }else{
            return false
        }
    }
    fun startTimeCounter() {
        var countTime: TextView = findViewById(R.id.txtTimer)
        var scoreWindow= Intent(this,ScoreActivity::class.java)
        object : CountDownTimer(1000000, 1000) {//50000 should be

            override fun onTick(millisUntilFinished: Long) {
                countTime.text = counter.toString()
                if (counter<=0){
                    cancel()
                    onFinish()
                }
                counter--
            }
            override fun onFinish() {
                var extras = Bundle()
                extras.putInt("correct",numberOfCorrectAnswers)
                extras.putInt("incorrect",numberOfIncorrectAnswers)
                scoreWindow.putExtras(extras)
                startActivity(scoreWindow)
            }

        }.start()
    }
    fun bonusTime(){
        if(numberOfCorrectAnswers%5==0){
            var alertBonus=Toast.makeText(applicationContext,"10 seconds added",Toast.LENGTH_SHORT)
            alertBonus.show()
            counter+=10
        }
    }
}