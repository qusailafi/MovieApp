package com.example.movie.presenter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.AdapterMoviesBinding
import com.example.movie.domain.entity.Result
import com.example.movie.presenter.ui.listener.MovieListener
//AdapterMovies used to display movies in recycler view
class AdapterMovies(var result: ArrayList<Result>,var item: MovieListener) :
    RecyclerView.Adapter<AdapterMovies.MoviesAdapter>() {


    class MoviesAdapter(binding: AdapterMoviesBinding,item: MovieListener) :
        RecyclerView.ViewHolder(binding.root) {
        var binding = binding
        var item: MovieListener=item

        fun bind(result: Result) {
            binding.result = result
            binding.root.setOnClickListener {
                item.onMoviewClick(result)
            }
             binding.executePendingBindings()//Used to bind the data to layout via data binding


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter {
        var binding: AdapterMoviesBinding =
            AdapterMoviesBinding.inflate(LayoutInflater.from(parent.context))

        return MoviesAdapter(binding,item)
    }

    override fun onBindViewHolder(holder: MoviesAdapter, position: Int) {
        holder.bind(result[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return result.size
    }

    fun updateAdapter(list:ArrayList<Result>){
        this.result.addAll(list)
        notifyDataSetChanged()

    }
}