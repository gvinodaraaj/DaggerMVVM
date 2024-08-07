package com.example.m2p.utile

import java.text.NumberFormat
import java.util.Locale

object StringFormater {
    fun String.removeFormat():String{
        return this.replace(",", "")
    }

    fun String.firstString():String{
        return this.split(" ").get(0)
    }
    fun String.moneyString( prefix:String="",sufix:String=""):String{
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        return prefix+numberFormat.format(this.toDouble())+" "+sufix
    }
}