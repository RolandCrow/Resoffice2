package com.bignerdranch.android.resoffice2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.resoffice2.repository.Repository
import com.bignerdranch.android.resoffice2.repository.RepositoryOffice


@Suppress("UNCHECKED_CAST")
class OfficeViewModelFactory(private val repositoryOffice: RepositoryOffice): ViewModelProvider.Factory  { // апи с дата классами присоединяются к ViewModel

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OfficeViewModel(repositoryOffice) as T
    }



}