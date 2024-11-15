package com.example.testtask.ui.home.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PopularDiscussions(
    @SerializedName("title")
    val title: String,
    @SerializedName("items")
    val items: List<PopularDiscussionsItem>,
)

@Keep
data class PopularDiscussionsItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("actions")
    val actions: PopularDiscussionActions,
    @SerializedName("userAvatar")
    val userAvatar: String,
)

@Keep
data class PopularDiscussionActions(
    @SerializedName("increaseCount")
    val increaseCount: Int,
    @SerializedName("decreaseCount")
    val decreaseCount: Int,
    @SerializedName("commentCount")
    val commentCount: Int,
)