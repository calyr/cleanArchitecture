package com.calyrsoft.cleanmovieapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calyrsoft.cleanmovieapp.R
import com.calyrsoft.domain.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_movie.view.*

class MainAdapter(val list: List<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie, parent, false)
        return MainViewHolder(view)
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = list.get(position)
        holder.itemView.movie_title.text = movie.title
        Picasso.get().load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
            .into(holder.itemView.movie_image)
//        holder.itemView.movie_image =
    }
}
