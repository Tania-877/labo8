package com.uca.laboratorio5.repositories

import com.uca.laboratorio5.data.model.MovieModel

class MovieRepository(private val movies: MutableList<MovieModel>) {
    fun getMovies() = movies

    fun addMovies(movie: MovieModel) = movies.add(movie)
}