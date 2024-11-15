package com.example.testtask.ui.home.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PopularHealthTips(
    @SerializedName("title")
    val title: String,
    @SerializedName("items")
    val items: List<PopularHealthTipsItem>
)

@Keep
data class PopularHealthTipsItem(
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
)