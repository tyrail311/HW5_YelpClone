package com.example.hw5_yelpclone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class YelpAdapter(private val businesses: ArrayList<Business>) : RecyclerView.Adapter<YelpAdapter.MyViewHolder>() {
    inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
        val rating = itemView.findViewById<RatingBar>(R.id.ratingBar)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val reviews = itemView.findViewById<TextView>(R.id.review_number)
        val distance = itemView.findViewById<TextView>(R.id.distance)
        val address = itemView.findViewById<TextView>(R.id.address)
        val price = itemView.findViewById<TextView>(R.id.price)
        val type = itemView.findViewById<TextView>(R.id.type)
//        val food_id = itemView.findViewById<TextView>(R.id.food_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = businesses[position]
        holder.name.text = currentItem.name
        holder.rating.rating = currentItem.rating
        holder.reviews.text = "${currentItem.review_count} Reviews"
        holder.distance.text = "${String.format("%.2f", currentItem.distance*0.000621371)} mi"
        holder.address.text = currentItem.location.address1
        holder.price.text = currentItem.price
        holder.type.text = currentItem.categories[0].title
//        holder.food_id.text = currentItem.id

        val context = holder.itemView.context
        Glide.with(context)
            .load(currentItem.image_url)
            .placeholder(R.drawable.ic_baseline_no_food_24)
            .into(holder.image)
    }

        override fun getItemCount(): Int {
        return businesses.size
    }
}