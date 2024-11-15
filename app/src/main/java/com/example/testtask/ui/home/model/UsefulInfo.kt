package com.example.testtask.ui.home.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UsefulInfo(
    @SerializedName("title")
    val title: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("description")
    val description: String,
)