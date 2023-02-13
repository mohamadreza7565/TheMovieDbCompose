package com.mohammadreza.moviedbcompose.data.model


import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)