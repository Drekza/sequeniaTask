package com.example.sequeniatask.api

import com.example.sequeniatask.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("")
    suspend fun getMoviesList(): Response<MoviesResponse>
}