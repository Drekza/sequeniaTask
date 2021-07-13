package com.example.sequeniatask.api

import com.example.sequeniatask.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("sequeniatesttask/films.json")
    suspend fun getMoviesList(): Response<MoviesResponse>
}