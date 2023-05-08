package com.uca.laboratorio5.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uca.laboratorio5.R;
import com.uca.laboratorio5.data.model.MovieModel
import com.uca.laboratorio5.databinding.FragmentFirstBinding
import com.uca.laboratorio5.databinding.FragmentNewMovieBinding
import com.uca.laboratorio5.ui.recyclerview.MovieRecyclerViewAdapter

// TODO: Rename parameter arguments, choose names that match

lateinit var actionBtn: FloatingActionButton
class FirstFragment : Fragment() {
    private val viewModel: MovieViewModel by activityViewModels {
        MovieViewModel.Factory
    }
    private lateinit var adapter: MovieRecyclerViewAdapter
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setRecyclerView(view)

        binding.actionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_firstFragment_to_newFragment)
        }
    }

    private fun setViewModel(){
        binding.viewmodel = viewModel
    }
    private fun showSelectedItem(movie: MovieModel){
        viewModel.setSelectedMovie(movie)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
    }

    private fun displayMovies(){
        Log.d("USERS -> ",viewModel.getMovies().toString())
        adapter.setData(viewModel.getMovies())
        adapter.notifyDataSetChanged()
    }

    private fun setRecyclerView(view: View){

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter = MovieRecyclerViewAdapter {
                selectedMovie ->
            showSelectedItem(selectedMovie)
        }

        binding.recyclerView.adapter = adapter

        displayMovies()
    }
}