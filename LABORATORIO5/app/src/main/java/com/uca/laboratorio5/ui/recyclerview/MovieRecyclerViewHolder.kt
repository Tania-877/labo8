package com.uca.laboratorio5.ui.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.uca.laboratorio5.data.model.MovieModel
import com.uca.laboratorio5.databinding.MovieItemBinding

class MovieRecyclerViewHolder(private val binding:MovieItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieModel, clickListener:(MovieModel) -> Unit){
        binding.txtTitle.text = movie.name
        binding.txtQualification.text = movie.qualification

        binding.movieItemCard.setOnClickListener{
            clickListener(movie)
        }
    }
}