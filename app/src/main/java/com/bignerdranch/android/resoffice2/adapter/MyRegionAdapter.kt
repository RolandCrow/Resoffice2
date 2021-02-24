package com.bignerdranch.android.resoffice2.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.resoffice2.MainActivity
import com.bignerdranch.android.resoffice2.R
import com.bignerdranch.android.resoffice2.RegionActivity
import com.bignerdranch.android.resoffice2.api.Region
import kotlinx.android.synthetic.main.activity_region.*
import kotlinx.android.synthetic.main.row_of_region.view.*

class MyRegionAdapter(): RecyclerView.Adapter<MyRegionAdapter.MyRegionViewHolder> (){

    private var myRegionList = emptyList<Region>() // пустой лист для информации



    inner class MyRegionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRegionViewHolder {
        return MyRegionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_of_region, parent, false))
    }

    override fun getItemCount(): Int {
      return myRegionList.size
    }

    override fun onBindViewHolder(holder: MyRegionViewHolder, position: Int) {
        holder.itemView.nameRegion.text = myRegionList[position].SNAME // иницирование из recyclerview в linearLayout
        holder.itemView.numberRegion.text = myRegionList[position].ID.toString() // иницирование из recyclerview в linearLayout



         val saveData: String = holder.itemView.numberRegion.text as String // сохраняем номер региона

        val intentToActivity = Intent(holder.itemView.context, MainActivity::class.java )
        intentToActivity.putExtra("saveData", saveData)
        holder.itemView.context.startActivity(intentToActivity) // посылаем номера региона в главную активность






    }

    fun setData(newList: List<Region>) { // поставляем информацию в лист
        myRegionList = newList
        notifyDataSetChanged()



    }

}