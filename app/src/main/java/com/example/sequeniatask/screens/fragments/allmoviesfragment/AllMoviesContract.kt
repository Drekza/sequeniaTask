package com.example.sequeniatask.screens.fragments.allmoviesfragment

import com.example.sequeniatask.BasePresenter
import com.example.sequeniatask.BaseView

interface AllMoviesContract {

    interface Presenter : BasePresenter<AllMoviesFragment>{
        fun loadMovieList()
    }

    interface View : BaseView<Presenter>{
        fun displayMovieList(recyclerViewList: MutableList<Any>)
    }
}