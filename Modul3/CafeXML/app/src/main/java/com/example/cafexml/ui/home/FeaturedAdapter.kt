package com.example.cafexml.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cafexml.R
import com.example.cafexml.data.Cafe
import com.example.cafexml.databinding.ItemCafeHorizontalBinding

class FeaturedAdapter(private val list: List<Cafe>) : RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCafeHorizontalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCafeHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.binding.apply {
            tvName.text = data.name
            tvDesc.text = data.desc
            imgCafe.setImageResource(data.image)

            btnMaps.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.mapsUrl))
                it.context.startActivity(intent)
            }

            btnDetail.setOnClickListener { view ->
                val bundle = Bundle().apply {
                    putString("name", data.name)
                    putString("desc", data.desc)
                    putInt("image", data.image)
                    putString("rating", data.rating)
                }
                view.findNavController().navigate(R.id.action_home_to_detail, bundle)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}