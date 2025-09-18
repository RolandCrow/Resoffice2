package com.bignerdranch.android.resoffice2.repository

import com.bignerdranch.android.resoffice2.api.Office
import com.bignerdranch.android.resoffice2.api.RetrofitInstance
import retrofit2.Response

class RepositoryOffice {
    suspend fun getOffices(region: String): Response<List<Office>> { // // соединяем апи с дата классами
        return RetrofitInstance.api.getOffices(region)
    }
}