package com.example.dev_intensive_2019


import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dev_intensive_2019.models.Bender
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessController.getContext
import java.security.Key

class MainActivity : AppCompatActivity() , View.OnClickListener{
    lateinit var benderImage: ImageView
    lateinit var textTxt : TextView
    lateinit var messageEt : EditText
    lateinit var sendBtn : ImageView
    lateinit var benderObj : Bender
    lateinit var imm : InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status),Bender.Question.valueOf(question))

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this@MainActivity)
        actionDone()
}

   private fun Activity.hideKeyboard() {
       imm = getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
       imm.hideSoftInputFromWindow(sendBtn.windowToken,0)
   }

    private fun actionDone(){
         messageEt.setOnEditorActionListener { v, actionId, event ->
             if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER){
                 val(pharse,color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
                 messageEt.setText("")
                 val (r,g,b) = color
                 benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
                 textTxt.text = pharse
                 hideKeyboard()
                 return@setOnEditorActionListener true
             }
             else return@setOnEditorActionListener false
         }

    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send){
           val(pharse,color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = pharse
            hideKeyboard()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Status",benderObj.status.name)
    }
}
