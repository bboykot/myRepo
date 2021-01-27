package overview

import android.example.com.spacex_launches.R
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import repository.Models

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Models>?) {

    val adapter = recyclerView.adapter as SpaceXGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bidImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let{
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .fallback(R.drawable.ic_broken_image))
            .into(imgView)

    }
}