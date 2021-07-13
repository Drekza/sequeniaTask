package com.example.sequeniatask.screens.fragments.allmoviesfragment

import com.example.sequeniatask.BasePresenter
import com.example.sequeniatask.BaseView
import com.example.sequeniatask.models.Movie

interface AllMoviesContract {

    interface Presenter : BasePresenter{
        fun loadMovieList()
    }

    interface View : BaseView<Presenter>{
        fun displayMovieList(movieList: List<Movie>)
    }
}