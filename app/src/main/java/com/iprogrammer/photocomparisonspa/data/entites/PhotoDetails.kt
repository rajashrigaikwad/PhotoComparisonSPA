package com.iprogrammer.photocomparisonspa.data.entites

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import com.iprogrammer.photocomparisonspa.R
import com.iprogrammer.photocomparisonspa.utils.GlideApp

@Entity
data class PhotoDetails(
    @PrimaryKey
    @SerializedName("id") var photoId: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("thumbnailUrl") var thumbnailUrl: String? = null,
    @SerializedName("comparedFlag") var comparedFlag: Boolean=false
)

@BindingAdapter("photoCover")
fun loadPhoto(view: ImageView, imageUrl: String?) {
    GlideApp.with(view.getContext())
        .load(imageUrl).apply(RequestOptions().placeholder(R.drawable.img_book_placeholder))
        .into(view)
}

/*
@BindingAdapter("photoCover")
fun loadPhoto(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load("https://i.picsum.photos/id/223/200/200.jpg?hmac=CNNyWbBcEAJ7TPkTmEEwdGrLFEYkxpTeVwJ7U0LB30Y").apply(RequestOptions().circleCrop())
        .into(view)
}*/
