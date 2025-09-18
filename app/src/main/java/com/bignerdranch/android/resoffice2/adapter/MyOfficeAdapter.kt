package com.bignerdranch.android.resoffice2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.resoffice2.api.Office
import com.bignerdranch.android.resoffice2.databinding.ListOfficesBinding

class MyOfficeAdapter: RecyclerView.Adapter<MyOfficeAdapter.MyOfficeViewHolder>()  { // все тоже самое, что и в MyRegionAdapter

    private var myOfficeList = emptyList<Office>()

    inner class MyOfficeViewHolder(private val binding: ListOfficesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(office: Office) {
            binding.nameOffice.text = office.SSHORTNAME
            binding.addressOffices.text = office.SADDRESS
            binding.timeWorking.text = office.SGRAF
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOfficeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListOfficesBinding.inflate(inflater, parent, false)
        return MyOfficeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myOfficeList.size
    }

    override fun onBindViewHolder(holder: MyOfficeViewHolder, position: Int) {
        holder.bind(myOfficeList[position])
    }

    fun setData(newList: List<Office>) {
        myOfficeList = newList
        notifyDataSetChanged()
    }
}