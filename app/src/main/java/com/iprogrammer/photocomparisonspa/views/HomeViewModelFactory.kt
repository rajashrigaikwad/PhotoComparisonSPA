package com.iprogrammer.photocomparisonspa.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iprogrammer.photocomparisonspa.data.repository.PhotoDetailsRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val photoDetailsRepository: PhotoDetailsRepository

): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(photoDetailsRepository) as T
    }


}