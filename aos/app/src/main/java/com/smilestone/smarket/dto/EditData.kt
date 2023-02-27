package com.smilestone.smarket.dto

import com.google.gson.annotations.SerializedName

data class EditData(
    @SerializedName("sellerId")
    val sellerId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("category")
    val category: String
)