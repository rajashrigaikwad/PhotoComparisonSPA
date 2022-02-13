package com.iprogrammer.photocomparisonspa.views

import android.util.Log
import androidx.lifecycle.ViewModel
import com.iprogrammer.photocomparisonspa.data.repository.PhotoDetailsRepository
import com.iprogrammer.photocomparisonspa.utils.ApiException
import com.iprogrammer.photocomparisonspa.utils.Coroutines
import com.iprogrammer.photocomparisonspa.utils.NoIntenetException
import com.iprogrammer.photocomparisonspa.utils.ServerUnreachableException

class HomeViewModel(
    private val photoDetailsRepository: PhotoDetailsRepository

) : ViewModel() {
    private val TAG: String = HomeViewModel::class.java.name
    var photoDetailsListener: PhotoDetailsListener? = null

    fun getPhotoList() = photoDetailsRepository.getPhotoListFromDB()
    fun getComparedPhotoDetails() = photoDetailsRepository.getComparedPhotoDetailsFromDB(true)


    fun getAllPhotos() {
        photoDetailsListener?.onStarted()
        try {
            Coroutines.main {
                val response = photoDetailsRepository.getAllPhotosRepository()
                if (response.isNullOrEmpty()) {
                    photoDetailsListener?.onFailed("Something went wrong")
                    return@main
                }
                photoDetailsRepository.insertPhotoListDetails(response)
                photoDetailsListener?.onSuccess()
            }
        } catch (e: NoIntenetException) {
            Log.e(TAG, "NoInternetException-", e)
            photoDetailsListener?.onFailed(e.message!!)
        } catch (e: ServerUnreachableException) {
            Log.e(TAG, "ServerUnreachableException-", e)
            photoDetailsListener?.onFailed(e.message!!)
        } catch (e: ApiException) {
            Log.e(TAG, "ApiException-", e)
            photoDetailsListener?.onFailed(e.message!!)
        } catch (e: Exception) {
            Log.e(TAG, "Exception-", e)
            photoDetailsListener?.onFailed("Something went wrong")
        }
    }

    fun updateFlag(photoId: Int) {
        try {
            Coroutines.io {
                val status = photoDetailsRepository.getFlagStatus(photoId)
                when (status) {
                    true -> photoDetailsRepository.updateComparedFlag(photoId, false)
                    false -> photoDetailsRepository.updateComparedFlag(photoId, true)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception-", e)
            photoDetailsListener?.onFailed("Something went wrong")
        }
    }

}