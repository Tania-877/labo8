package com.uca.laboratorio5.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.uca.laboratorio5.R
import com.uca.laboratorio5.data.model.MovieModel
import com.uca.laboratorio5.databinding.FragmentNewMovieBinding

/**
 * A simple [Fragment] subclass.
 * Use the [newMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class newMovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null/*
    private lateinit var txtName: EditText
    private lateinit var txtCategory: EditText
    private lateinit var txtDescription: EditText
    private lateinit var txtCalification: EditText
    private lateinit var actionBtn: Button*/
    private lateinit var binding:FragmentNewMovieBinding

    private val viewModel: MovieViewModel by activityViewModels {
        MovieViewModel.Factory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        observeStatus()
    }

    private fun setViewModel(){
        binding.viewmodel = viewModel
    }

    private fun observeStatus(){
        viewModel.status.observe(viewLifecycleOwner){
            status ->
            when{
                status.equals(MovieViewModel.WRONG_INFORMATION) ->{
                    Log.d("LOG", status)
                    viewModel.clearStatus()
                }
                status.equals(MovieViewModel.MOVIE_CREATED) ->{
                    Log.d("STATUS", status)
                    Log.d("PELICULAS", viewModel.getMovies().toString())

                    viewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment newMovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            newMovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}