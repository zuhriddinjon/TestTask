package com.example.testtask.ui.home.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HomeDataDto(
    @SerializedName("popular_community")
    val popularCommunity: PopularCommunity,
    @SerializedName("recommendations")
    val recommendations: Recommendations,
    @SerializedName("popular_healthTips")
    val popularHealthTips: PopularHealthTips,
    @SerializedName("usefulInfo")
    val usefulInfo: UsefulInfo,
    @SerializedName("popular_discussions")
    val popularDiscussions: PopularDiscussions,
)