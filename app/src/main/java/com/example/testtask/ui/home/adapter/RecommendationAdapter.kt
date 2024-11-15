package com.example.testtask.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.databinding.ItemRecomendBinding
import com.example.testtask.ui.home.model.RecommendationsItem
import com.example.testtask.utils.getAssetUri

class RecommendationAdapter(private val onItemClicked: (RecommendationsItem) -> Unit) :
    ListAdapter<RecommendationsItem, RecommendationAdapter.RecommendationViewHolder>(
        RecommendationDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val binding =
            ItemRecomendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        val popularCommunityItem = getItem(position)
        holder.bind(popularCommunityItem)
    }

    class RecommendationViewHolder(
        private val binding: ItemRecomendBinding,
        private val onItemClicked: (RecommendationsItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: RecommendationsItem

        init {
            binding.root.setOnClickListener { onItemClicked.invoke(model) }
        }

        fun bind(item: RecommendationsItem) {
            this.model = item
            binding.txtTitle.text = item.title
            try {
                Glide.with(binding.root.context)
                    .load(getAssetUri("images/${item.icon}.png"))
                    .into(binding.ivImage)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    class RecommendationDiffCallback : DiffUtil.ItemCallback<RecommendationsItem>() {
        override fun areItemsTheSame(
            oldItem: RecommendationsItem,
            newItem: RecommendationsItem,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecommendationsItem,
            newItem: RecommendationsItem,
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}
