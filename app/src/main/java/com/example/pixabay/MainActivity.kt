package com.example.pixabay

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter
    private lateinit var retrofit: Retrofit
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView=findViewById(R.id.searchBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Initialize Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiInterface::class.java)

        getImage("butterfly Flower")
        searchImage()
    }

    private fun getImage(query: String) {
        // Make API request
        val service = retrofit.create(ApiInterface::class.java)
        service.searchImages("43000360-2cce79fe617b5927b866ffd01", "$query").enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                if (response.isSuccessful) {
                    val myData = response.body()
                    myData?.let {
                        adapter = ImageAdapter(it.hits)
                        recyclerView.adapter = adapter
                    }
                } else {
                    Log.e("API Error", "Failed to get data")
                }
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                Log.e("API Error", "onFailure: ${t.message}")
            }
        })
    }

    private fun searchImage() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    getImage(query)
                    Toast.makeText(this@MainActivity, "searching...", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
}