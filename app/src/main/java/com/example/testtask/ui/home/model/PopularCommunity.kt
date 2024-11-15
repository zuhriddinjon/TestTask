package com.example.testtask.ui.home.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PopularCommunity(
    @SerializedName("title")
    val title: String,
    @SerializedName("items")
    val items: List<PopularCommunityItem>,
)

@Keep
data class PopularCommunityItem(
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
)