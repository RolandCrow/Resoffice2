package com.bignerdranch.android.resoffice2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.resoffice2.api.Office
import com.bignerdranch.android.resoffice2.repository.Repository
import com.bignerdranch.android.resoffice2.repository.RepositoryOffice
import kotlinx.coroutines.launch
import retrofit2.Response

class OfficeViewModel(private val repository: RepositoryOffice): ViewModel() {

    val myResponseOfOffice: MutableLiveData<Response<List<Office>>> = MutableLiveData()  // LiveData для обхода основного потока

    fun getOffices(region: String) {
        viewModelScope.launch {
            val response = repository.getOffices(region) // запускаем viewModel для получения данных
            myResponseOfOffice.value = response
        }
    }
}