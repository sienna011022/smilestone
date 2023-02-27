package com.smilestone.smarket.dto

import com.google.gson.annotations.SerializedName

data class SignUpData (
    @SerializedName("userId")
    val userId: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("nickName")
    val nickname: String
)