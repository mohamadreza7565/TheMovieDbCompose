package com.mohammadreza.moviedbcompose.core.base


/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class BaseException(
    val type: Type,
    val serverMessage: String? = null,
    val code: Int? = null,
) : Throwable() {

    enum class Type {
        VALIDATION, SIMPLE, AUTH, INTERNET, SERVER
    }

}