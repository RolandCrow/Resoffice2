package com.bignerdranch.android.resoffice2.api

import com.bignerdranch.android.resoffice2.api.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy { // инструменты для построения запроса
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val api: ResApi by lazy { // связываем с api
        retrofit.create(ResApi::class.java)
    }


}