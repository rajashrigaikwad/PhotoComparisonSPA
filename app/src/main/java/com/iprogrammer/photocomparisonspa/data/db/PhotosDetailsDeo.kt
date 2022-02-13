package com.iprogrammer.photocomparisonspa.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iprogrammer.photocomparisonspa.data.entites.PhotoDetails


@Dao
interface PhotosDetailsDeo {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotosDetails(photoDetails: List<PhotoDetails>)

    @Query("UPDATE photodetails SET comparedFlag = :mComparedFlag WHERE photoId=:mPhotoId")
    suspend fun updateComparedFlag(mPhotoId: Int, mComparedFlag: Boolean)


    @Query("SELECT * FROM PhotoDetails")
    fun getPhotosDetails(): LiveData<List<PhotoDetails>>

    @Query("SELECT * FROM PhotoDetails WHERE comparedFlag=:mComparedFlag")
    fun getComparedPhotoDetails(mComparedFlag: Boolean): LiveData<List<PhotoDetails>>

    @Query("SELECT comparedFlag FROM PhotoDetails WHERE photoId=:mPhotoId")
    fun getFlagStatus(mPhotoId: Int): Boolean


}