/**
 *
 * This class for playing game activity which the user see the playing game window
 *
 */
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

class GameActivity : AppCompatActivity() {
    var counter = 50//counter time at the start
    var numberOfCorrectAnswers=0//number of correct answers
    var numberOfIncorrectAnswers=0//number of incorrect answers
    //variables use both expressions
    var expressionAnswer=0.0//this variable to keep temporarily the expression answer
    var expression=""//this variable to keep temporarily the expression

    var expression1=""//left side expression
    var expression2=""//right side expression

    var answer1=0.0//left side answer
    var answer2=0.0//right side answer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        if(savedInstanceState!=null){
            savedInstanceState.getInt("counter")//recover the timer if the programme in resume
        }

        startTimeCounter()//starting the counter at the start

        var txtExpression1=findViewById<TextView>(R.id.txtExpression1)//ref left expression
        var txtExpression2=findViewById<TextView>(R.id.txtExpression2)//ref right expression
        var txtCorrectness=findViewById<TextView>(R.id.txtCorrectness)//ref correct or incorrect indicate

        var btnGreater=findViewById<Button>(R.id.btnGreater)//ref button greater
        var btnEqual=findViewById<Button>(R.id.btnEqual)//ref button equal
        var btnLess=findViewById<Button>(R.id.btnLess)//ref button less

        startGame(txtExpression1,txtExpression2)//generate the expressions first time opening the game window

        btnGreater.setOnClickListener{
            if (answer1>answer2){//if the answer is correct change show it with in unique colors red and green with correct and incorrect
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#41cf41"))
                numberOfCorrectAnswers++
                bonusTime()
            }else{
                txtCorrectness.text="Wrong"
                txtCorrectness.setTextColor(Color.parseColor("#ffc09f"))
                numberOfIncorrectAnswers++
            }
            Handler().postDelayed({//added 1.5 s delay for next question
                txtCorrectness.text=""
                startGame(txtExpression1,txtExpression2)

            }, 1500)
        }
        btnEqual.setOnClickListener{
            if (answer1==answer2){//if the answer is correct change show it with in unique colors red and green with correct and incorrect
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#41cf41"))
                numberOfCorrectAnswers++
                bonusTime()
            }else{
                txtCorrectness.text="Wrong"
                txtCorrectness.setTextColor(Color.parseColor("#ffc09f"))
                numberOfIncorrectAnswers++
            }
            Handler().postDelayed({//added 1.5 s delay for next question
                txtCorrectness.text=""
                startGame(txtExpression1,txtExpression2)

            }, 1500)
        }
        btnLess.setOnClickListener{
            if (answer1<answer2){//if the answer is correct change show it with in unique colors red and green with correct and incorrect
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#41cf41"))
                numberOfCorrectAnswers++
                bonusTime()
            }else{
                txtCorrectness.text="Wrong"
                txtCorrectness.setTextColor(Color.parseColor("#ffc09f"))
                numberOfIncorrectAnswers++
            }
            Handler().postDelayed({//added 1.5 s delay for next question
                txtCorrectness.text=""
                startGame(txtExpression1,txtExpression2)

            }, 1500)
        }

        if(savedInstanceState!=null){//recover the variables
            counter=savedInstanceState.getInt("counter")
            numberOfIncorrectAnswers=savedInstanceState.getInt("numberOfIncorrectAnswers")
            numberOfCorrectAnswers=savedInstanceState.getInt("numberOfCorrectAnswers")
            expression1= savedInstanceState.getString("expression1").toString()
            expression2= savedInstanceState.getString("expression2").toString()
            answer1=savedInstanceState.getDouble("answer1")
            answer2=savedInstanceState.getDouble("answer2")

            txtExpression1.text=expression1//changed the expression test view 1
            txtExpression2.text=expression2//changed the expression test view 1
        }


    }

    fun startGame(txtException1: TextView,txtException2: TextView){
        /**
         * to display the game logic.
         */
        val rand = Random()
        val numOfOperations1 = rand.nextInt(3) + 1//number of operations for left expression
        val numOfOperations2 = rand.nextInt(3) + 1//number of operations for right expression
        val firstNum1 = rand.nextInt(20) + 1//first number of the expression left
        val firstNum2 = rand.nextInt(20) + 1//first number of the expression right


        //generating first expression
        expressionAnswer = firstNum1.toDouble()//adding first number at the start to the temp expression answer
        genarate(firstNum1.toString(), numOfOperations1)//generate random expression and answer
        answer1=expressionAnswer//give value to answer1 from temp answer variable
        expression1=expression
        txtException1.text=expression

        //generating second expression
        expression=""//making the expression temp variable empty
        expressionAnswer=firstNum2.toDouble()//adding first number at the start to the temp expression answer
        genarate(firstNum2.toString(), numOfOperations2)//generate random expression and answer
        answer2=expressionAnswer//give value to answer2 from temp answer variable
        expression2=expression
        txtException2.text=expression
    }
    fun genarate(firstNum:String,numOfOperations: Int){
        /**
         * to generate a random expressions and relative answers
         */
        var operations= mutableListOf<String>("+","-","*","/")//operations can have in the expression
        var rand= Random()

        var operationIndex=rand.nextInt(4)//generating random expression
        var secondNum=rand.nextInt(20)+1//generating random second number

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
            //to get integers only answer and expressions
            while (!isWhole(expressionAnswer / secondNum)) {
                secondNum = rand.nextInt(20) + 1
            }
            expressionAnswer = expressionAnswer / secondNum;
        }
        //////

        var term=firstNum+operations[operationIndex]+secondNum
        expression=term

        var newNumOfOperations=numOfOperations-1
        if(newNumOfOperations>0){
            genarate("($term)",newNumOfOperations);//recursive again with new first and number of operations
        }

    }
    fun isWhole(value: Double):Boolean {
        /**
         * to check number is a whole number
         * @return true for if it is whole number otherwise false.
         */
        return value - value.toInt() == 0.0
    }
    fun inRange(value1: Double, value2: Double, operation:String): Boolean {
        //to check a operation is an range of 0 to 100 including 0 and 100 before doing the calculations
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
        /**
         * to start the timer.
         */

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
        /**
         * to add bonus time.
         */
        if(numberOfCorrectAnswers%5==0){
            var alertBonus=Toast.makeText(applicationContext,"10 seconds added",Toast.LENGTH_SHORT)
            alertBonus.show()
            counter+=10
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter",counter)
        outState.putInt("numberOfCorrectAnswers",numberOfCorrectAnswers)
        outState.putInt("numberOfIncorrectAnswers",numberOfIncorrectAnswers)
        outState.putString("expression1",expression1)
        outState.putString("expression2",expression2)
        outState.putDouble("answer1",answer1)
        outState.putDouble("answer2",answer2)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt("counter")
        savedInstanceState.getInt("numberOfCorrectAnswers")
        savedInstanceState.getInt("numberOfIncorrectAnswers")
        savedInstanceState.getString("expression1")
        savedInstanceState.getString("expression2")
        savedInstanceState.getDouble("answer1")
        savedInstanceState.getDouble("answer2")
    }
}