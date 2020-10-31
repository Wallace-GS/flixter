package dev.wallacegs.flixter

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.wallacegs.flixter.databinding.FragmentMoviesBinding
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MoviesFragment"
private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = mutableListOf<Movie>()
        val adapter = context?.let { MoviesAdapter(it, movies) }
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(context)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val movieService = retrofit.create(MovieService::class.java)

        movieService.fillApi(API_KEY).enqueue(object: Callback<MovieSearchResult> {
            override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
                response.body()?.movies?.let { movies.addAll(it) }
                adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }
        })
    }
}