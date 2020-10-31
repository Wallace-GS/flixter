package dev.wallacegs.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = mutableListOf<Movie>()
        val adapter = MoviesAdapter(this, movies)
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val movieService = retrofit.create(MovieService::class.java)

        movieService.fillApi(API_KEY).enqueue(object: Callback<MovieSearchResult>{
            override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
                response.body()?.movies?.let { movies.addAll(it) }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }
        })
    }
}