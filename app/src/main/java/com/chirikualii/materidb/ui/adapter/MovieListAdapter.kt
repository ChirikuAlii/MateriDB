package com.chirikualii.materidb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chirikualii.materidb.data.model.Movie
import com.chirikualii.materidb.databinding.ItemMovieBinding

class MovieListAdapter :RecyclerView.Adapter<MovieListAdapter.Holder>() {

    private val listData = mutableListOf<Movie>()
    class Holder(val binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(data:Movie){

            binding.tvTitle.text = data.title
            binding.tvGenre.text = data.genre

            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${data.imagePoster}")
                .into(binding.ivMovie)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun addItem(data:List<Movie>){
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
}