package com.example.testtask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testtask.databinding.FragmentHomeBinding
import com.example.testtask.ui.home.adapter.PopularCommunityAdapter
import com.example.testtask.ui.home.adapter.PopularDiscussionAdapter
import com.example.testtask.ui.home.adapter.PopularHealthTipAdapter
import com.example.testtask.ui.home.adapter.RecommendationAdapter
import com.example.testtask.ui.home.model.HomeDataDto
import com.example.testtask.ui.home.model.PopularCommunityItem
import com.example.testtask.ui.home.model.PopularDiscussionsItem
import com.example.testtask.ui.home.model.PopularHealthTipsItem
import com.example.testtask.ui.home.model.RecommendationsItem
import com.example.testtask.utils.SpacingItemDecoration
import com.example.testtask.utils.toPx
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    private val communityAdapter by lazy { PopularCommunityAdapter(onItemClicked = { item: PopularCommunityItem -> }) }
    private val recommendationAdapter by lazy { RecommendationAdapter(onItemClicked = { item: RecommendationsItem -> }) }
    private val healthTipAdapter by lazy { PopularHealthTipAdapter(onItemClicked = { item: PopularHealthTipsItem -> }) }
    private val discussionAdapter by lazy {
        PopularDiscussionAdapter(clickListener = object :
            PopularDiscussionAdapter.OnItemClickListener {
            override fun onItemClick(item: PopularDiscussionsItem) {}

            override fun onIncreaseClick(item: PopularDiscussionsItem) {}

            override fun onDecreaseClick(item: PopularDiscussionsItem) {}

            override fun onCommentClick(item: PopularDiscussionsItem) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.loadData(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
    }

    private fun setupView() {
        binding.includePopularCommunity.rvList.adapter = communityAdapter
        binding.includeWeRecommend.rvList.adapter = recommendationAdapter
        binding.includeHealthyWithTabi.rvList.adapter = healthTipAdapter
        binding.includePopularDiscussions.rvList.adapter = discussionAdapter
        binding.includePopularDiscussions.rvList.isNestedScrollingEnabled = false

        binding.includePopularCommunity.rvList
            .addItemDecoration(SpacingItemDecoration(0, 8.toPx()))
        binding.includeWeRecommend.rvList
            .addItemDecoration(SpacingItemDecoration(0, 8.toPx()))
        binding.includeHealthyWithTabi.rvList
            .addItemDecoration(SpacingItemDecoration(0, 8.toPx()))
        binding.includePopularDiscussions.rvList
            .addItemDecoration(SpacingItemDecoration(8.toPx(), 0))

        binding.includePopularCommunity.btnAll.setOnClickListener { }
        binding.includeWeRecommend.btnAll.setOnClickListener { }
        binding.includeHealthyWithTabi.btnAll.setOnClickListener { }
        binding.includePopularDiscussions.btnAll.setOnClickListener { }
        binding.includeGoodToKnow.cvContainer.setOnClickListener { }

        binding.btnSearch.setOnClickListener { }
        binding.btnBell.setOnClickListener { }
        binding.btnMessage.setOnClickListener { }

        binding.cvFindHelp.setOnClickListener { }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.data.collectLatest {
                    when (it) {
                        Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.scrollView.visibility = View.GONE
                        }

                        is Resource.Success -> {
                            binding.scrollView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            setupData(it.data)
                        }

                        is Resource.Error -> {

                        }
                    }
                }
            }
        }
    }

    private fun setupData(data: HomeDataDto) {
        binding.includePopularCommunity.apply {
            txtTitle.text = data.popularCommunity.title
            communityAdapter.submitList(data.popularCommunity.items)
        }
        binding.includePopularDiscussions.apply {
            txtTitle.text = data.popularDiscussions.title
            discussionAdapter.submitList(data.popularDiscussions.items)
        }
        binding.includeHealthyWithTabi.apply {
            txtTitle.text = data.popularHealthTips.title
            healthTipAdapter.submitList(data.popularHealthTips.items)
        }
        binding.includeGoodToKnow.apply {
            txtTitle.text = data.usefulInfo.title
            txtDescTitle.text = data.usefulInfo.message
            txtDescription.text = data.usefulInfo.description
        }
        binding.includeWeRecommend.apply {
            txtTitle.text = data.recommendations.title
            recommendationAdapter.submitList(data.recommendations.items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}