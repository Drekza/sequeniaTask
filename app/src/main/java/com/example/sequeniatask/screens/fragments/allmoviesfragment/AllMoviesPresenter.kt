package com.example.sequeniatask.screens.fragments.allmoviesfragment

import com.example.sequeniatask.models.MoviesResponse
import com.example.sequeniatask.repository.MoviesRepository
import com.example.sequeniatask.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class AllMoviesPresenter (private val view: AllMoviesContract.View,
private val moviesRepository: MoviesRepository) : AllMoviesContract.Presenter {


    init{
        this.view.setPresenter(this)
    }


    override fun loadMovieList(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getMovieList()

            view.displayMovieList(handlingMovieListResponse(response))

        }
    }

    private fun handlingMovieListResponse(response: Response<MoviesResponse>): Resource<MoviesResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}