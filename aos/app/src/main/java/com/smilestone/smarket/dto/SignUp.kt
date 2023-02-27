package com.smilestone.smarket.dto

import com.google.gson.annotations.SerializedName

data class SignUp(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String
)