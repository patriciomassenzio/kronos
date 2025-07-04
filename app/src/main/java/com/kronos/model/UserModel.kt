package com.kronos.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("rol") val rol: String
)