package com.vishakha.softwarelabassignmentapp.fragmentOnboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vishakha.softwarelabassignmentapp.R
import com.vishakha.softwarelabassignmentapp.databinding.FragmentOnBoarding3Binding

private const val ARG_PAGE_POSITION = "page_position"

class OnBoardingFragment3 : Fragment() {

    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding get() = _binding!!

    private var pagePosition: Int = 2  // Default to 2 (third page – Local)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pagePosition = it.getInt(ARG_PAGE_POSITION, 2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // View Binding inflate
        _binding = FragmentOnBoarding3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Third page ke liye specific setup (Local theme)
        binding.root.setBackgroundColor(0xFFFFC107.toInt())  // Yellow color

        // Title aur Description set karo
        binding.tvTitle.text = "Local"
        binding.tvDescription.text = "We love the earth and know you do too! Join us in reducing our local carbon footprint one order at a time."

        // Illustration (agar layout mein ImageView hai toh set karo)
        // binding.ivIllustration.setImageResource(R.drawable.ic_local_illustration)

        // Yeh last page hai → Join button visible karo
        binding.btnJoin?.visibility = View.VISIBLE

        // Join button click → Login screen pe navigate
        binding.btnJoin?.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_to_login)
        }

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
        fun newInstance(position: Int = 2) = OnBoardingFragment3().apply {
            arguments = Bundle().apply {
                putInt(ARG_PAGE_POSITION, position)
            }
        }
    }
}