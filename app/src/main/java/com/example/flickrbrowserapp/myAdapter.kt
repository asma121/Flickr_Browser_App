package com.example.flickrbrowserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class myAdapter(private val list:ArrayList<Photo>,val activity: MainActivity): RecyclerView.Adapter<myAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val id = list[position].id
        val title = list[position].title
        val secret = list[position].secret
        val server = list[position].server
        holder.itemView.apply {
            textView.text= title
            Glide.with(this)
                .load("https://farm66.staticflickr.com/${server}/${id}_${secret}.jpg")
                .centerCrop()
                .into(imageView)
            imageView.setOnClickListener {
                Glide.with(activity)
                    .load("https://farm66.staticflickr.com/${server}/${id}_${secret}.jpg")
                    .centerCrop()
                    .into(activity.imageView2)
                activity.imageView2.isVisible=true
            }
            activity.imageView2.setOnClickListener {
                activity.imageView2.isVisible=false
            }
        }
    }

    override fun getItemCount()=list.size
}