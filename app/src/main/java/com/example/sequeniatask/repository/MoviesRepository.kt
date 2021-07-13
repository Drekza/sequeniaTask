package com.example.sequeniatask.repository

import com.example.sequeniatask.api.RetrofitInstance

class MoviesRepository {
    suspend fun getMovieList() = RetrofitInstance.api.getMoviesList()
}