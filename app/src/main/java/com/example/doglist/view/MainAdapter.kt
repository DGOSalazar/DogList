package com.example.doglist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.R
import com.example.doglist.databinding.DogListBinding
import com.squareup.picasso.Picasso

class MainAdapter(private var Dogs : List<String>)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            var mBinding= DogListBinding.bind(view)
            fun bind(image: String){
                Picasso.get().load(image).into(mBinding.imgDog)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate((R.layout.dog_list),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Dogs[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = Dogs.size
}