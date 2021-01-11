package com.example.madlevel6task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel6task2.R
import com.example.madlevel6task2.adapter.MovieAdapter
import com.example.madlevel6task2.databinding.FragmentMoviesBinding
import com.example.madlevel6task2.vm.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by activityViewModels()
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(viewModel.movies) {
            // add selected movie to arguments and start MovieDetailFragment
            val bundle = Bundle().apply { putParcelable(KEY_MOVIE, it) }

            val fragment = MovieDetailFragment()
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }

        observeMovies()

        binding.btnSubmit.setOnClickListener {
            viewModel.getMoviesOfYear(binding.etYear.text.toString().toInt())
        }

        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        movieAdapter.notifyDataSetChanged()
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, {
            movieAdapter.notifyDataSetChanged()
        })

        // Observe the error message.
        viewModel.errorText.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

}