package com.bignerdranch.android.resoffice2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.resoffice2.api.Region
import com.bignerdranch.android.resoffice2.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class RegionViewModel(private val repository: Repository): ViewModel() {

    val myResponse2: MutableLiveData<Response<List<Region>>> = MutableLiveData() // все тоже самое, что и в OfficeViewModel



    fun getRegion2(coordinate: String) {
        viewModelScope.launch {
            val response = repository.getRegion2(coordinate)
            myResponse2.value = response
        }
    }





    }


