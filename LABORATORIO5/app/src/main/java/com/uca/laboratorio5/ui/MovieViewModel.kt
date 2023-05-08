package com.uca.laboratorio5.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.laboratorio5.MovieReviewerApplication
import com.uca.laboratorio5.data.model.MovieModel
import com.uca.laboratorio5.repositories.MovieRepository
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY

class MovieViewModel (private val repository: MovieRepository):ViewModel(){
    val name = MutableLiveData("");
    val description = MutableLiveData("");
    val category = MutableLiveData("");
    val qualification = MutableLiveData("");
    val status = MutableLiveData("");
    fun getMovies() = repository.getMovies()

    private fun addMovies(movie:MovieModel) = repository.addMovies(movie)

    fun createMovie(){
        if(!validateData()){
            status.value = WRONG_INFORMATION;
            return
        }

        addMovies(
            MovieModel(
                name.value!!,
                category.value!!,
                description.value!!,
                qualification.value!!
            )
        )
        clearData()

        status.value = MOVIE_CREATED
    }

    private fun validateData():Boolean{
        when{
            name.value.isNullOrBlank() -> return false
            category.value.isNullOrBlank() -> return false
            description.value.isNullOrBlank() -> return false
            qualification.value.isNullOrBlank() -> return false
        }
        return true
    }

    private fun clearData(){
        name.value = ""
        category.value = ""
        description.value = ""
        qualification.value = ""
    }

    fun clearStatus(){
        status.value = INACTIVE
    }

    fun setSelectedMovie(movie: MovieModel){
        name.value = movie.name;
        category.value = movie.category
        description.value = movie.description
        qualification.value = movie.qualification
    }

    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as MovieReviewerApplication
                MovieViewModel(app.movieRepository)
            }
        }

        const val MOVIE_CREATED = "Movie created"
        const val WRONG_INFORMATION = "Wrong information"
        const val INACTIVE = ""
    }

}