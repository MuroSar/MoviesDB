package com.sartimau.moviesdb.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.sartimau.moviesdb.R
import com.sartimau.moviesdb.adapters.ViewPagerAdapter
import com.sartimau.moviesdb.fragments.PopularTabFragment
import com.sartimau.moviesdb.fragments.TopRatedTabFragment
import com.sartimau.moviesdb.fragments.UpcomingTabFragment
import com.sartimau.moviesdb.utils.LiveDataEvent
import com.sartimau.moviesdb.viewmodels.LoaderData
import com.sartimau.moviesdb.viewmodels.LoaderStatus
import com.sartimau.moviesdb.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.loader
import kotlinx.android.synthetic.main.activity_main.tabs
import kotlinx.android.synthetic.main.activity_main.viewpager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loaderState.observe(::getLifecycle, ::updateLoader)

        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PopularTabFragment(), PopularTabFragment.TITLE)
        adapter.addFragment(TopRatedTabFragment(), TopRatedTabFragment.TITLE)
        adapter.addFragment(UpcomingTabFragment(), UpcomingTabFragment.TITLE)
        viewPager.adapter = adapter
    }

    private fun updateLoader(loaderData: LiveDataEvent<LoaderData>) {
        when (loaderData.peekContent().loaderStatus) {
            LoaderStatus.SHOW -> {
                loader.visibility = View.VISIBLE
            }
            LoaderStatus.HIDE -> {
                loader.visibility = View.GONE
            }
        }
    }
}
