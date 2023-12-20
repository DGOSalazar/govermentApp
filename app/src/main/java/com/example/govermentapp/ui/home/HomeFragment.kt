package com.example.govermentapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.govermentapp.databinding.FragmentHomeBinding
import com.example.govermentapp.ui.home.ListFragment.Companion.showPage
import com.example.govermentapp.ui.home.adapter.GovernmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var pagerAdapter: FragmentStateAdapter
    private var currentPage = 1

    private val viewModel:HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPagerAdapter()
    }

    private fun setPagerAdapter() {
        pagerAdapter = GovernmentPagerAdapter(this) {
            currentPage = it
        }
        for (index in 1..10) {
            (pagerAdapter as GovernmentPagerAdapter).addFragment(showPage(currentPage))
            currentPage = index
        }

        mBinding.vpDcvv.adapter = pagerAdapter
        mBinding.vpDcvv.clipToPadding = false
        mBinding.vpDcvv.setPageTransformer(MarginPageTransformer(10))
        if (pagerAdapter.itemCount > 1) {
            TabLayoutMediator(mBinding.tblIndicator, mBinding.vpDcvv) { _, _ -> }.attach()
        }
    }
}