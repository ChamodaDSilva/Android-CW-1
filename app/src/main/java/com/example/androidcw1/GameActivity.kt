package com.example.androidcw1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class GameActivity : AppCompatActivity() {
    //variables use both expressions
    var expressionAnswer=0.0
    var expression=""

    var answer1=0.0
    var answer2=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var txtExpression1=findViewById<TextView>(R.id.txtExpression1)//left expression
        var txtExpression2=findViewById<TextView>(R.id.txtExpression2)//right expression
        var txtCorrectness=findViewById<TextView>(R.id.txtCorrectness)//correct or incorrect indicate

        var btnGreater=findViewById<Button>(R.id.btnGreater)//buttons
        var btnEqual=findViewById<Button>(R.id.btnEqual)
        var btnLess=findViewById<Button>(R.id.btnLess)

        var btnNext=findViewById<Button>(R.id.btnNext)


        startGame(txtExpression1,txtExpression2)//starting game first time

        btnGreater.setOnClickListener{
            if (answer1>answer2){
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#00FF00"))
            }else{
                txtCorrectness.text="Incorrect"
                txtCorrectness.setTextColor(Color.parseColor("#FF0000"))
            }
        }
        btnEqual.setOnClickListener{
            if (answer1==answer2){
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#00FF00"))
            }else{
                txtCorrectness.text="Incorrect"
                txtCorrectness.setTextColor(Color.parseColor("#FF0000"))
            }
        }
        btnLess.setOnClickListener{
            if (answer1<answer2){
                txtCorrectness.text="Correct"
                txtCorrectness.setTextColor(Color.parseColor("#00FF00"))
            }else{
                txtCorrectness.text="Incorrect"
                txtCorrectness.setTextColor(Color.parseColor("#FF0000"))
            }
        }
        btnNext.setOnClickListener{
            txtCorrectness.text=""
            startGame(txtExpression1,txtExpression2)
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
}