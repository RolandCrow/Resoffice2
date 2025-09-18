package com.bignerdranch.android.resoffice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.resoffice2.adapter.MyRegionAdapter
import com.bignerdranch.android.resoffice2.databinding.ActivityRegionBinding
import com.bignerdranch.android.resoffice2.repository.Repository
import com.bignerdranch.android.resoffice2.viewModel.RegionViewModel
import com.bignerdranch.android.resoffice2.viewModel.RegionViewModelFactory

class RegionActivity : AppCompatActivity() { // все тоже самое, что и в OfficeActivity
    private lateinit var binding: ActivityRegionBinding
    private lateinit var viewModel: RegionViewModel
    private val myRegionAdapter by lazy { MyRegionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region)
        setupRecyclerView()
        binding = ActivityRegionBinding.inflate(layoutInflater)
        val repository = Repository()
        val regionViewModelFactory = RegionViewModelFactory(repository)
        viewModel = ViewModelProvider(this, regionViewModelFactory).get(RegionViewModel::class.java)
        val coordinate = intent.getStringExtra("coordinate1")
        viewModel.getRegion2(coordinate!!) // добавляем к основному запросу полученные координаты
        viewModel.myResponse2.observe(this, Observer { response ->
            if(response.isSuccessful) {
                response.body()?.let { myRegionAdapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        } )
    }

    private fun setupRecyclerView() {
        binding.regionRecyclerView.adapter = myRegionAdapter
        binding.regionRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}