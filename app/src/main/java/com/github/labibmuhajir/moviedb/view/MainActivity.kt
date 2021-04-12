package com.github.labibmuhajir.moviedb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.labibmuhajir.moviedb.extension.viewBinding
import com.github.labibmuhajir.moviedb.view.account.AccountFragment
import com.github.labibmuhajir.moviedb.view.favorite.FavoriteFragment
import com.github.labibmuhajir.moviedb.view.home.HomeFragment
import com.github.labibmuhajir.moviedb.view.popular.PopularFragment
import dagger.hilt.android.AndroidEntryPoint
import moviedb.R
import moviedb.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        homeAdapter = HomeAdapter(supportFragmentManager, lifecycle)

        with(binding) {
            viewPager.adapter = homeAdapter
            viewPager.isUserInputEnabled = false
            bottomNavigation.setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.menu_home -> {
                        viewPager.setCurrentItem(0, false)
                        true
                    }
                    R.id.menu_popular -> {
                        viewPager.setCurrentItem(1, false)
                        true
                    }
                    R.id.menu_favorite -> {
                        viewPager.setCurrentItem(2, false)
                        true
                    }
                    R.id.menu_account -> {
                        viewPager.setCurrentItem(3, false)
                        true
                    }
                    else ->  false
                }
            }
        }
    }
}

class HomeAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val pages = listOf(
        HomeFragment(), PopularFragment(), FavoriteFragment(), AccountFragment()
    )

    override fun getItemCount(): Int = pages.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun createFragment(position: Int): Fragment = pages[position]
}