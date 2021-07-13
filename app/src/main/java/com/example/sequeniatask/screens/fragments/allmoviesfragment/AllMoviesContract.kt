package com.example.sequeniatask.screens.fragments.allmoviesfragment

import com.example.sequeniatask.BasePresenter
import com.example.sequeniatask.BaseView
import com.example.sequeniatask.models.Movie
import com.example.sequeniatask.models.MoviesResponse
import com.example.sequeniatask.utils.Resource

interface AllMoviesContract {

    interface Presenter : BasePresenter{
        fun loadMovieList()
    }

    interface View : BaseView<Presenter>{
        fun displayMovieList(moviesResponse: Resource<MoviesResponse>)
    }
}