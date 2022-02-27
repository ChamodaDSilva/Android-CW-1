package com.example.androidcw1

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

        startGame(txtExpression1,txtExpression2)//starting game first time

        btnGreater.setOnClickListener{
            if (answer1>answer2){
                txtCorrectness.text="Correct"
            }else{
                txtCorrectness.text="Incorrect"
            }
        }
        btnEqual.setOnClickListener{
            if (answer1==answer2){
                txtCorrectness.text="Correct"
            }else{
                txtCorrectness.text="Incorrect"
            }
        }
        btnLess.setOnClickListener{
            if (answer1<answer2){
                txtCorrectness.text="Correct"
            }else{
                txtCorrectness.text="Incorrect"
            }
        }



    }
    fun startGame(txtException1: TextView,txtException2: TextView){
        val rand = Random()
        val numOfOperations1 = rand.nextInt(4) + 1
        val numOfOperations2 = rand.nextInt(4) + 1
        val firtNum1 = rand.nextInt(20) + 1
        val firtNum2 = rand.nextInt(20) + 1



        expressionAnswer = firtNum1.toDouble()

        //generating first expression
        genarate(firtNum1.toString(), numOfOperations1)
        answer1=expressionAnswer
        txtException1.text=expression+",\n$answer1"

        //generating second expression
        expression=""
        expressionAnswer=firtNum2.toDouble()
        genarate(firtNum2.toString(), numOfOperations2)
        answer2=expressionAnswer
        txtException2.text=expression+",\n$answer2"
    }
    fun genarate(firstNum:String,numOfOperations: Int){
        var operations= mutableListOf<String>("+","-","*","/")
        var rand= Random()

        var operationIndex=rand.nextInt(4)
        var secondNum=rand.nextInt(20)+1

        //about the answer of arithmetic operation
        if(operationIndex==0){
            expressionAnswer=expressionAnswer+secondNum;
        }else if(operationIndex==1){
            expressionAnswer=expressionAnswer-secondNum;
        }else if(operationIndex==2){
            expressionAnswer=expressionAnswer*secondNum;
        }else if(operationIndex==3){
            expressionAnswer=expressionAnswer/secondNum;
        }
        //////

        var term=firstNum+operations[operationIndex]+secondNum
        expression=term

        var newNumOfOperations=numOfOperations-1
        if(newNumOfOperations>0){
            genarate("($term)",newNumOfOperations);
        }



    }
}