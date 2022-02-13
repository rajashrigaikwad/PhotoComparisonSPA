package com.iprogrammer.photocomparisonspa.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iprogrammer.photocomparisonspa.R
import com.iprogrammer.photocomparisonspa.data.entites.PhotoDetails
import com.iprogrammer.photocomparisonspa.databinding.ItemComparedPhotosBinding

class ComparedListAdapter(
    private val mPhotoDetailsList: List<PhotoDetails>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ComparedListViewHolder(
        val itemComparedPhotosBinding: ItemComparedPhotosBinding
    ) : RecyclerView.ViewHolder(itemComparedPhotosBinding.root)


    override fun getItemCount(): Int {
        return mPhotoDetailsList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ComparedListViewHolder).itemComparedPhotosBinding.model = mPhotoDetailsList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ComparedListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_compared_photos, parent, false
            )
        )
    }


}