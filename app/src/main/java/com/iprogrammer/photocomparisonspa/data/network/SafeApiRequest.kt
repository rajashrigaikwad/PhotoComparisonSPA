package com.iprogrammer.photocomparisonspa.data.network
import android.util.Log
import com.iprogrammer.photocomparisonspa.utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest{
    suspend fun <T: Any> apiRequest(call: suspend ()-> Response<T>)  : T{
        val response = call.invoke()
        if (response.isSuccessful){
            Log.d("SafeApiRequest", "isSuccessful="+response.body().toString())
            return response.body()!!
        }
        else{
            val error = response.errorBody()?.string()
            Log.d("SafeApiRequest","!isSuccessful="+response.errorBody()?.string())

            val message = StringBuilder()

            error?.let {
                try{
                     message.append(JSONObject(it).getString("resultMessage"))
                }catch (e: JSONException){ }
                message.append("\n")
            }
            message.append("Error Code:${response.code()}")
            throw ApiException(message.toString())
        }
    }
}