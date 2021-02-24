package com.bignerdranch.android.resoffice2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.resoffice2.R
import com.bignerdranch.android.resoffice2.api.Office
import kotlinx.android.synthetic.main.list_offices.view.*

class MyOfficeAdapter: RecyclerView.Adapter<MyOfficeAdapter.MyOfficeViewHolder>()  { // все тоже самое, что и в MyRegionAdapter

    private var myOfficeList = emptyList<Office>()

    inner class MyOfficeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOfficeViewHolder {
        return MyOfficeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_offices, parent, false))
    }

    override fun getItemCount(): Int {
        return myOfficeList.size
    }

    override fun onBindViewHolder(holder: MyOfficeViewHolder, position: Int) {
        holder.itemView.nameOffice.text = myOfficeList[position].SSHORTNAME
        holder.itemView.addressOffices.text = myOfficeList[position].SADDRESS
        holder.itemView.time_working.text = myOfficeList[position].SGRAF
    }

    fun setData(newList: List<Office>) {
        myOfficeList = newList
        notifyDataSetChanged()
    }


}