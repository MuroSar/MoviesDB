package com.sartimau.moviesdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sartimau.moviesdb.R

class PopularTabFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_tab, container, false)
    }

    companion object {
        const val TITLE = "Popular"
    }
}
