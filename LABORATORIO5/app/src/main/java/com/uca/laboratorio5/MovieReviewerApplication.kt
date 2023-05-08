package com.uca.laboratorio5

import android.app.Application
import com.uca.laboratorio5.data.model.movies
import com.uca.laboratorio5.repositories.MovieRepository

class MovieReviewerApplication:Application() {
    val movieRepository: MovieRepository by lazy {
        MovieRepository(movies)
    }
}