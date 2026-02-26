package com.vishakha.softwarelabassignmentapp.fragmentOnboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vishakha.softwarelabassignmentapp.databinding.FragmentOnBoarding1Binding
import com.vishakha.softwarelabassignmentapp.R

class OnboardingFragment1 : Fragment() {

    private var _binding : FragmentOnBoarding1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoarding1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager setup
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = OnboardingPagerAdapter(this)

        // Dots indicator setup
        TabLayoutMediator(binding.tabDots, viewPager) { tab, position ->
            // Dots customize kar sakte ho agar chaho
        }.attach()

        // Last page pe button visible karo
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.btnJoinMovement?.visibility = if (position == 2) View.VISIBLE else View.GONE
            }
        })

        // Join button click → Login pe jaao
        binding.btnJoinMovement?.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class OnboardingPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingPageFragment.newInstance(0)
            1 -> OnboardingPageFragment.newInstance(1)
            2 -> OnboardingPageFragment.newInstance(2)
            else -> OnboardingPageFragment.newInstance(0)
        }
    }
}
