package com.vishakha.softwarelabassignmentapp.signupFragment

import android.os.Bundle
import com.vishakha.softwarelabassignmentapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vishakha.softwarelabassignmentapp.databinding.FragmentSignupStep4Binding
import com.vishakha.softwarelabassignmentapp.viewmodel.AuthViewModel
import com.vishakha.softwarelabassignmentapp.viewmodel.ViewModelFactory
import com.vishakha.softwarelabassignmentapp.repository.AuthRepository
import com.vishakha.softwarelabassignmentapp.models.RegisterRequest
import com.vishakha.softwarelabassignmentapp.signupFragment.SignupStep2FragmentArgs

class SignupStep4Fragment : Fragment() {

    private var _binding: FragmentSignupStep4Binding? = null
    private val binding get() = _binding!!

    private val args: SignupStep4FragmentArgs by navArgs()
    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory(AuthRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStep4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            // Previous steps se aaye data ko args se le rahe hain
            // (nav_graph mein sab arguments pass kiye gaye hain)

            val hoursJson = args.businessHours
            val type = object : TypeToken<Map<String, List<String>>>() {}.type
            val businessHours: Map<String, List<String>> = Gson().fromJson(hoursJson, type)

            val request = RegisterRequest(
                full_name = args.fullName,
                email = args.email,
                phone = args.phone,
                password = args.password,
                business_name = args.businessName,
                informal_name = args.informalName,
                address = args.address,
                city = args.city,
                state = args.state,
                zip_code = args.zipCode,
                registration_proof = args.registrationProof,
                business_hours = businessHours,
                device_token = "test_device_token",
                type = "email"
            )

            viewModel.register(request)
        }

        // Register result observe karo
        viewModel.registerResult.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true) {
                    binding.tvSuccess.visibility = View.VISIBLE
                    Toast.makeText(context, "Signup Successful!", Toast.LENGTH_SHORT).show()
                    // Success pe login pe jaao ya home pe
                    findNavController().navigate(R.id.action_step4_to_login)
                } else {
                    Toast.makeText(context, body?.message ?: "Signup Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Network error: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}