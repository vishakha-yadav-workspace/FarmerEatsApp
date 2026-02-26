package com.vishakha.softwarelabassignmentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vishakha.softwarelabassignmentapp.databinding.FragmentLoginBinding
import com.vishakha.softwarelabassignmentapp.models.LoginRequest
import com.vishakha.softwarelabassignmentapp.repository.AuthRepository
import com.vishakha.softwarelabassignmentapp.viewmodel.AuthViewModel
import com.vishakha.softwarelabassignmentapp.viewmodel.ViewModelFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory(AuthRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Login Button Click
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = LoginRequest(
                email = email,
                password = password,
                device_token = "test_device_token_123"
            )

            viewModel.login(request)
        }

        // Observe Login Response
        viewModel.loginResult.observe(viewLifecycleOwner, Observer { response ->

            if (response.isSuccessful && response.body()?.success == true) {

                Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_login_to_home)

            } else {

                Toast.makeText(
                    requireContext(),
                    response.body()?.message ?: "Login Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        // Create Account Click (tvCreate from XML)
        binding.tvCreate.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }

        // Forgot Password Click
        binding.tvForgot.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_forgot)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}