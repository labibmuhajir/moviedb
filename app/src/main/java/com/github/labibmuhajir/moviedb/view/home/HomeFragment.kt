package com.github.labibmuhajir.moviedb.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import moviedb.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val bannerAdapter by lazy { PosterAdapter() }
    private val segmentedAdapter by lazy { SegmentedAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vpBanner.adapter = bannerAdapter
        binding.rvMovie.adapter = segmentedAdapter
        viewModel.bannerLiveData.observe(viewLifecycleOwner) { bannerAdapter.movies = it }
        viewModel.segmentedLiveData.observe(viewLifecycleOwner) { segmentedAdapter.data = it }
        viewModel.initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}