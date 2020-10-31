package dev.wallacegs.flixter

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*
import java.util.*

class MoviesAdapter(val context: Context, val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            val imageUrl: String
            itemView.tvTitle.text = movie.title
            if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.formatBackdrop()
                itemView.tvOverview.text = movie.overview
            } else {
                imageUrl = movie.formatPoster()
                itemView.tvOverview.text = movie.getShortOverview()
            }
            Glide.with(context).load(imageUrl).into(itemView.ivPoster)
            itemView.container.setOnClickListener {
                val movieDetails = arrayOf<String>(movie.title, movie.overview, movie.rating.toString(), movie.id.toString())
                val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsActivity(movieDetails)
                itemView.findNavController().navigate(action)
            }
        }

    }
}