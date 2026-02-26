package com.vishakha.softwarelabassignmentapp.fragmentOnboarding
// ↑ Yeh package tumhare project ke hisaab se sahi kar lo
// Agar project ka root package alag hai toh change kar dena (jaise com.vasu.farmereats.ui.onboarding)

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vishakha.softwarelabassignmentapp.R
import com.vishakha.softwarelabassignmentapp.databinding.FragmentOnboardingPageBinding

private const val ARG_PAGE = "page_position"

class OnboardingPageFragment : Fragment() {

    private var _binding: FragmentOnboardingPageBinding? = null
    private val binding get() = _binding!!

    private var pagePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pagePosition = it.getInt(ARG_PAGE, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (pagePosition) {
            0 -> {  // Quality - Green theme
                binding.root.setBackgroundColor(0xFF4CAF50.toInt())
                binding.tvTitle.text = "Quality"
                binding.tvDescription.text = "Sell your farm fresh products directly to consumers, cutting out the middleman and reducing emissions of the global supply chain."
                binding.btnJoin?.visibility = View.GONE
            }
            1 -> {  // Convenient - Orange theme
                binding.root.setBackgroundColor(0xFFFF9800.toInt())
                binding.tvTitle.text = "Convenient"
                binding.tvDescription.text = "Our team of delivery drivers will make sure your orders are picked up on time and promptly delivered to your customers."
                binding.btnJoin?.visibility = View.GONE
            }
            2 -> {  // Local - Yellow theme (last page)
                binding.root.setBackgroundColor(0xFFFFC107.toInt())
                binding.tvTitle.text = "Local"
                binding.tvDescription.text = "We love the earth and know you do too! Join us in reducing our local carbon footprint one order at a time."
                binding.btnJoin?.visibility = View.VISIBLE
                binding.btnJoin?.setOnClickListener {
                    findNavController().navigate(R.id.action_onboarding_to_login)
                }
            }
        }

        // Har page pe "Login" text clickable
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(position: Int) = OnboardingPageFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PAGE, position)
            }
        }
    }
}