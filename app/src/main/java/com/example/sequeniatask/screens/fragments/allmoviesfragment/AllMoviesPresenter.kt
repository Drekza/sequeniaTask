package com.example.sequeniatask.screens.fragments.allmoviesfragment

import com.example.sequeniatask.repository.MoviesRepository

class AllMoviesPresenter (private val view: AllMoviesContract.View,
private val moviesRepository: MoviesRepository) : AllMoviesContract.Presenter {


    init{
        this.view.setPresenter(this)
    }

    
    override fun loadMovieList() {

    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}