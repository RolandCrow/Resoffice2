package com.bignerdranch.android.resoffice2.repository

import com.bignerdranch.android.resoffice2.api.Region
import com.bignerdranch.android.resoffice2.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getRegion2(coordinate: String): Response<List<Region>> { // соединяем апи с дата классами
        return RetrofitInstance.api.getRegion2(coordinate)
    }
}