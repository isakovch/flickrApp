package com.bts.flickr.ui.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bts.flickr.R
import com.bts.flickr.config.ApiConfig
import com.bts.flickr.data.entity.Photo
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class ImagesAdapter(private val listener: OnImageClickListener) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private var photoList: ArrayList<Photo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photos, parent, false)

        return ViewHolder(view)
    }

    fun addItems(photoList: ArrayList<Photo>) {
        this.photoList = photoList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return photoList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = photoList!![position]

        val url = String.format(ApiConfig.IMAGE_CONSTRUCTION_URL, item.farm, item.server, item.id, item.secret)

        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(holder.image)

        holder.itemView.setOnClickListener { listener.onImageClick(holder.image, url) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.image)
        lateinit var image: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
