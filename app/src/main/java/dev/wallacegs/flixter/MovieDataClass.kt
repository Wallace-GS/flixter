package dev.wallacegs.flixter

import com.google.gson.annotations.SerializedName

private const val MAX_LEN = 160;
data class MovieSearchResult(
    @SerializedName("results") val movies: List<Movie>
)

data class Movie(
    val overview: String,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float
) {
    fun formatPoster() = "https://image.tmdb.org/t/p/w342/$posterPath"
    fun formatBackdrop() = "https://image.tmdb.org/t/p/w342/$backdropPath"
    fun getShortOverview(): String {
        if (overview.length >= MAX_LEN) {
            val index = overview.indexOf(' ', MAX_LEN)
            return overview.substring(0, index) + " ..."
        }

        return overview
    }
}