package com.example.parcial3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FarmaciaAdapter(private val farmacias: List<Farmacia>) : RecyclerView.Adapter<FarmaciaAdapter.FarmaciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmaciaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_farmacia, parent, false)
        return FarmaciaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FarmaciaViewHolder, position: Int) {
        val farmacia = farmacias[position]
        holder.nameTextView.text = farmacia.name
        holder.addressTextView.text = farmacia.address
        holder.phoneTextView.text = farmacia.phone
    }

    override fun getItemCount(): Int {
        return farmacias.size
    }

    class FarmaciaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.farmaciaName)
        val addressTextView: TextView = itemView.findViewById(R.id.farmaciaAddress)
        val phoneTextView: TextView = itemView.findViewById(R.id.farmaciaPhone)
    }
}
