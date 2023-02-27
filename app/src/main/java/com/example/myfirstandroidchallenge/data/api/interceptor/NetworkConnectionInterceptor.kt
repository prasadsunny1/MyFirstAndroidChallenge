package com.example.myfirstandroidchallenge.data.api.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.example.myfirstandroidchallenge.data.api.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import java.io.IOException

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val mContext: Context

    init {
        mContext = context
    }

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        if (!isConnected) {
            throw NoConnectivityException()
            // Throwing our custom exception 'NoConnectivityException'
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private val isConnected: Boolean
        get() {
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // What is the alternative of following line in Kotlin?
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }
}