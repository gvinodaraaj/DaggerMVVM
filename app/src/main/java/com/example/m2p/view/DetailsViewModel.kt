package com.example.m2p.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.m2p.data.model.BankDetails
import com.example.m2p.data.model.ContactDetails
import com.example.m2p.data.model.TransactionList
import com.example.m2p.data.repository.profileRepo
import com.example.m2p.utile.StringFormater.removeFormat

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    fun getMyList(): List<TransactionList> {
        val repo = profileRepo(context).getUsersFromAssets()
            ?.map {
                TransactionList(
                    it.userId!!,
                    it.userName!!,
                    it.userIcon!!,
                    it.bankId!!,
                    it.bankName!!,
                    it.bankBg!!,
                    it.status!!,
                    it.amount!!.removeFormat(),
                    it.type!!,
                    it.time!!
                )
            }
        return repo!!
    }

    fun getMyContact(): List<ContactDetails> {
        val list = getMyList().groupBy { it.user_id }.map { it.key }
        var contactList: MutableList<ContactDetails> = mutableListOf()
        contactList.add(ContactDetails("0", "Transfer", "transfer.png"))

        for (i in 0 until list.size) {
            val bankId = list[i]
            contactList.add(getContact(bankId))
        }
        return contactList
    }

    private fun getContact(userId: String): ContactDetails {
        val myMutableList = getMyList()
        val list = myMutableList.filter { it.user_id.equals(userId) }
            .map { ContactDetails(it.user_id, it.name, it.user_bg) }.last()
        return list
    }

    fun getTotal(): String {
        val list = getMyList()
        val gain = list.filter { it.status.equals("Completed") && it.type.equals("CREDIT") }
            .map { it.amount.toDouble() }.sum()
        val loss = list.filter { it.status.equals("Completed") && it.type.equals("DEBIT") }
            .map { it.amount.toDouble() }.sum()
        val result = gain - loss
        return result.toString()
    }

    fun getMyBanks(): List<BankDetails> {
        val myMutableList = getMyList()
        val list = myMutableList.groupBy { it.bank_id }.map { it.key }
        var bankList: MutableList<BankDetails> = mutableListOf()
        for (i in 0 until list.size) {
            val bankId = list[i]
            val gain =
                myMutableList.filter { it.bank_id.equals(bankId) && it.type.equals("CREDIT") }
                    .map { it.amount.toDouble() }.sum()
            val loss = myMutableList.filter { it.bank_id.equals(bankId) && it.type.equals("DEBIT") }
                .map { it.amount.toDouble() }.sum()
            val result = (gain - loss).toString()
            bankList.add(getBank(bankId, result))
        }
        return bankList
    }

    private fun getBank(bankId: String, sum: String): BankDetails {
        val myMutableList = getMyList()
        val list = myMutableList.filter { it.bank_id.equals(bankId) }
            .map { BankDetails(bankId, it.bank_name, it.bank_bg, sum) }.last()
        return list
    }

}
