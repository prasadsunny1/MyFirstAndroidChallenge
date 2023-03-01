package com.example.myfirstandroidchallenge.data.api.exception

/**
 *  No connectivity exception class
 */
class NoConnectivityException : NetworkException() {

    override fun getLocalizedMessage(): String {
        return "No Internet Connection"
    }
}

