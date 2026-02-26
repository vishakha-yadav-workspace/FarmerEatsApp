package com.vishakha.softwarelabassignmentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vishakha.softwarelabassignmentapp.fragmentOnboarding.OnBoardingFragment2
import com.vishakha.softwarelabassignmentapp.fragmentOnboarding.OnBoardingFragment3
import com.vishakha.softwarelabassignmentapp.fragmentOnboarding.OnboardingFragment1

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = OnboardingAdapter(this)

        val tabDots = findViewById<com.google.android.material.tabs.TabLayout>(R.id.tab_dots)
        TabLayoutMediator(tabDots, viewPager) { _, _ -> }.attach()

        // Last screen pe login button se LoginActivity open karo
    }
}

class OnboardingAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        return when (position) {
            0 -> OnboardingFragment1()
            1 -> OnBoardingFragment2()
            2 -> OnBoardingFragment3()
            else -> OnboardingFragment1()
        }
    }
}