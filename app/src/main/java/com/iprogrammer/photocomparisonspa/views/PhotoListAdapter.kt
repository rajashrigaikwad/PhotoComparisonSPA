package com.iprogrammer.photocomparisonspa.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iprogrammer.photocomparisonspa.R
import com.iprogrammer.photocomparisonspa.data.entites.PhotoDetails
import com.iprogrammer.photocomparisonspa.databinding.ItemPhotoBinding

class PhotoListAdapter(
    private val mPhotoDetailsList: List<PhotoDetails>,
    private val mPhotoDetailsAdapterCallbackListener: PhotoDetailsAdapterCallbackListener
    ): RecyclerView.Adapter<PhotoListAdapter.PhotoListViewHolder>() {

    inner class PhotoListViewHolder(
        val itemPhotoBinding: ItemPhotoBinding
    ): RecyclerView.ViewHolder(itemPhotoBinding.root){
        fun bind(photoDetails: PhotoDetails){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder=PhotoListViewHolder(
        DataBindingUtil.inflate<ItemPhotoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_photo,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.itemPhotoBinding.model = mPhotoDetailsList[position]
        holder.itemPhotoBinding.buttonCompare.setOnClickListener {
            when(mPhotoDetailsList[position].comparedFlag){
                true-> holder.itemPhotoBinding.buttonCompare.text = "Remove"
                false-> holder.itemPhotoBinding.buttonCompare.text = "Compare"
            }
            mPhotoDetailsAdapterCallbackListener.onCompareButtonClicked(mPhotoDetailsList[position].photoId)
        }
    }

    override fun getItemCount(): Int {
        return mPhotoDetailsList.size
    }

}