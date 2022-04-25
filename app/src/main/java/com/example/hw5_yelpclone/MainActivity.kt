package com.example.hw5_yelpclone

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://api.yelp.com/v3/"
    private val API_KEY = "n719WaUhmolH0GN7xAxFqw_K7bxpjTME8o68ktoCUWZY9ATn0ESHCcVQd2Ck3FGT9h3EOuYF4RU5Ogvyk4nGf5odENo7l7d_JmprQMZjgmwxnURsGwiXTdJIXSRiYnYx"
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun search(view:View) {
        val location = findViewById<TextView>(R.id.location_search).text
        val food = findViewById<TextView>(R.id.food_search).text
//        val busId = findViewById<TextView>(R.id.food_id).text

        if (food.isEmpty()) {
            makeBuilder(
                "Search term missing",
                "Search term cannot be empty, Please enter a search term.",
                R.id.food_search
            )
            return

        }

        if (location.isEmpty()) {
            makeBuilder(
                "Location missing",
                "Location cannot be empty, Please enter a location.",
                R.id.location_search
            )
            return
        }

//        val reviewList = ArrayList<Review>()
        val businessList = ArrayList<Business>()
        val adapter = YelpAdapter(businessList)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val yelpApi = retrofit.create(YelpController::class.java)

        yelpApi.searchRestaraunts("Bearer $API_KEY", "$food", "$location").enqueue(object : Callback<YelpData> {
            override fun onFailure(call: Call<YelpData>, t: Throwable) {
                Log.d(TAG, "onFailure : $t")
            }

            override fun onResponse(call: Call<YelpData>, response: Response<YelpData>) {
                Log.d(TAG, "onResponse: $response")

                val body = response.body()

                if (body == null) {
                    Log.w(TAG, "Valid response was not received")
                    return
                }
                businessList.addAll(body.businesses)
                adapter.notifyDataSetChanged()
            }
        })
        findViewById<TextView>(R.id.location_search).hideKeyboard()
        findViewById<TextView>(R.id.food_search).hideKeyboard()
//        yelpApi.getReviews("Bearer $API_KEY", "$busId" /*or "${body.businesses.get(0).id}"*/).enqueue(object :
//            Callback<YelpData> {
//
//            override fun onFailure(call: Call<YelpData>, t: Throwable) {
//                Log.d(TAG, "onFailure : $t")
//            }
//
//            override fun onResponse(call: Call<YelpData>, response: Response<YelpData>) {
//                Log.d(TAG, "onResponse: $response")
//
//                val body1 = response.body()
//
//                if (body1 == null) {
//                    Log.w(TAG, "Valid response was not received")
//                    return
//                }
//                reviewList.addAll(body1.reviews)
//                adapter.notifyDataSetChanged()
//            }
//        })

    }
    private fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
    private fun makeBuilder(title : String, message: String, view: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNeutralButton("Okay"){dialog, which -> findViewById<EditText>(view).requestFocus() }
        builder.setIcon(android.R.drawable.ic_delete)
        val dialog = builder.create()
        dialog.show()
    }
}