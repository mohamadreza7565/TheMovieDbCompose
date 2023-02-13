package com.mohammadreza.moviedbcompose.core.base

import android.content.Context
import android.util.Log
import com.mohammadreza.moviedbcompose.R
import com.mohammadreza.moviedbcompose.data.model.BaseErrorResponse
import com.mohammadreza.moviedbcompose.global.GlobalFunction
import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent
import retrofit2.Response


abstract class BaseDataSource {

    val mContext: Context by KoinJavaComponent.inject(Context::class.java)

    private suspend fun <T> getResult(call: suspend () -> Response<T>): BaseApiDataState<T> {
        try {

            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                return BaseApiDataState.Success(body)
            }

            return BaseApiDataState.Error(parseErrorModel(response.errorBody() as BaseErrorResponse))
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
                        mContext.getString(R.string.error_connection_to_server),
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
