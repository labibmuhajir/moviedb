package com.github.labibmuhajir.moviedb.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.labibmuhajir.moviedb.extension.ImageSize
import com.github.labibmuhajir.moviedb.extension.loadUrl
import com.github.labibmuhajir.moviedb.service.model.Movie
import moviedb.databinding.ItemBannerBinding

class PosterAdapter : RecyclerView.Adapter<PosterAdapter.PosterHolder>() {
    var movies: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PosterHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            movie.backdropPath?.let { binding.ivBanner.loadUrl(it, ImageSize.w500) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PosterHolder(binding)
    }

    override fun onBindViewHolder(holder: PosterHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}