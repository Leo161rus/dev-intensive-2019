package com.example.dev_intensive_2019.models

import android.app.Activity
import android.graphics.Rect
import android.view.ViewTreeObserver
import com.example.dev_intensive_2019.R
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.StringBuilder

class Bender (var status: Status = Status.NORMAL, var question : Question = Question.NAME) {

    fun askQuestion() : String = when(question){
                Question.NAME -> Question.NAME.question
                Question.PROFESSION -> Question.PROFESSION.question
                Question.MATERIAL -> Question.MATERIAL.question
                Question.BDAY -> Question.BDAY.question
                Question.SERIAL -> Question.SERIAL.question
                Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String): Pair<String, Triple<Int,Int,Int>>{

     return  if (question.answers.contains(answer)){
         question = question.nextQuestion()
            "Отлично - это правильный ответ\n${question.question}" to status.color
        }
     else if (question == Question.NAME && answer.first().isLowerCase()){
         question = question.stayQuestion()
            "Имя должно начинаться с заглавной буквы\n${question.question}" to status.color
     }
     else if (question == Question.PROFESSION && answer.first().isUpperCase()){
         question = question.stayQuestion()
            "Профессия должна начинаться со строчной буквы\n${question.question}" to status.color
     }
     else if (question == Question.MATERIAL){
         val strBuilder = StringBuilder()
         for (i in answer){
             if (i.toString() == (1..9).toString())
                 strBuilder.append("Материал не должен содержать цифр")
             question = question.stayQuestion()
         }
         return strBuilder.toString() + "\n${question.question}" to status.color
     }
     else if (question == Question.BDAY){
         val strBuilder = StringBuilder()
         for (i in answer){
             if(i.toString() != (1..9).toString()){
                 strBuilder.append("Год моего рождения должен содержать только цифры")
                 question = question.stayQuestion()
             }
         }
         return strBuilder.toString() + "\n${question.question}" to status.color
     }
     else if (question == Question.SERIAL && answer.length != 7){
         val strBuilder = StringBuilder()
         for (i in answer){
             if(i.toString() != (1..9).toString()){
                    strBuilder.append("Серийный номер содержит только цифры, и их 7")
                 question = question.stayQuestion()
             }
         }
         return strBuilder.toString() + "\n${question.question}" to status.color
     }
     else if (question == Question.SERIAL && answer.length == 7) {
         val strBuilder = StringBuilder()
         for (i in answer) {
             if (i.toString() != (1..9).toString()) {
                 strBuilder.append("Серийный номер содержит только цифры, и их 7")
                 question = question.stayQuestion()
             }else{
                 status = status.nextStatus()
                 "Это не правильный ответ\n${question.question}!" to status.color
             }
         }
         return strBuilder.toString() + "\n${question.question}" to status.color
     }
     else {
         status = status.nextStatus()
            "Это не правильный ответ\n${question.question}!" to status.color
        }
    }

    enum class Status (val color: Triple<Int,Int,Int>){
        NORMAL (Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANDER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

        fun nextStatus() : Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }
            else{
                values()[0]
            }
        }
    }

    fun Activity.isKeyboardOpen() : Boolean{
        val activityRootView = window.decorView.rootView
        activityRootView.getWindowVisibleDisplayFrame(Rect())
             val heightDiff = activityRootView.rootView.height - (Rect().bottom - Rect().top)
        return heightDiff > 100
    }

    fun Activity.isKeyboardClosed() : Boolean{
        val activityRootView = window.decorView.rootView
        activityRootView.getWindowVisibleDisplayFrame(Rect())
        val heightDiff = activityRootView.rootView.height - (Rect().bottom - Rect().top)
        return heightDiff < 100
    }

    enum class Question (val question: String, val answers : List<String>){
        NAME("Как меня зовут?", listOf("бендер", "Bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun stayQuestion(): Question = NAME
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "Bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun stayQuestion(): Question = PROFESSION
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево","metal","iron","wood")){
            override fun nextQuestion(): Question = BDAY
            override fun stayQuestion(): Question = MATERIAL
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun stayQuestion(): Question = BDAY
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun stayQuestion(): Question = SERIAL
        },
        IDLE("На этом все, вопросов больше нет", listOf(",бендер", "Bender")) {
            override fun nextQuestion(): Question = NAME
            override fun stayQuestion(): Question = IDLE
        };

        abstract fun nextQuestion() : Question
        abstract fun stayQuestion() : Question
    }
}
