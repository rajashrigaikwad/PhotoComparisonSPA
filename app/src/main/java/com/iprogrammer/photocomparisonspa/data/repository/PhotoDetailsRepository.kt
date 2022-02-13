package com.iprogrammer.photocomparisonspa.data.repository
import com.iprogrammer.photocomparisonspa.data.db.AppDatabase
import com.iprogrammer.photocomparisonspa.data.entites.PhotoDetails
import com.iprogrammer.photocomparisonspa.data.network.PhotoComparisonAPI
import com.iprogrammer.photocomparisonspa.data.network.SafeApiRequest

class PhotoDetailsRepository(
    private val appDatabase: AppDatabase,
    private val photoComparisonAPI: PhotoComparisonAPI
) : SafeApiRequest() {

    suspend fun getAllPhotosRepository():List<PhotoDetails>{
        return apiRequest {photoComparisonAPI.getAllPhotos()}
    }
    suspend fun insertPhotoListDetails(photoDetails: List<PhotoDetails>) =
        appDatabase.getPhotosDetailsDeo().insertPhotosDetails(photoDetails)

    suspend fun updateComparedFlag(photoId: Int,comparedFlag: Boolean) =
        appDatabase.getPhotosDetailsDeo().updateComparedFlag(photoId,comparedFlag)

    fun getPhotoListFromDB() = appDatabase.getPhotosDetailsDeo().getPhotosDetails()

    fun getComparedPhotoDetailsFromDB(comparedFlag: Boolean) = appDatabase.getPhotosDetailsDeo().getComparedPhotoDetails(comparedFlag)
    fun getFlagStatus(photoId: Int) = appDatabase.getPhotosDetailsDeo().getFlagStatus(photoId)

}