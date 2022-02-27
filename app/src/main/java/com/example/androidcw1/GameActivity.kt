package com.example.androidcw1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class GameActivity : AppCompatActivity() {
    var expressionAnswer=0
    var expression=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var txtExpression1=findViewById<TextView>(R.id.txtExpression1)
        var txtExpression2=findViewById<TextView>(R.id.txtExpression2)

        var btnGreater=findViewById<Button>(R.id.btnGreater)
        var btnEqual=findViewById<Button>(R.id.btnEqual)
        var btnLess=findViewById<Button>(R.id.btnLess)

        startGame(txtExpression1,txtExpression2)

        btnGreater.setOnClickListener{
            startGame(txtExpression1,txtExpression2)
        }


    }
    fun startGame(txtException1: TextView,txtException2: TextView){
        val rand = Random()
        val numOfOperations1 = rand.nextInt(4) + 1
        val numOfOperations2 = rand.nextInt(4) + 1
        val firtNum1 = rand.nextInt(20) + 1
        val firtNum2 = rand.nextInt(20) + 1


        //testing
        expressionAnswer = firtNum1

        //generating first expression
        genarate(firtNum1.toString(), numOfOperations1)
        var answer1=expressionAnswer
        txtException1.text=expression

        //generating second expression
        expression=""
        expressionAnswer=firtNum2
        genarate(firtNum2.toString(), numOfOperations2)
        var answer2=expressionAnswer
        txtException2.text=expression
    }
    fun genarate(firstNum:String,numOfOperations: Int){
        var operations= mutableListOf<String>("+","-","*","/")
        var rand= Random()

        var operationIndex=rand.nextInt(4)
        var secondNum=rand.nextInt(20)+1

        /////////////
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