package com.example.daggermvvm.data

data class UserModel(val userName: String, val userPassword: String, val userRePassword: String, val userPhone: String, val keepLogin: Boolean,
                     var isLogin:Boolean)