package com.example.madlevel6task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.FragmentMovieDetailBinding
import com.example.madlevel6task2.databinding.FragmentMoviesBinding
import com.example.madlevel6task2.model.ImageItem
import com.example.madlevel6task2.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import java.lang.IllegalArgumentException

const val KEY_MOVIE = "movie"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var movie: Movie

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = arguments?.getParcelable(KEY_MOVIE)
            ?: throw IllegalArgumentException("movie not sent as argument!")

        initViews()
    }

    private fun initViews() {
        // load images
        Glide.with(requireContext()).load(ImageItem(movie.posterPath!!).getImageUrl()).into(binding.ivMoviePoster)
        Glide.with(requireContext()).load(ImageItem(movie.backdropPath!!).getImageUrl()).into(binding.ivBackdrop)

        binding.tvTitle.text = movie.title
        binding.tvOverviewText.text = movie.overview
        binding.tvReleaseDate.text = movie.releaseDate
        binding.tvRating.text = movie.voteAverage.toString()
    }
}