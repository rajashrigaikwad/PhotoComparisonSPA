package com.iprogrammer.photocomparisonspa.data.network

import com.iprogrammer.photocomparisonspa.data.entites.PhotoDetails
import com.iprogrammer.photocomparisonspa.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface PhotoComparisonAPI {

    @GET("photos")
    suspend fun getAllPhotos(
    ): Response<List<PhotoDetails>>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): PhotoComparisonAPI {
            val unSafeOkHttpClient = UnSafeOkHttpClient.getUnsafeOkHttpClient()
            unSafeOkHttpClient.addInterceptor(networkConnectionInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)

            return Retrofit.Builder()
                .client(okHttpClient.build())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PhotoComparisonAPI::class.java)
        }
    }

}