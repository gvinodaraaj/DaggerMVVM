package com.example.daggermvvm.view

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.daggermvvm.data.BankDetails
import com.example.daggermvvm.data.ContactDetails
import com.example.daggermvvm.data.TransactionList
import com.example.daggermvvm.repository.profileRepo

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    // Declare a mutable list
    private val myMutableList: MutableList<TransactionList> = mutableListOf()
    /*user_id,name,user_bg,bank_id,bank_name,bank_bg,status,amount,type, time,*/
    /*
  @SerializedName("id"        ) var id       : Int?    = null,
  @SerializedName("user_id"   ) var userId   : String? = null,
  @SerializedName("user_name" ) var userName : String? = null,
  @SerializedName("user_icon" ) var userIcon : String? = null,
  @SerializedName("bank_id"   ) var bankId   : String? = null,
  @SerializedName("bank_name" ) var bankName : String? = null,
  @SerializedName("bank_bg"   ) var bankBg   : String? = null,
  @SerializedName("status"    ) var status   : String? = null,
  @SerializedName("amount"    ) var amount   : String? = null,
  @SerializedName("type"      ) var type     : String? = null,
  @SerializedName("time"      ) var time     : String? = null*/

    fun getMyList(): List<TransactionList> {
        val repo = profileRepo(context).getUsersFromAssets()
            ?.map{TransactionList(it.userId!!,it.userName!!,it.userIcon!!,it.bankId!!,it.bankName!!,it.bankBg!!,it.status!!,it.amount!!,it.type!!,it.time!!) }
        return repo!!
    }

    fun getMyContact(): List<ContactDetails> {
        val list=myMutableList.groupBy { it.user_id }.map{it.key}
        var contactList:MutableList <ContactDetails> = mutableListOf()
        for(i in 0 until list.size){
            val bankId=list[i]
            contactList.add(getContact(bankId))
        }
        return contactList
    }
    private fun getContact(userId:String):ContactDetails{
        val myMutableList = getMyList()
        val list=myMutableList.filter{it.user_id.equals(userId)}.map{ContactDetails(userId,it.name,it.bank_bg)}.last()
        return list
    }
    fun getTotal():String{
        val list=getMyList()
        val gain= list.filter {it.status.equals("Completed")&&it.type.equals("CREDIT")}.map { it.amount.toInt()}.sum()
        val loss= list.filter {it.status.equals("Completed")&&it.type.equals("DEBIT")}.map { it.amount.toInt()}.sum()
        val result =gain-loss
        return "$"+result
    }


    fun getMyBanks() :List<BankDetails>{
        val myMutableList = getMyList()
        val list=myMutableList.groupBy { it.bank_id }.map{it.key}
        var bankList:MutableList <BankDetails> = mutableListOf()
        for(i in 0 until list.size){
            val bankId=list[i]
            val gain= myMutableList.filter {it.bank_id.equals(bankId)&& it.type.equals("CREDIT")}.map { it.amount.toInt()}.sum()
            val loss= myMutableList.filter {it.bank_id.equals(bankId)&& it.type.equals("DEBIT")}.map { it.amount.toInt()}.sum()
            val result = "$"+(gain-loss)
            bankList.add(getBank(bankId,result))
        }
        return bankList
    }
    private fun getBank(bankId:String, sum:String):BankDetails{
        val myMutableList = getMyList()
        val list=myMutableList.filter{it.bank_id.equals(bankId)}.map{BankDetails(bankId.toString(),it.bank_name,it.bank_bg,sum)}.last()
        return list
    }




}
