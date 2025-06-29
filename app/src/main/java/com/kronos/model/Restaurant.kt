package com.kronos.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("_id") val id: String,
    @SerializedName("created_at") val createdAt : String,
    @SerializedName("update_at") val updateAt : String,
    @SerializedName("is_deleted") val isDeleted : Boolean = false,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: String,
    @SerializedName("comments") val comments: String,
)