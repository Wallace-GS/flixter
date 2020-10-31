package dev.wallacegs.flixter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.youtube.player.YouTubePlayerFragment
import dev.wallacegs.flixter.databinding.FragmentDetailsBinding

private const val YT_API_KEY = "AIzaSyA1qgKoN7Q-zrH0Yc0RlbgoGszW2ZMDlf0"
private const val VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class DetailsFragment : Fragment(){

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        arguments?.let {
//            val args = DetailsFragmentArgs.fromBundle(it)
//            binding.tvTitle.text = args.movieDetails[0]
//            binding.tvOverview.text = args.movieDetails[1]
//            binding.ratingBar.rating = args.movieDetails[2].toFloat()
//        }

    }
}