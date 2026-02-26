package com.vishakha.softwarelabassignmentapp.authFregment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vishakha.softwarelabassignmentapp.R
import com.vishakha.softwarelabassignmentapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Yeh dashboard hai – yahan user ka data show kar sakte ho
        // Example: binding.tvWelcome.text = "Welcome to FarmerEats!"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}