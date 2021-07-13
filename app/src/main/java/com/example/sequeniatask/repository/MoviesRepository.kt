package com.example.sequeniatask.repository

import com.example.sequeniatask.api.RetrofitInstance

class MoviesRepository {
    suspend fun getMoviesList() = RetrofitInstance.api.getMoviesList()
}