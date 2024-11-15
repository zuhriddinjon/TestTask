package com.example.testtask.ui.home.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Recommendations(
    @SerializedName("title")
    val title: String,
    @SerializedName("items")
    val items: List<RecommendationsItem>
)

@Keep
data class RecommendationsItem(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("title")
    val title: String
)