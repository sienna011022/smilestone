package com.smilestone.smarket.dto

import com.google.gson.annotations.SerializedName

data class LoginData (
    @SerializedName("userId")
    val userId: String,

    @SerializedName("password")
    val password: String
    )