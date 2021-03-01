package com.example.ithub.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RestClient {
    private val BASE_URL = "https://waytojob.000webhostapp.com/"
    private var mRetrofit: Retrofit? = null
    val client: Retrofit
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return mRetrofit!!
        }
}
