package com.example.testtask.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.databinding.ItemPopularDiscussionBinding
import com.example.testtask.ui.home.model.PopularDiscussionsItem
import com.example.testtask.utils.getAssetUri

class PopularDiscussionAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<PopularDiscussionsItem, PopularDiscussionAdapter.PopularDiscussionViewHolder>(
        DiscussionDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularDiscussionViewHolder {
        val binding =
            ItemPopularDiscussionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularDiscussionViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: PopularDiscussionViewHolder, position: Int) {
        val popularCommunityItem = getItem(position)
        holder.bind(popularCommunityItem)
    }

    class PopularDiscussionViewHolder(
        private val binding: ItemPopularDiscussionBinding,
        private val clickListener: OnItemClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: PopularDiscussionsItem

        init {
            binding.btnMore.setOnClickListener { clickListener.onItemClick(model) }
            binding.btnIncrease.setOnClickListener { clickListener.onIncreaseClick(model) }
            binding.btnDecrease.setOnClickListener { clickListener.onDecreaseClick(model) }
            binding.btnComment.setOnClickListener { clickListener.onCommentClick(model) }
        }

        fun bind(item: PopularDiscussionsItem) {
            this.model = item
            binding.txtTitle.text = item.title
            binding.txtDescription.text = item.description
            binding.btnIncrease.text = item.actions.increaseCount.toString()
            binding.btnDecrease.text = item.actions.decreaseCount.toString()
            binding.btnComment.text = item.actions.commentCount.toString()
            try {
                Glide.with(binding.root.context)
                    .load(getAssetUri("images/${item.userAvatar}.png"))
                    .into(binding.ivImage)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    class DiscussionDiffCallback : DiffUtil.ItemCallback<PopularDiscussionsItem>() {
        override fun areItemsTheSame(
            oldItem: PopularDiscussionsItem,
            newItem: PopularDiscussionsItem,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PopularDiscussionsItem,
            newItem: PopularDiscussionsItem,
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: PopularDiscussionsItem)
        fun onIncreaseClick(item: PopularDiscussionsItem)
        fun onDecreaseClick(item: PopularDiscussionsItem)
        fun onCommentClick(item: PopularDiscussionsItem)
    }
}
