package com.example.testtask.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.databinding.ItemPopularCommunityBinding
import com.example.testtask.ui.home.model.PopularCommunityItem
import com.example.testtask.utils.getAssetUri

class PopularCommunityAdapter(private val onItemClicked: (PopularCommunityItem) -> Unit) :
    ListAdapter<PopularCommunityItem, PopularCommunityAdapter.PopularCommunityViewHolder>(
        CommunityDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCommunityViewHolder {
        val binding =
            ItemPopularCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularCommunityViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: PopularCommunityViewHolder, position: Int) {
        val popularCommunityItem = getItem(position)
        holder.bind(popularCommunityItem)
    }

    class PopularCommunityViewHolder(
        private val binding: ItemPopularCommunityBinding,
        private val onItemClicked: (PopularCommunityItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: PopularCommunityItem

        init {
            binding.root.setOnClickListener { onItemClicked.invoke(model) }
        }

        fun bind(item: PopularCommunityItem) {
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


    class CommunityDiffCallback : DiffUtil.ItemCallback<PopularCommunityItem>() {
        override fun areItemsTheSame(
            oldItem: PopularCommunityItem,
            newItem: PopularCommunityItem,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PopularCommunityItem,
            newItem: PopularCommunityItem,
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}
