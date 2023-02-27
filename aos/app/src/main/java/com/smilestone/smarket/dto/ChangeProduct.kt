package com.smilestone.smarket.dto

import com.google.gson.annotations.SerializedName

data class ChangeProduct(
    @SerializedName("productId")
    val productId: Long,

    @SerializedName("sellerId")
    val sellerId: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("price")
    val price: Long,

    @SerializedName("category")
    val category: String,

    @SerializedName("state")
    val state: Boolean,

    @SerializedName("view")
    val view: Long,

    @SerializedName("createdAt")
    val localDateTime: String,
)
