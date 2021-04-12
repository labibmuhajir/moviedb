package com.github.labibmuhajir.moviedb.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.labibmuhajir.moviedb.extension.loadUrl
import com.github.labibmuhajir.moviedb.service.model.Movie
import moviedb.databinding.ItemSegmentedMovieBinding
import moviedb.databinding.ItemSegmentedTitleBinding

class SegmentedAdapter : RecyclerView.Adapter<SegmentedAdapter.SegmentedTitleHolder>() {
    var data: List<Pair<String, List<Movie>>> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SegmentedTitleHolder {
        val binding = ItemSegmentedTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SegmentedTitleHolder(binding)
    }

    override fun onBindViewHolder(holder: SegmentedTitleHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class SegmentedTitleHolder(private val binding: ItemSegmentedTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pair<String, List<Movie>>) {
            binding.tvTitle.text = data.first
            binding.rvMovie.adapter = SegmentedMovieAdapter(data.second)
        }
    }
}

class SegmentedMovieAdapter(private val data: List<Movie>) :
    RecyclerView.Adapter<SegmentedMovieAdapter.SegmentedMovieHolder>() {
    inner class SegmentedMovieHolder(private val binding: ItemSegmentedMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            movie.posterPath?.let { binding.ivPoster.loadUrl(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SegmentedMovieHolder {
        val binding =
            ItemSegmentedMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SegmentedMovieHolder(binding)
    }

    override fun onBindViewHolder(holder: SegmentedMovieHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}