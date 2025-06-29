package com.kronos.model

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("_id") val id: String,
    @SerializedName("created_at") val createdAt : String,
    @SerializedName("update_at") val updateAt : String,
    @SerializedName("is_deleted") val isDeleted : Boolean = false,
    @SerializedName("0") val numero: Int,
    @SerializedName("descripcion") val descripcion: String,


)
