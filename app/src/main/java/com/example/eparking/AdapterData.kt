package com.example.eparking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterData(private var userArrayList: ArrayList<TableResponse>) :
    RecyclerView.Adapter<AdapterData.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_dashboard_jukir, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tvUserName.text = userArrayList[i].no_plat
        viewHolder.tvAge.text = userArrayList[i].status.toString()
        viewHolder.tvDesignation.text = userArrayList[i].payment_type
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserName: TextView = itemView.findViewById(R.id.NoPlatData)
        val tvAge: TextView = itemView.findViewById(R.id.statusData)
        val tvDesignation: TextView = itemView.findViewById(R.id.hargaParkir)


    }

    fun setData(dataList: List<TableResponse>) {
        userArrayList.clear()
        userArrayList.addAll(dataList)
        notifyDataSetChanged()
    }

}