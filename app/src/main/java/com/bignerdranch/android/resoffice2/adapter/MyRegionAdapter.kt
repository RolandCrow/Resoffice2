package com.bignerdranch.android.resoffice2.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.resoffice2.MainActivity
import com.bignerdranch.android.resoffice2.api.Region
import com.bignerdranch.android.resoffice2.databinding.RowOfRegionBinding

class MyRegionAdapter() : RecyclerView.Adapter<MyRegionAdapter.MyRegionViewHolder>() {
    private var myRegionList = emptyList<Region>()
    inner class MyRegionViewHolder(private val binding: RowOfRegionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val saveData: String = binding.numberRegion.text as String
                val intentToActivity = Intent(itemView.context, MainActivity::class.java )
                intentToActivity.putExtra("saveData", saveData)
                itemView.context.startActivity(intentToActivity)
            }
        }

        fun bind(region: Region) {
            binding.nameRegion.text = region.SNAME
            binding.numberRegion.text = region.ID.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRegionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowOfRegionBinding.inflate(inflater, parent, false)
        return MyRegionViewHolder(binding)
    }

    override fun getItemCount(): Int = myRegionList.size

    override fun onBindViewHolder(holder: MyRegionViewHolder, position: Int) {
        holder.bind(myRegionList[position])
    }

    fun setData(newList: List<Region>) {
        myRegionList = newList
        notifyDataSetChanged()
    }
}