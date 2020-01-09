package com.example.dev_intensive.models

import java.util.*

class User (
    val id: String,
    val firstName : String?,
    val lastName : String?,
    val avatar : String?,
    val rating : Int = 0,
    val respect : Int = 0,
    val lastVisit : Date? = null,
    val isOnline : Boolean = false
){
    constructor(id: String,firstName : String?,lastName : String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null)

    constructor(id: String) : this(id, "John","Doe $id")

    init {
        println("It`s Alive!!!\n" + "${if(lastName === "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName !!!"}\n")
    }

    companion object Factory{
        private var listId = -1
        fun makeUser(fullName : String) : User {
            listId++

            val parts = fullName.split(" ")

            val firstName = parts.getOrNull(0)
            val lastName = parts.getOrNull(1)

            return User(
                id = "$listId",
                firstName = "$firstName",
                lastName = "$lastName"
            )
        }
    }


    data class Builder constructor(
       private var id : String? = null,
       private var firstName : String? = null,
       private var lastName : String? = null,
       private var avatar : String? = null,
       private var rating : Int = 0,
       private var respect : Int = 0,
       private var lastVisit : Date? = null,
       private var isOnline : Boolean = false
    )
    {
        fun id(id : String) = apply { this.id = id }
        fun firstName(firstName: String?) = apply { this.firstName = firstName}
        fun lastName(lastName: String?) = apply { this.lastName = lastName }
        fun avatar(avatar: String?) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating }
        fun respect(respect: Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }
        fun build() = User(id!!,firstName,lastName,avatar,rating,respect, lastVisit, isOnline)
    }
}