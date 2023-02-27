package com.example.myfirstandroidchallenge.data.api.exception

class NoConnectivityException : NetworkException() {

    override fun getLocalizedMessage(): String {
        return "No Internet Connection"
    }
}

