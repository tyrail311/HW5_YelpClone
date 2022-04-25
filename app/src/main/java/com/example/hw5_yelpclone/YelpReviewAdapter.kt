//package com.example.hw5_yelpclone
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.RatingBar
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class YelpReviewAdapter(private val reviews: ArrayList<Review>) : RecyclerView.Adapter<YelpReviewAdapter.MyViewHolder>() {
//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val review = itemView.findViewById<TextView>(R.id.review_text)
//        val rating = itemView.findViewById<RatingBar>(R.id.review_rating)
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YelpReviewAdapter.MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = reviews[position]
//        holder.review.text = currentItem.text
//        holder.rating.rating = currentItem.rating
//    }
//
//    override fun getItemCount(): Int {
//        return reviews.size
//    }
//}
//
