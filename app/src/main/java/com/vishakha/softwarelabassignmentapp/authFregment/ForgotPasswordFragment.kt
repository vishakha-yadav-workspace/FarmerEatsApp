package com.vishakha.softwarelabassignmentapp.authFregment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vishakha.softwarelabassignmentapp.R
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.vishakha.softwarelabassignmentapp.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSendOtp.setOnClickListener {
            val phone = binding.etPhone.text.toString().trim()
            if (phone.isEmpty()) {
                Toast.makeText(context, "Enter phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Future mein API call kar sakte ho forgot password ke liye
            Toast.makeText(context, "OTP sent to $phone", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_forgot_to_otp)
        }

        binding.tvBackToLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}