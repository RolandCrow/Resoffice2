package com.bignerdranch.android.resoffice2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.resoffice2.repository.Repository

@Suppress("UNCHECKED_CAST")
class RegionViewModelFactory(private val repository: Repository): ViewModelProvider.Factory  { // // все тоже самое, что и в OfficeViewModelFactory

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegionViewModel(repository) as T
    }
}