package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class full_screen_image : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        val fullScreenImage=findViewById<ImageView>(R.id.fullScreenImage)
        val imageUrl= intent.getStringExtra("largeImageUrl")

        Glide.with(this)
            .load(imageUrl)
            .into(fullScreenImage)
    }
}