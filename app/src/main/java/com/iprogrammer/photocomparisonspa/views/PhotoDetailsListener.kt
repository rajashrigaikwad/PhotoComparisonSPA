package com.iprogrammer.photocomparisonspa.views

interface PhotoDetailsListener {
    fun onStarted()
    fun onSuccess()
    fun onFailed(message: String)
}