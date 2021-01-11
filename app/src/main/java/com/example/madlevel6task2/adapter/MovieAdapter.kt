package com.example.madlevel6task2.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task2.R
import com.example.madlevel6task2.model.ImageItem
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.model.MovieList
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: LiveData<MovieList>, private val onClick: (Movie) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.value?.results?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies.value?.results!![position])
        holder.itemView.tvMovieIndex.text = "${position + 1}."
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(movies.value?.results!![adapterPosition]) }
        }

        fun bind(movie: Movie) {
            Log.d("TAG", "binding movie: $movie")
            if (movie.posterPath != null) {
                Glide.with(context).load(ImageItem(movie.posterPath).getImageUrl()).into(itemView.ivMoviePoster)
            }
//            else if (movie.backdropPath != null) {
//                Glide.with(context).load(ImageItem(movie.backdropPath).getImageUrl()).into(itemView.ivMoviePoster)
//            }
        }
    }

}