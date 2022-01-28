package com.example.sampleapplistdetail.presentation.movies_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplistdetail.databinding.ItemMoviesListBinding
import com.example.sampleapplistdetail.domain.model.MoviesListItem

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.MyViewHolder>() {

    private var listener :((MoviesListItem)->Unit)?=null

    var list = mutableListOf<MoviesListItem>()

    fun setContentList(list: MutableList<MoviesListItem>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ItemMoviesListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            ItemMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(MoviesListItem)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.movie = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}