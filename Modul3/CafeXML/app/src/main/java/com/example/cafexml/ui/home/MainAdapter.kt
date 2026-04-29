package com.example.cafexml.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafexml.R
import com.example.cafexml.data.Cafe
import com.example.cafexml.databinding.ItemCafeVerticalBinding
import com.example.cafexml.databinding.ItemHorizontalContainerBinding

class MainAdapter(private val allList: List<Cafe>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    private val featuredList = allList.filter { it.isFeatured }

    override fun getItemViewType(position: Int): Int =
        if (position == 0) TYPE_HEADER else TYPE_ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_HEADER) {
            HeaderViewHolder(ItemHorizontalContainerBinding.inflate(inflater, parent, false))
        } else {
            ItemViewHolder(ItemCafeVerticalBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.binding.rvHorizontal.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = FeaturedAdapter(featuredList)
            }
        } else if (holder is ItemViewHolder) {
            holder.bind(allList[position - 1])
        }
    }

    override fun getItemCount(): Int = allList.size + 1

    inner class ItemViewHolder(val binding: ItemCafeVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Cafe) {
            binding.tvName.text = data.name
            binding.tvDesc.text = data.desc
            binding.tvRating.text = "⭐ ${data.rating}"
            binding.imgCafe.setImageResource(data.image)

            binding.btnMaps.setOnClickListener { view ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.mapsUrl))
                view.context.startActivity(intent)
            }

            binding.btnDetail.setOnClickListener { view ->
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

    class HeaderViewHolder(val binding: ItemHorizontalContainerBinding) : RecyclerView.ViewHolder(binding.root)
}