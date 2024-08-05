package com.example.daggermvvm.utile

object StringFormater {
    fun String.removeFormat():String{
        return this.split(".").get(0).replace(",", "")
    }

    fun String.firstString():String{
        return this.split(" ").get(0)
    }
}