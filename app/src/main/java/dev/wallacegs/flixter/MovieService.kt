package dev.wallacegs.flixter

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    fun fillApi(
        @Query("api_key") apiKey: String
    ) : Call<MovieSearchResult>
}