package com.kronos.model

import com.google.gson.annotations.SerializedName

data class UserPinModel(
    @SerializedName("identifier") val identifier: Int,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String = "",
    @SerializedName("comments") val comments: String= "",
    @SerializedName("phone") val phone: String= "",
    @SerializedName("address") val address: String= "",
    @SerializedName("email") val email: String,
    @SerializedName("restaurante_id") val restaurante_id: List<Int> = emptyList(),
    @SerializedName("emergency_contact") val emergency_contact: Int = 0,
    @SerializedName("work_schedule") val work_schedule: String = "",
)


