package com.mohammadreza.moviedbcompose.data.model


import com.google.gson.annotations.SerializedName

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
data class BaseErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)