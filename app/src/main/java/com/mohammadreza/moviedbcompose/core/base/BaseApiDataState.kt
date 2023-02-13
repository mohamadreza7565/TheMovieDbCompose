package com.mohammadreza.moviedbcompose.core.base

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
sealed class BaseApiDataState<out R> {
    object Completed : BaseApiDataState<Nothing>()
    data class Success<out T>(val data: T?) : BaseApiDataState<T>()
    data class Error(val exception: BaseException) : BaseApiDataState<Nothing>()
    object Loading : BaseApiDataState<Nothing>()
}