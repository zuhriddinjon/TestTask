package com.example.testtask.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.databinding.ItemHealthyWithTabiBinding
import com.example.testtask.ui.home.model.PopularHealthTipsItem
import com.example.testtask.utils.getAssetUri

class PopularHealthTipAdapter(private val onItemClicked: (PopularHealthTipsItem) -> Unit) :
    ListAdapter<PopularHealthTipsItem, PopularHealthTipAdapter.PopularHealthTipViewHolder>(
        HealthTipDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHealthTipViewHolder {
        val binding =
            ItemHealthyWithTabiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularHealthTipViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: PopularHealthTipViewHolder, position: Int) {
        val popularCommunityItem = getItem(position)
        holder.bind(popularCommunityItem)
    }

    class PopularHealthTipViewHolder(
        private val binding: ItemHealthyWithTabiBinding,
        private val onItemClicked: (PopularHealthTipsItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: PopularHealthTipsItem

        init {
            binding.root.setOnClickListener { onItemClicked.invoke(model) }
        }

        fun bind(item: PopularHealthTipsItem) {
            this.model = item
            binding.txtTitle.text = item.title
            try {
                Glide.with(binding.root.context)
                    .load(getAssetUri("images/${item.image}.png"))
                    .into(binding.ivImage)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    class HealthTipDiffCallback : DiffUtil.ItemCallback<PopularHealthTipsItem>() {
        override fun areItemsTheSame(
            oldItem: PopularHealthTipsItem,
            newItem: PopularHealthTipsItem,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PopularHealthTipsItem,
            newItem: PopularHealthTipsItem,
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}
