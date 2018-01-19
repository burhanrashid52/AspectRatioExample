package ja.burhanrashid52.aspectratio

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val staggeredGridLayoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvPosters.layoutManager = staggeredGridLayoutManager
        val moviePosterAdapter = MoviePosterAdapter()
        rvPosters.adapter = moviePosterAdapter
        moviePosterAdapter.mMoviePosters = readMoviesDetails()
    }

    private fun readMoviesDetails(): List<MoviePoster> {
        val moviePosters = ArrayList<MoviePoster>()
        val inputStream = resources.openRawResource(R.raw.sample_data)
        val stream = InputStreamReader(inputStream)
        val response = stream.buffered().use(BufferedReader::readText)

        try {
            val movieArray = JSONArray(response)
            (0 until movieArray.length())
                    .map { movieArray.getJSONObject(it) }
                    .mapTo(moviePosters) {
                        MoviePoster(
                                it.getString("name"),
                                it.getString("imageUrl"),
                                it.getInt("width"),
                                it.getInt("height")
                        )
                    }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return moviePosters
    }
}
