package com.smilestone.smarket.dto

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Login(

        @SerializedName("message")
        val message: String,

        @SerializedName("code")
        val code: String,

        @SerializedName("id")
        val id: Long,

        @SerializedName("userId")
        val userId: String,

        @SerializedName("tokens")
        val tokens: Token,
    )
