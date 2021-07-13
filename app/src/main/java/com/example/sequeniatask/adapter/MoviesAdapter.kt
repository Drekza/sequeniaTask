package com.example.sequeniatask.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sequeniatask.R
import com.example.sequeniatask.models.Genre
import com.example.sequeniatask.models.Film
import com.example.sequeniatask.models.Subtitle
import com.example.sequeniatask.screens.MoviesActivity
import com.example.sequeniatask.utils.Constants.Companion.VIEW_TYPE_GENRE
import com.example.sequeniatask.utils.Constants.Companion.VIEW_TYPE_MOVIE
import com.example.sequeniatask.utils.Constants.Companion.VIEW_TYPE_SUBTITLE
import kotlinx.android.synthetic.main.item_genre.view.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_subtitle.view.*
import org.w3c.dom.Text

class MoviesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<Any>? = null


    private inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val genreBtn: RadioButton = itemView.genreBtn
    }

    private inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivMovieImage: ImageView = itemView.ivMovieImage
        val tvMovieName: TextView = itemView.tvMovieName
    }

    private inner class SubtitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvSubtitle: TextView = itemView.tvSubTitle
    }

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == VIEW_TYPE_GENRE){
            val view = inflater.inflate(R.layout.item_genre, parent, false)
            val viewHolder = GenreViewHolder(view)
            viewHolder
        } else if (viewType == VIEW_TYPE_MOVIE){
            val view = inflater.inflate(R.layout.item_movie, parent, false)
            val viewHolder = MovieViewHolder(view)
            viewHolder
        } else {
            val view = inflater.inflate(R.layout.item_subtitle, parent, false)
            val viewHolder = SubtitleViewHolder(view)
            viewHolder
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(list?.get(position)){
            is Genre -> VIEW_TYPE_GENRE
            is Film -> VIEW_TYPE_MOVIE
            else -> VIEW_TYPE_SUBTITLE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MovieViewHolder -> {
                val movie = list?.get(position) as Film
                holder.itemView.apply {
                    Glide.with(this).load(movie.image_url).into(ivMovieImage)
                    tvMovieName.text = movie.localized_name
                    ivMovieImage.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putSerializable("Movie", movie)
                        it.findNavController().navigate(R.id.action_allMoviesFragment_to_movieInfoFragment, bundle)
                    }
                }
            }
            is GenreViewHolder -> {
                val genre = list?.get(position) as Genre
                holder.itemView.apply {
                    genreBtn.text = genre.genre
                    genreBtn.isChecked = genre.isSelected
                    genreBtn.setOnClickListener {

                        genre.isSelected = true

                        val iterator = list?.listIterator()

                        while(iterator?.hasNext() == true){
                            val element = iterator.next()
                            if(element is Genre && element != genre){
                                element.isSelected = false
                            }
                            if(element is Film && genre.genre !in element.genres){
                                iterator.remove()
                            }
                        }
                        notifyDataSetChanged()

                    }
                }

            }
            is SubtitleViewHolder -> {
                val subtitle = list?.get(position) as Subtitle
                holder.itemView.apply {
                    tvSubTitle.text = subtitle.subtitle
                }
            }
        }
    }

    fun setRecyclerList(list: MutableList<Any>){
        this.list = list
        notifyDataSetChanged()
    }



    override fun getItemCount() = list?.size ?: 0


}