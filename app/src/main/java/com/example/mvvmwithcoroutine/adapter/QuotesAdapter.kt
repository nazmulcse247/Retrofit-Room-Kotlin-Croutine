package com.example.mvvmwithcoroutine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmwithcoroutine.R
import com.example.mvvmwithcoroutine.model.Result

class QuotesAdapter : ListAdapter<Result,QuotesAdapter.QuotesViewHolder>(DiffUtil()){


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.quoteId == newItem.quoteId
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {

            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
       val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.quotes_item,parent,false)
        return QuotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val result = getItem(position)
        holder.authorTv.text = result.author
        holder.contentTv.text = result.content

    }

    inner class QuotesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val authorTv = itemView.findViewById<TextView>(R.id.authorTv)
        val contentTv = itemView.findViewById<TextView>(R.id.contentTv)

    }
}