package com.example.myfirstandroidchallenge.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Class to intercept the network connection and retry the request with exponential backoff
 */
class RetryInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val request = chain.request()

        // Initialize variables for exponential backoff
        var numRetries = 0
        var backoffSeconds: Long = INITIAL_BACKOFF_SECONDS.toLong()
        while (true) {
            try {
                // Try to execute the request
                val response = chain.proceed(request)

                // If the response was successful, return it
                if (response.isSuccessful) {
                    return response
                }
                throw IOException("Request failed with HTTP error code " + response.code())
            } catch (e: IOException) {
                // Check if we have exceeded the maximum number of retries
                if (numRetries >= MAX_RETRIES) {
                    throw e
                }

                // Increment the retry counter and calculate the backoff time
                numRetries++
                backoffSeconds *= 2

                // Sleep for the backoff time before retrying
                try {
                    Thread.sleep(SECONDS.toMillis(backoffSeconds))
                } catch (ignored: InterruptedException) {
                    // Ignore the interrupted exception and continue with the retry
                }
            }
        }
    }

    companion object {

        private const val MAX_RETRIES = 3
        private const val INITIAL_BACKOFF_SECONDS = 1
    }
}