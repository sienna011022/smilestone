package com.smilestone.smarket.dto

data class Chat(
    val roomId: String,
    val sender: String,
    val message: String,
    val chatAt: String
)
