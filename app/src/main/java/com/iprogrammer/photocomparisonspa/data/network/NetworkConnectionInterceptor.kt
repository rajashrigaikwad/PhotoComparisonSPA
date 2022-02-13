package com.iprogrammer.photocomparisonspa.data.network
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.iprogrammer.photocomparisonspa.utils.NoIntenetException
import com.iprogrammer.photocomparisonspa.utils.ServerUnreachableException
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Exception
import java.net.SocketTimeoutException

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoIntenetException("Internet unavailable! Please check your network settings and try again.")
        try {
            return chain.proceed(chain.request())
        }
        catch (e: SocketTimeoutException){
            throw ServerUnreachableException("Server unreachable")
        }catch (e: Exception){
            Log.e("NetworkConnection","Exception",e)
            throw ServerUnreachableException("Server unreachable")
        }
    }
    private fun isInternetAvailable(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}