package com.example.dev_intensive.utils

object Utils {
    fun parseFullName(fullName : String?) : Pair<String?,String?>{
        val parts = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        if (firstName?.length == 0 && lastName?.length ==0){
            firstName = "null"
            lastName = "null"
        }

        else if (fullName == ""){
            firstName = "null"
            lastName = "null"
        }

        return firstName to lastName
    }


    fun toInitials(firstName : String?, lastName : String?) : String?{

        val strBuilder = StringBuilder()

        if (firstName?.trim().isNullOrEmpty() && lastName?.trim().isNullOrEmpty())strBuilder.append("null")
        else if (!firstName?.trim().isNullOrEmpty() && lastName?.trim().isNullOrEmpty())strBuilder.append(firstName?.first()?.toUpperCase().toString())
        else if (!firstName?.trim().isNullOrEmpty() && !lastName?.trim().isNullOrEmpty())strBuilder.append(firstName?.first()?.toUpperCase().toString() + lastName?.first()?.toUpperCase().toString())
        else if (firstName?.trim().isNullOrEmpty() && !lastName?.trim().isNullOrEmpty())strBuilder.append(lastName?.first()?.toUpperCase().toString())

        return strBuilder.toString()

    }

    fun transliteration(payload : String, divider : String = " ") : String {
        val pairString = payload.split(" ")

        val firstName = pairString.getOrNull(0)
        val lastName = pairString.getOrNull(1)

        val strBuilder = StringBuilder()

        val alpho =  mapOf("а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e",
            "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m",
            "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u",
            "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh", "щ" to "sh", "ъ" to "",
            "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")


        if (firstName != null) {
            for (i in firstName) {
                if (i.isUpperCase()) {
                    if (!alpho.containsKey(i.toLowerCase().toString()))strBuilder.append(i.toUpperCase())
                    else {
                        for (t in alpho.keys){
                            if (i.toLowerCase().toString() == t) {
                                if (alpho.getValue(t).length == 2)strBuilder.append(alpho.getValue(t).first().toUpperCase() + alpho.getValue(t).last().toString())
                                if (alpho.getValue(t).length == 1)strBuilder.append(alpho.getValue(t).toUpperCase())
                            }
                            else strBuilder.append("")
                        }
                    }

                }else {
                    if (!alpho.containsKey(i.toString()))strBuilder.append(i.toString())
                    else {
                        for (t in alpho.keys) {
                            if (i.toString() == t) strBuilder.append(alpho[t])
                        }
                    }
                }
            }
        }

        strBuilder.append(divider)

        if (lastName != null) {
            for (i in lastName) {
                if (i.isUpperCase()) {
                    if (!alpho.containsKey(i.toLowerCase().toString()))strBuilder.append(i.toUpperCase())
                    else{
                        for (t in alpho.keys){
                            if (i.toLowerCase().toString() == t){
                                if (alpho.getValue(t).length == 2)strBuilder.append(alpho.getValue(t).first().toUpperCase() + alpho.getValue(t).last().toString())
                                if (alpho.getValue(t).length == 1)strBuilder.append(alpho.getValue(t).toUpperCase())
                            }
                            else strBuilder.append("")
                        }
                    }
                } else{
                    if (!alpho.containsKey(i.toString()))strBuilder.append(i.toString())
                    else{
                        for (t in alpho.keys) {
                            if (i.toString() == t) strBuilder.append(alpho[t])
                        }
                    }
                }
            }
        }
        return strBuilder.toString()
    }

}