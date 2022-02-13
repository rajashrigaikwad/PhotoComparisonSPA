package com.iprogrammer.photocomparisonspa.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.iprogrammer.photocomparisonspa.R
import com.iprogrammer.photocomparisonspa.data.entites.PhotoDetails
import com.iprogrammer.photocomparisonspa.databinding.ActivityHomeBinding
import com.iprogrammer.photocomparisonspa.utils.hide
import com.iprogrammer.photocomparisonspa.utils.show
import com.iprogrammer.photocomparisonspa.utils.snackbar
import kotlinx.android.synthetic.main.activity_home.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware, PhotoDetailsListener,
    PhotoDetailsAdapterCallbackListener {
    override val kodein by kodein()
    private val TAG: String = HomeActivity::class.java.name
    private val factory: HomeViewModelFactory by instance()
    private var mPhotoListAdapter: PhotoListAdapter? = null
    private var mComparedListAdapter: ComparedListAdapter? = null
    private var mList = ArrayList<PhotoDetails>()
    private var mComparedList = ArrayList<PhotoDetails>()
    private var viewModel: HomeViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        DataBindingUtil.setContentView<ActivityHomeBinding>(
            this,
            R.layout.activity_home

        ).apply {
            this.lifecycleOwner = this@HomeActivity
            this.viewModel = viewModel
        }
        viewModel?.photoDetailsListener = this
        viewModel?.getPhotoList()?.observe(this, Observer { details ->
            if (details.isNullOrEmpty()) {
                /*Check if data not available in database then execute API call*/
                viewModel?.getAllPhotos()
            } else {
                mList.clear()
                mList.addAll(details)
                mPhotoListAdapter?.notifyDataSetChanged()
            }
        })
        viewModel?.getComparedPhotoDetails()?.observe(this, Observer {
            if (it != null) {
                mComparedList.clear()
                mComparedList.addAll(it)
                mComparedListAdapter?.notifyDataSetChanged()
            }
        })

        /*
        * SetUp Recyclerview to display all photos
        */
        recyclerPhotoList.also {
            mPhotoListAdapter = PhotoListAdapter(mList, this)
            val layoutManager = GridLayoutManager(this, 3)
            it.layoutManager = layoutManager
            it.adapter = mPhotoListAdapter
        }

        /*
       * SetUp Recyclerview to display all compared photos
       */
        recyclerComparisonTable.also {
            mComparedListAdapter = ComparedListAdapter(mComparedList)
            val layoutManager = LinearLayoutManager(this)
            it.layoutManager = layoutManager
            it.adapter = mComparedListAdapter
            it.addItemDecoration(
                DividerItemDecoration(
                    it.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onStarted() {
        if (progressBar != null) {
            progressBar.show()
        }
    }

    override fun onSuccess() {
        if (progressBar != null) {
            progressBar.hide()
        }
    }

    override fun onFailed(message: String) {
        if (progressBar != null) {
            progressBar.hide()
        }
        if (containerHome != null) {
            containerHome.snackbar(message)
        }
    }

    override fun onCompareButtonClicked(photoId: Int?) {
        viewModel?.updateFlag(photoId!!)
    }
}