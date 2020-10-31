package dev.wallacegs.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.navArgs
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import dev.wallacegs.flixter.databinding.ActivityDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val YT_API_KEY = "AIzaSyA1qgKoN7Q-zrH0Yc0RlbgoGszW2ZMDlf0"
private const val MOVIESDB_API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val TAG = "DetailsActivity"

class DetailsActivity : YouTubeBaseActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val args: DetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = args.movieDetails[0]
        binding.tvOverview.text = args.movieDetails[1]
        binding.ratingBar.rating = args.movieDetails[2].toFloat()
        val id = args.movieDetails[3]
        val baseUrl = "https://api.themoviedb.org/3/movie/$id/"

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val movieService = retrofit.create(MovieService::class.java)

        movieService.fillVidApi(MOVIESDB_API_KEY).enqueue(object: Callback<VideoSearchResult> {
            override fun onResponse(call: Call<VideoSearchResult>, response: Response<VideoSearchResult>) {
                val ytKey = response.body()?.videos?.get(0)?.key!!
                initYoutube(ytKey)
            }

            override fun onFailure(call: Call<VideoSearchResult>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }
        })
    }

    private fun initYoutube(ytKey: String) {
        val obj = object : OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                if (!p2) {
                    p1?.cueVideo(ytKey)
                }
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Log.e(TAG, "Error: $p1")
            }
        }

        binding.player.initialize(YT_API_KEY, obj)
    }


}