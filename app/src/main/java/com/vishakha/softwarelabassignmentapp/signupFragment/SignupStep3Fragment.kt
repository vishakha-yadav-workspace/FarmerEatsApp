package com.vishakha.softwarelabassignmentapp.signupFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.vishakha.softwarelabassignmentapp.databinding.FragmentSignupStep3Binding
import kotlin.getValue

class SignupStep3Fragment : Fragment() {

    private var _binding: FragmentSignupStep3Binding? = null
    private val binding get() = _binding!!

    private val args: SignupStep3FragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStep3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {

            val businessHours = mutableMapOf<String, List<String>>()

            if (binding.cbMonday.isChecked) {
                val start = binding.etMondayStart.text.toString().trim()
                val end = binding.etMondayEnd.text.toString().trim()

                if (start.isNotEmpty() && end.isNotEmpty()) {
                    businessHours["M"] = listOf(start, end)
                }
            }

            if (businessHours.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Select at least one day with hours",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val hoursJson = Gson().toJson(businessHours)

            val action = SignupStep3FragmentDirections.actionStep3ToStep4(
                fullName = args.fullName,
                email = args.email,
                phone = args.phone,
                password = args.password,
                businessName = args.businessName,
                informalName = args.informalName,
                address = args.address,
                city = args.city,
                state = args.state,
                zipCode = args.zipCode,
                registrationProof = args.registrationProof,
                businessHours = hoursJson
            )

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}