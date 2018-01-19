package ja.burhanrashid52.aspectratio

import android.annotation.SuppressLint
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MoviePosterAdapter : RecyclerView.Adapter<MoviePosterAdapter.ViewHolder>() {

    private val set = ConstraintSet()
    private val requestOptions = RequestOptions().placeholder(R.drawable.placeholder)
    var mMoviePosters: List<MoviePoster> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_poster, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val poster = mMoviePosters[position]

        holder.mMovieName.text = poster.name

        Glide.with(holder.itemView.context)
                .setDefaultRequestOptions(requestOptions)
                .load(poster.imageUrl)
                .into(holder.mImgPoster)

        val posterRatio = String.format("%d:%d", poster.width, poster.height)
        set.clone(holder.mConstraintLayout)
        set.setDimensionRatio(holder.mImgPoster.id, posterRatio)
        set.applyTo(holder.mConstraintLayout)
    }

    override fun getItemCount(): Int {
        return mMoviePosters.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.parentContsraint)
        val mImgPoster: ImageView = itemView.findViewById(R.id.imgSource)
        val mMovieName: TextView = itemView.findViewById(R.id.txtName)

    }
}
