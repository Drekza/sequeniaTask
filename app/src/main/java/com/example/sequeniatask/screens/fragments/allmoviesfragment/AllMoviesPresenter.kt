package com.example.sequeniatask.screens.fragments.allmoviesfragment

import com.example.sequeniatask.models.Genre
import com.example.sequeniatask.models.Film
import com.example.sequeniatask.models.MoviesResponse
import com.example.sequeniatask.models.Subtitle
import com.example.sequeniatask.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AllMoviesPresenter (private var view: AllMoviesContract.View?) : AllMoviesContract.Presenter {


    init{
        this.view?.setPresenter(this)
    }


    override fun loadMovieList(){
        val moviesRepository = MoviesRepository()

        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getMoviesList()
            if (response.isSuccessful){
                response.body()?.let { resultResponse ->
                    val list = resultResponse.films
                    withContext(Dispatchers.Main){
                        view?.displayMovieList(makeRecyclerViewList(list))
                    }
                }
            }
        }
    }



    fun makeRecyclerViewList(list: List<Film>): MutableList<Any>{
        val sortedList = list.sortedBy { it.localized_name }
        val recyclerViewList = mutableListOf<Any>()
        recyclerViewList.add(Subtitle("Жанры"))
        sortedList.forEach{ movie -> // adding genres
            val genres = movie.genres
            genres.forEach{
                val genre = Genre(it, false)
                if(genre !in recyclerViewList){
                    recyclerViewList.add(genre)
                }
            }
        }
        recyclerViewList.add(Subtitle("Фильмы"))
        sortedList.forEach{ movie -> // adding movies
            recyclerViewList.add(movie)
        }

        return recyclerViewList
    }

    override fun attach(view: AllMoviesFragment) {
        this.view = view
    }

    override fun onDestroy() {
        this.view = null
    }


}