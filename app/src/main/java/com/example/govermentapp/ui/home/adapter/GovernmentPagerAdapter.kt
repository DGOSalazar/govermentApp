package com.example.govermentapp.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class GovernmentPagerAdapter(fragment: Fragment?, var currentPage : (Int) -> Unit)
    : FragmentStateAdapter(fragment!!) {
    private val fragmentList: ArrayList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        currentPage(position)
        return fragmentList[position]
    }
}