package com.kronos.presentation.ui.views.login.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") val access_token: String,
    @SerializedName("token_type") val token_type: String
)