package com.kronos.model

import com.google.gson.annotations.SerializedName

data class EmployeesLogin(
    @SerializedName ("identifier") val identifier: Int,
)
