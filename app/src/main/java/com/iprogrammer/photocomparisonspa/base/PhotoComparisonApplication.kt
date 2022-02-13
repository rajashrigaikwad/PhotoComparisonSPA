package com.iprogrammer.photocomparisonspa.base

import android.app.Application
import com.iprogrammer.photocomparisonspa.data.db.AppDatabase
import com.iprogrammer.photocomparisonspa.data.network.NetworkConnectionInterceptor
import com.iprogrammer.photocomparisonspa.data.network.PhotoComparisonAPI
import com.iprogrammer.photocomparisonspa.data.repository.PhotoDetailsRepository
import com.iprogrammer.photocomparisonspa.views.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class PhotoComparisonApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@PhotoComparisonApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { PhotoComparisonAPI(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PhotoDetailsRepository(instance(), instance()) }
        bind() from singleton { HomeViewModelFactory(instance()) }
    }
}