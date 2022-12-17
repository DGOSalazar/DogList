package com.example.doglist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doglist.databinding.ActivityMainBinding
import com.example.doglist.model.dao.apiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mAdapter: MainAdapter
    private val dogo = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.serachView.setOnQueryTextListener(this)
        initRecyclerView()
        Toast.makeText(this,
            "La petición no se ejecuto correctamente",
            Toast.LENGTH_LONG).show()
    }

    private fun initRecyclerView() {
        mAdapter= MainAdapter(dogo)
        binding.recyView.layoutManager=LinearLayoutManager(this)
        binding.recyView.adapter=mAdapter
    }

    private fun retroFit(): Retrofit {
        return Retrofit.Builder().
                baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchByNAme(name: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = retroFit().create(apiAdapter::class.java).getDogsbyName("$name/images")
            val doggy = call.body()

            withContext(Dispatchers.Main) {
                if(call.isSuccessful) {
                    val image = doggy?.imagesDogs?: emptyList()
                    dogo.clear()
                    dogo.addAll(image)
                    initRecyclerView()
                }
            }
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if(!p0.isNullOrEmpty()) searchByNAme(p0.lowercase())
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}