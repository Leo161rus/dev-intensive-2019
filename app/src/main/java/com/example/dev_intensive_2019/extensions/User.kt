@file:Suppress("UNREACHABLE_CODE")

package com.example.dev_intensive.extensions

import  com.example.dev_intensive.models.User
import com.example.dev_intensive.models.UserView
import com.example.dev_intensive.utils.Utils
import java.util.*
import java.util.Collections.replaceAll
import kotlin.math.absoluteValue

fun User.toUserView() : UserView {
    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName,lastName)
    val status = if(lastVisit == null ) "Если ни разу не был " else if(isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"
    return  UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        nickName = nickName,
        initials = initials,
        status = status
    )

}

fun String.truncate(wordsNum : Int = 16) : String{
    val strBuild = StringBuilder()
    val finalStr = this.subSequence(0,wordsNum)
    strBuild.append("${finalStr.trim()}...")
    return strBuild.toString()
}

fun String.stripHtml():String{
    val s = this.replace(Regex("[/=[<[a-z]>]]"),"")
    return s
}

fun Date.humanizeDiff(): String {
    val date =  Date().time - this.time
    val strBuilder = StringBuilder()
    val declensionMinutes = listOf("минут","минуты","минуту")
    val declensionHours = listOf("час","часа","часов")
    val declensionDay = listOf("день","дней","дня")

    if (date > 0)
        return when {
            date <= 1 * SECOND -> return strBuilder.append("только что").toString()

            date in 2..44 * SECOND -> return strBuilder.append("несколько секунд назад")
                .toString()

            date in 44 * SECOND..75 * SECOND -> return strBuilder.append("минуту назад")
                .toString()

            date in 75 * SECOND..45 * MINUTE  -> {
                return if ( 1 <(date / MINUTE) && (date/ MINUTE) <= 5 ||
                    21 < (date / MINUTE)|| (date / MINUTE) <= 24 ||
                    31 < (date / MINUTE)|| (date / MINUTE) <34 ||
                    42 < (date / MINUTE)|| (date / MINUTE) < 45) strBuilder.append("${date / MINUTE}" + declensionMinutes[1]).toString()
                else if (5 < (date / MINUTE) && (date / MINUTE) < 21 ||
                    24 < (date / MINUTE) || (date / MINUTE) <= 30 ||
                    34 < (date / MINUTE) || (date / MINUTE) <40 ||
                    45 == (date / MINUTE).toInt())
                    strBuilder.append("${date / MINUTE}" + " " + declensionMinutes[0] + " назад").toString()
                else strBuilder.append("${date / MINUTE}" + " " + declensionMinutes[2] + " назад").toString()
            }

            date in 45 * MINUTE..75 * MINUTE -> return strBuilder.append("час назад").toString()
            date in 75 * MINUTE..22 * HOUR -> {
                return if ( 1 < (date / HOUR) || (date / HOUR) < 5 ||
                    22 == (date / HOUR).toInt()) strBuilder.append("${date / HOUR}" + " " + declensionHours[1] + " назад").toString()
                else if ( 4 < (date / HOUR).toInt() || (date / HOUR) < 21)
                    strBuilder.append("${date / HOUR}" + " " + declensionHours[2] + " назад").toString()
                else strBuilder.append("${date / HOUR}" + " " + declensionHours[0] + " назад").toString()
            }
            date in 22 * HOUR..26 * HOUR -> return strBuilder.append("день назад").toString()
            date in 26 * HOUR..360 * DAY -> {
                return if ((date / DAY).toString() == "2" ||
                    (date / DAY).toString() == "3" ||
                    (date / DAY).toString() == "4" )strBuilder.append("${date/DAY} " + declensionDay[2] + " назад").toString()
                else if ((date/DAY).toString() == "5" || (date/DAY).toString() == "6" ||
                    (date / DAY).toString() == "7" || (date / DAY).toString() == "8" ||
                    (date / DAY).toString() == "9")strBuilder.append("${date/DAY} " + declensionDay[1] + " назад").toString()
                else if((date/DAY).toString() == "1")strBuilder.append("${date/DAY} " + declensionDay[0] + " назад").toString()
                else if((date/DAY).toString() == "11" || (date/DAY).toString() == "12" || (date/DAY).toString() == "13" || (date/DAY).toString() == "14" ||
                    (date/DAY).toString() == "111" || (date/DAY).toString() == "112" || (date/DAY).toString() == "113" || (date/DAY).toString() == "114"||
                    (date/DAY).toString() == "211" || (date/DAY).toString() == "212" || (date/DAY).toString() == "213" || (date/DAY).toString() == "214" ||
                    (date/DAY).toString() == "311" || (date/DAY).toString() == "312" || (date/DAY).toString() == "313" || (date/DAY).toString() == "314")strBuilder.append("${date/DAY} " + declensionDay[1] + " назад").toString()
                      else if((date/DAY).toString().last() == '1' && (date/DAY).toString() != "11" && (date/DAY).toString() != "111" &&
                    (date/DAY).toString() != "211" && (date/DAY).toString() != "311")strBuilder.append("${date/DAY} " + declensionDay[0] + " назад").toString()
                else if((date/DAY).toString().last() == '2' && (date/DAY).toString() != "12" ||
                        (date/DAY).toString().last() == '2' && (date/DAY).toString() != "112" ||
                    (date/DAY).toString().last() == '2' && (date/DAY).toString() != "212" ||
                    (date/DAY).toString().last() == '2' && (date/DAY).toString() != "312")strBuilder.append("${date/DAY} " + declensionDay[2] + " назад").toString()
                else if((date/DAY).toString().last() == '3' && (date/DAY).toString() != "13" ||
                    (date/DAY).toString().last() == '3' && (date/DAY).toString() != "113" ||
                    (date/DAY).toString().last() == '3' && (date/DAY).toString() != "213" ||
                    (date/DAY).toString().last() == '3' && (date/DAY).toString() != "313")strBuilder.append("${date/DAY} " + declensionDay[2] + " назад").toString()
                else if((date/DAY).toString().last() == '4' && (date/DAY).toString() != "14" ||
                        (date/DAY).toString().last() == '4' && (date/DAY).toString() != "114" ||
                    (date/DAY).toString().last() == '4' && (date/DAY).toString() != "214" ||
                    (date/DAY).toString().last() == '4' && (date/DAY).toString() != "314")strBuilder.append("${date/DAY} " + declensionDay[2] + " назад").toString()
                else if((date/DAY).toString().last() == '5' || (date/DAY).toString().last() == '6' || (date/DAY).toString().last() == '7' ||
                    (date/DAY).toString().last() == '8' || (date/DAY).toString().last() == '9' || (date/DAY).toString().last() == '0')strBuilder.append("${date/DAY} " + declensionDay[1] + " назад").toString()

                else strBuilder.append("null").toString()
            }
            else -> strBuilder.append("более года назад").toString()
        }

    else return when (date.absoluteValue) {
        in 2..44 * SECOND -> strBuilder.append("через несколько секунд")
            .toString()

        in 44 * SECOND..75 * SECOND -> strBuilder.append("через минуту")
            .toString()

        in 75 * SECOND..45 * MINUTE -> {
            return if ( 1 <(date / MINUTE) || (date/ MINUTE) <= 5 ||
                21 < (date / MINUTE)|| (date / MINUTE) <= 24 ||
                31 < (date / MINUTE)|| (date / MINUTE) <34 ||
                42 < (date / MINUTE)|| (date / MINUTE) < 45) strBuilder.append("через ${date.absoluteValue / MINUTE}" + " " + declensionMinutes[1]).toString()
            else if (5 < (date / MINUTE) || (date / MINUTE) < 21 ||
                24 < (date / MINUTE) || (date / MINUTE) <= 30 ||
                34 < (date / MINUTE) || (date / MINUTE) <40 ||
                45 == (date / MINUTE).toInt())
                strBuilder.append("через ${date.absoluteValue / MINUTE}" + " " + declensionMinutes[0]).toString()
            else strBuilder.append("через ${date.absoluteValue / MINUTE}" + " " + declensionMinutes[2]).toString()
        }

        in 45 * MINUTE..75 * MINUTE -> strBuilder.append("через час").toString()

        in 75 * MINUTE..22 * HOUR -> {
            return if ( 1 < (date / HOUR) || (date / HOUR) < 5 ||
                22 == (date / HOUR).toInt()) strBuilder.append("через ${date.absoluteValue / HOUR}" + " " + declensionHours[1]).toString()
            else if ( 4 < (date / HOUR).toInt() && (date / HOUR) < 21)
                strBuilder.append("через ${date.absoluteValue / HOUR}" + " " + declensionHours[2]).toString()
            else strBuilder.append("через ${date.absoluteValue / HOUR}" + " " + declensionHours[0]).toString()
        }

        in 22 * HOUR..26 * HOUR -> strBuilder.append("через день").toString()
        in 26 * HOUR.. 360 * DAY -> {
            return if ((date / DAY).toString() == "2" ||
                (date / DAY).toString() == "3" ||
                (date / DAY).toString() == "4" )strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[2]).toString()
            else if ((date/DAY).toString() == "5" || (date/DAY).toString() == "6" ||
                (date / DAY).toString() == "7" || (date / DAY).toString() == "8" ||
                (date / DAY).toString() == "9")strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[1]).toString()
            else if((date/DAY).toString() == "1")strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[0]).toString()
            else if((date/DAY).absoluteValue.toString() == "11" || (date/DAY).absoluteValue.toString() == "12" || (date/DAY).absoluteValue.toString() == "13" || (date/DAY).absoluteValue.toString() == "14" ||
                (date/DAY).absoluteValue.toString() == "111" || (date/DAY).absoluteValue.toString() == "112" || (date/DAY).absoluteValue.toString() == "113" || (date/DAY).absoluteValue.toString() == "114"||
                (date/DAY).absoluteValue.toString() == "211" || (date/DAY).absoluteValue.toString() == "212" || (date/DAY).absoluteValue.toString() == "213" || (date/DAY).absoluteValue.toString() == "214" ||
                (date/DAY).absoluteValue.toString() == "311" || (date/DAY).absoluteValue.toString() == "312" || (date/DAY).absoluteValue.toString() == "313" || (date/DAY).absoluteValue.toString() == "314")strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[1]).toString()
            else if((date/DAY).toString().last() == '1' && (date/DAY).toString() != "11" && (date/DAY).toString() != "111" &&
                (date/DAY).toString() != "211" && (date/DAY).toString() != "311")strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[0]).toString()
            else if((date/DAY).toString().last() == '2' && (date/DAY).toString() != "12" ||
                (date/DAY).toString().last() == '2' && (date/DAY).toString() != "112" ||
                (date/DAY).toString().last() == '2' && (date/DAY).toString() != "212" ||
                (date/DAY).toString().last() == '2' && (date/DAY).toString() != "312")strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[2]).toString()
            else if((date/DAY).toString().last() == '3' && (date/DAY).toString() != "13" ||
                (date/DAY).toString().last() == '3' && (date/DAY).toString() != "113" ||
                (date/DAY).toString().last() == '3' && (date/DAY).toString() != "213" ||
                (date/DAY).toString().last() == '3' && (date/DAY).toString() != "313")strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[2]).toString()
            else if((date/DAY).toString().last() == '4' && (date/DAY).toString() != "14" ||
                (date/DAY).toString().last() == '4' && (date/DAY).toString() != "114" ||
                (date/DAY).toString().last() == '4' && (date/DAY).toString() != "214" ||
                (date/DAY).toString().last() == '4' && (date/DAY).toString() != "314")strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[2]).toString()
            else if((date/DAY).toString().last() == '5' || (date/DAY).toString().last() == '6' || (date/DAY).toString().last() == '7' ||
                (date/DAY).absoluteValue.toString().last() == '8' || (date/DAY).toString().last() == '9' || (date/DAY).toString().last() == '0')strBuilder.append("через ${(date/DAY).absoluteValue} " + declensionDay[1]).toString()
            else strBuilder.append("null").toString()
        }
        else -> strBuilder.append("более чем через год").toString()
    }
    return strBuilder.toString()
}
