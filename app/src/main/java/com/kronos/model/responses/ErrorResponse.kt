package com.kronos.model.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponseModel(
    @SerializedName("status_code") val status_code: Int,
    @SerializedName("data") val data: Any?,
    @SerializedName("errors") val errors: List<ErrorDetail>,
    @SerializedName("request_id") val request_id: String
)

data class ErrorDetail(
    @SerializedName("error") val error: String,
    @SerializedName("description") val description: String,
    @SerializedName("message") val message: String
)
