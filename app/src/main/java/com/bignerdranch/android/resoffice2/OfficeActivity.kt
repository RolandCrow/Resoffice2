package com.bignerdranch.android.resoffice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.resoffice2.adapter.MyOfficeAdapter
import com.bignerdranch.android.resoffice2.databinding.ActivityOfficeBinding
import com.bignerdranch.android.resoffice2.repository.RepositoryOffice
import com.bignerdranch.android.resoffice2.viewModel.OfficeViewModel
import com.bignerdranch.android.resoffice2.viewModel.OfficeViewModelFactory

class OfficeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOfficeBinding
    private lateinit var viewModel: OfficeViewModel // присоединяем ViewModel с данными api
    private val myOfficeAdapter by lazy { MyOfficeAdapter() } // иницируем адаптер recyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_office)
        binding = ActivityOfficeBinding.inflate(layoutInflater)
        setupRecyclerView() // устанавливаем recyclerView
        val repository = RepositoryOffice()  // добавляем репозиторий
        val officeViewModelFactory = OfficeViewModelFactory(repository) // присоединяем ViewModel
        viewModel = ViewModelProvider(this, officeViewModelFactory).get(OfficeViewModel::class.java)
        val region = intent.getStringExtra("numbReg") // получаем информацию из интента (номер региона)
        viewModel.getOffices(region!!) // присоединяем к запросу
        viewModel.myResponseOfOffice.observe(this, Observer {  response ->
            if(response.isSuccessful) {
                response.body()?.let { myOfficeAdapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() { // установка recyclerview
        binding.officeActivityRC.adapter = myOfficeAdapter
        binding.officeActivityRC.layoutManager = LinearLayoutManager(this)
    }
}
