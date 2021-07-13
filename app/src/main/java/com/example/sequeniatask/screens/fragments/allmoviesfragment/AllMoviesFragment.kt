package com.example.sequeniatask.screens.fragments.allmoviesfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sequeniatask.R
import com.example.sequeniatask.adapter.MoviesAdapter
import com.example.sequeniatask.models.Genre
import com.example.sequeniatask.models.MoviesResponse
import com.example.sequeniatask.models.Subtitle
import com.example.sequeniatask.repository.MoviesRepository
import com.example.sequeniatask.utils.Resource
import kotlinx.android.synthetic.main.fragment_all_movies.*

class AllMoviesFragment : Fragment(R.layout.fragment_all_movies), AllMoviesContract.View {

    private lateinit var allMoviesPresenter: AllMoviesContract.Presenter
    lateinit var moviesAdapter: MoviesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Главная"

        val presenter = AllMoviesPresenter(this)
        setPresenter(presenter)
        allMoviesPresenter.attach(this)
        setUpRecyclerView()
        allMoviesPresenter.loadMovieList()

    }

    override fun displayMovieList(recyclerViewList: MutableList<Any>) {
        moviesAdapter.setRecyclerList(recyclerViewList)

    }

    private fun setUpRecyclerView() {
        moviesAdapter = MoviesAdapter()
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                val element = moviesAdapter.list?.get(position)
                return when(element){
                    is Genre, is Subtitle -> 2
                    else -> 1
                }
            }

        })

        rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = gridLayoutManager
        }
    }

    override fun setPresenter(presenter: AllMoviesContract.Presenter) {
        this.allMoviesPresenter = presenter
    }
}