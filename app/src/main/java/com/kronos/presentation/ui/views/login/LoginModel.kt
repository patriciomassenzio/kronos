package com.kronos.presentation.ui.views.login

import com.google.gson.annotations.SerializedName

data class LoginModel (
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)