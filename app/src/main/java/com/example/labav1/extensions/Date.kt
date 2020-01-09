package com.example.dev_intensive.extensions

import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy") : String{
    val dataFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dataFormat.format(this)
}

fun Date.add(value : Int, units : TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun TimeUnits.plural(value: Int) : String{
    val strBuilder = StringBuilder()
    val declensionSeconds = listOf("секунду","секунды","секунд")
    val declensionMinutes = listOf("минуту","минуты","минут")
    val declensionHours = listOf("час","часа","часов")
    val declensionDay = listOf("день","дня","дней")

    if (TimeUnits.SECOND == this) {
        if (value.toString().last() == '1' && value != 11 || value.toString() == "1" ) strBuilder.append("$value " + declensionSeconds[0])
        else if (value.toString() == "2" ||
            value.toString() == "3"||
            value.toString() == "4") strBuilder.append("$value " + declensionSeconds[1])
        else if (value.toString().last() == '2' && value !in 5..20 ||
            value.toString().last() == '3' && value !in 5..20 ||
            value.toString().last() == '4' && value !in 5..20) strBuilder.append("$value " + declensionSeconds[1])
        else if (value.toString().last() == '5' ||
            value.toString().last() == '6' ||
            value.toString().last() == '7' ||
            value.toString().last() == '8' ||
            value.toString().last() == '9' ||
            value.toString().last() == '0' && 5 <= value && value < 21 ||
            value.toString().last() == '0') strBuilder.append("$value " + declensionSeconds[2])
        else if (value.toString() == "5" ||
            value.toString() == "6" ||
            value.toString() == "7" ||
            value.toString() == "8" ||
            value.toString() == "9" ||
            value in 5..20
        ) strBuilder.append("$value " + declensionSeconds[2])
        else if (value in  5..20)strBuilder.append("$value " + declensionSeconds[2])
    }

    else if (TimeUnits.MINUTE == this){
        if (value.toString().last() == '1' && value != 11 || value.toString() == "1" ) strBuilder.append("$value " + declensionMinutes[0])
        else if (value.toString() == "2" ||
            value.toString() == "3"||
            value.toString() == "4") strBuilder.append("$value " + declensionMinutes[1])
        else if (value.toString().last() == '2' && value !in 5..20 ||
            value.toString().last() == '3' && value !in 5..20 ||
            value.toString().last() == '4' && value !in 5..20) strBuilder.append("$value " + declensionMinutes[1])
        else if (value.toString().last() == '5' ||
            value.toString().last() == '6' ||
            value.toString().last() == '7' ||
            value.toString().last() == '8' ||
            value.toString().last() == '9' ||
            value.toString().last() == '0' && 5 <= value && value < 21 ||
            value.toString().last() == '0') strBuilder.append("$value " + declensionMinutes[2])
        else if (value.toString() == "5" ||
            value.toString() == "6" ||
            value.toString() == "7" ||
            value.toString() == "8" ||
            value.toString() == "9" ||
            value in 5..20
        ) strBuilder.append("$value " + declensionMinutes[2])
        else if (value in  5..20)strBuilder.append("$value " + declensionMinutes[2])
    }

    else if (TimeUnits.HOUR == this){
        if (value.toString().last() == '1' && value != 11 || value.toString() == "1" ) strBuilder.append("$value " + declensionHours[0])
        else if (value.toString() == "2" ||
            value.toString() == "3"||
            value.toString() == "4") strBuilder.append("$value " + declensionHours[1])
        else if (value.toString().last() == '2' && value !in 5..20 ||
            value.toString().last() == '3' && value !in 5..20 ||
            value.toString().last() == '4' && value !in 5..20) strBuilder.append("$value " + declensionHours[1])
        else if (value.toString().last() == '5' ||
            value.toString().last() == '6' ||
            value.toString().last() == '7' ||
            value.toString().last() == '8' ||
            value.toString().last() == '9' ||
            value.toString().last() == '0' && 5 <= value && value < 21 ||
            value.toString().last() == '0') strBuilder.append("$value " + declensionHours[2])
        else if (value.toString() == "5" ||
            value.toString() == "6" ||
            value.toString() == "7" ||
            value.toString() == "8" ||
            value.toString() == "9" ||
            value in 5..20
        ) strBuilder.append("$value " + declensionHours[2])
        else if (value in  5..20)strBuilder.append("$value " + declensionHours[2])
    }
    else{
        if (value.toString().last() == '1' && value != 11 || value.toString() == "1" ) strBuilder.append("$value " + declensionDay[0])
        else if (value.toString() == "2" ||
            value.toString() == "3"||
            value.toString() == "4") strBuilder.append("$value " + declensionDay[1])
        else if (value.toString().last() == '2' && value !in 5..20 ||
            value.toString().last() == '3' && value !in 5..20 ||
            value.toString().last() == '4' && value !in 5..20) strBuilder.append("$value " + declensionDay[1])
        else if (value.toString().last() == '5' ||
            value.toString().last() == '6' ||
            value.toString().last() == '7' ||
            value.toString().last() == '8' ||
            value.toString().last() == '9' ||
            value.toString().last() == '0' && 5 <= value && value < 21 ||
            value.toString().last() == '0') strBuilder.append("$value " + declensionDay[2])
        else if (value.toString() == "5" ||
            value.toString() == "6" ||
            value.toString() == "7" ||
            value.toString() == "8" ||
            value.toString() == "9" ||
            value in 5..20
        ) strBuilder.append("$value " + declensionDay[2])
        else if (value in  5..20)strBuilder.append("$value " + declensionDay[2])
    }
    return strBuilder.toString()
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}