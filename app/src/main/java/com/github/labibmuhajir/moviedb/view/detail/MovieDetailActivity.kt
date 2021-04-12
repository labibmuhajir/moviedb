package com.github.labibmuhajir.moviedb.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.labibmuhajir.moviedb.extension.viewBinding
import moviedb.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMovieDetailBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}