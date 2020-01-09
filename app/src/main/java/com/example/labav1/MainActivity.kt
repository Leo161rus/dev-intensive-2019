package com.example.labav1

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val COUNTER_KEY = "Counter"
    lateinit var buttonPlus : Button
    lateinit var buttonMinus : Button
    lateinit var textCounter : TextView
    var id = 0

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        iniUI()
        textCounter.setText(id)

        val sp : SharedPreferences = getSharedPreferences("Counter",Context.MODE_PRIVATE)
        val editor =  sp.edit()
        editor.putInt(COUNTER_KEY,id)
        editor.apply()

        buttonMinus.setOnClickListener {
           val a =  sp.getInt(COUNTER_KEY,id)
            if (a == 0){
                val toast = Toast.makeText(applicationContext, "Min number", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                id--
                textCounter.setText(id)
                editor.putInt(COUNTER_KEY,id)
                editor.apply()
            }
        }

        buttonPlus.setOnClickListener {
            val b = sp.getInt(COUNTER_KEY,id)
            if(b == 10){
                val toast = Toast.makeText(applicationContext, "Max number", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                id++
                editor.putInt(COUNTER_KEY,id)
                editor.apply()
            }
        }



    }


    private fun iniUI(){
        buttonMinus = findViewById(R.id.buttonMinus)
        buttonPlus = findViewById(R.id.buttonPlus)
        textCounter = findViewById(R.id.textViewCounter)

    }

}
