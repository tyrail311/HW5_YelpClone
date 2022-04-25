package com.example.hw5_yelpclone

data class YelpData (
    val businesses: List<Business>,
//    val reviews: List<Review>
)

//data class Review(
//    val text: String,
//    val rating: Float
//)

data class Business(
    val rating: Float,
    val categories: List<Categories>,
    val price: String,
    val location: Location,
    val name: String,
    val distance: Float,
    val review_count: Int,
    val image_url: String,
    val id: String
    )

data class Categories(
    val title: String
)

data class Location(
    val address1: String
)

