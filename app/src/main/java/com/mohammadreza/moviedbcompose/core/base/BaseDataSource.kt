package com.mohammadreza.moviedbcompose.core.base

import android.content.Context
import android.util.Log
import com.mohammadreza.moviedbcompose.R
import com.mohammadreza.moviedbcompose.data.model.BaseErrorResponse
import com.mohammadreza.moviedbcompose.global.GlobalFunction
import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent
import retrofit2.Response

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
abstract class BaseDataSource {

    private suspend fun <T> getResult(call: suspend () -> Response<T>): BaseApiDataState<T> {
        try {

            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                return BaseApiDataState.Success(body)
            }

            return BaseApiDataState.Error(
                BaseException(
                    type = BaseException.Type.INTERNET,
                    serverMessage = "Internet connection error"
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            logE(
                " call Api crashed - Exception happened in BaseDataSource.kt:27 -> \n" +
                        "\n======================================\\/========================================= \n $e \n " +
                        " ====================================/\\======================================="
            )
            return if (!GlobalFunction.isNetworkAvailable)
                BaseApiDataState.Error(
                    BaseException(
                        BaseException.Type.INTERNET,
                        "Internet connection error",
                        0
                    )
                )
            else
                BaseApiDataState.Error(BaseException(BaseException.Type.INTERNET, e.message, 0))
        }
    }

    private fun parseErrorModel(response: BaseErrorResponse): BaseException {
        return BaseException(
            BaseException.Type.SERVER,
            response.statusMessage,
            response.statusCode
        )
    }

    /**
     * get Result With Exponential Backoff Strategy
     */
    protected fun <T> callApi(call: suspend () -> Response<T>) = flow {
        emit(BaseApiDataState.Loading)
        val response = getResult(call)
        emit(response)
    }

    private fun logE(where: String) {
        Log.e(
            "BaseDataSource", "\n ------------------ Start ------------------\n" +
                    "----------------"
        )

        Log.e("BaseDataSource ", where)

        Log.e(
            "BaseDataSource ", "\n ------------------ End ------------------\n" +
                    "----------------"
        )


    }


}
