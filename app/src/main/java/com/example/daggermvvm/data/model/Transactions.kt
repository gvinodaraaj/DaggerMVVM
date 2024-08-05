package com.example.daggermvvm.data.model

import com.google.gson.annotations.SerializedName

data class Transactions(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("user_id") var userId: String? = null,
    @SerializedName("user_name") var userName: String? = null,
    @SerializedName("user_icon") var userIcon: String? = null,
    @SerializedName("bank_id") var bankId: String? = null,
    @SerializedName("bank_name") var bankName: String? = null,
    @SerializedName("bank_bg") var bankBg: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("time") var time: String? = null
)