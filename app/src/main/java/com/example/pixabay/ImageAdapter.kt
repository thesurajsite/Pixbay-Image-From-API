package com.example.pixabay

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val hits: List<Hit>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ImageViewHolder, position: Int) {
        val hit = hits[position]
        Glide.with(holder.itemView.context)
            .load(hit.previewURL)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {

            val intent=Intent(it.context, full_screen_image::class.java)
            val largeImageUrl=hit.largeImageURL
            intent.putExtra("largeImageUrl",largeImageUrl)
            it.context.startActivity(intent)

        }



    }

    override fun getItemCount(): Int {
        return hits.size
    }
}