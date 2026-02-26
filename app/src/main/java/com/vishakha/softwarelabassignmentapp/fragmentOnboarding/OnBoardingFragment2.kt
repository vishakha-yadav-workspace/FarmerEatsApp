package com.vishakha.softwarelabassignmentapp.fragmentOnboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vishakha.softwarelabassignmentapp.R
import com.vishakha.softwarelabassignmentapp.databinding.FragmentOnBoarding2Binding

private const val ARG_PAGE_POSITION = "page_position"

class OnBoardingFragment2 : Fragment() {

    private var _binding: FragmentOnBoarding2Binding? = null
    private val binding get() = _binding!!

    private var pagePosition: Int = 1  // Default to 1 (second page)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pagePosition = it.getInt(ARG_PAGE_POSITION, 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // View Binding inflate
        _binding = FragmentOnBoarding2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Second page ke liye specific setup (Convenient theme)
        binding.root.setBackgroundColor(0xFFFF9800.toInt())  // Orange color

        // Title aur Description set karo
        binding.tvTitle.text = "Convenient"
        binding.tvDescription.text = "Our team of delivery drivers will make sure your orders are picked up on time and promptly delivered to your customers."

        // Illustration (agar layout mein ImageView hai toh set karo)
        // binding.ivIllustration.setImageResource(R.drawable.ic_convenient_illustration)

        // Join button ko yahan hide rakho (sirf last page pe visible hona chahiye)
        binding.btnJoin?.visibility = View.GONE

        // Login text pe click → direct Login screen pe jaao
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int = 1) = OnBoardingFragment2().apply {
            arguments = Bundle().apply {
                putInt(ARG_PAGE_POSITION, position)
            }
        }
    }
}