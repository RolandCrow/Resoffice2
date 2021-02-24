package com.bignerdranch.android.resoffice2.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ResApi {


    @GET("myregion/{coordinate}") // определяем регион
    suspend fun getRegion2( // данные получаем в обход основного потока через корутины
        @Path("coordinate") coordinate: String // вставляем полученные координаты
    ): Response<List<Region>>


    @GET("agencies/{region}") // определяем офисы
    suspend fun getOffices(
        @Path("region") region: String
    ): Response<List<Office>>




}