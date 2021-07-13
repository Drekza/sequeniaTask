package com.example.sequeniatask.screens.fragments.movieinfofragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sequeniatask.R
import com.example.sequeniatask.models.Film
import kotlinx.android.synthetic.main.fragment_movie_info.*

class MovieInfoFragment : Fragment(R.layout.fragment_movie_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadMovieInfo()
    }

    private fun loadMovieInfo(){
        val args = arguments

        val movie = args?.getSerializable("Movie") as Film

        activity?.title = movie.localized_name
        Glide.with(this).load(movie.image_url).into(ivMovieImage)
        tvMovieName.text = movie.name
        tvMovieYear.text = movie.year.toString()
        tvMovieRating.text = movie.rating.toString()
        tvMovieDescription.text = movie.description
    }
}