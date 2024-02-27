package com.example.bettervicemusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val  retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getDate(query = "eminem")

        // after enter the retrofitData.enqueue() use ctrl + Shift + space

       retrofitData.enqueue(object : Callback<myData?> {
           override fun onResponse(call: Call<myData?>, response: Response<myData?>) {
     //if API request is success this block is execute
               val dataList = response.body()?.total
               val textView = findViewById<TextView>(R.id.textHello)
               textView.text =dataList.toString()
               Log.d("Tag: onResponse", "onResponse: " + response.body())
           }

           override fun onFailure(call: Call<myData?>, t: Throwable) {
         // if call failed
                Log.d("Tag:onFailure" ,"onFailure" + t.message)
           }
       })

    }
}