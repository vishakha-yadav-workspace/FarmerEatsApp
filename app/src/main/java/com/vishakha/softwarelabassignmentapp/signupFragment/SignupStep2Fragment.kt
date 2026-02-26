package com.vishakha.softwarelabassignmentapp.signupFragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vishakha.softwarelabassignmentapp.databinding.FragmentSignupStep2Binding
import kotlin.getValue

class SignupStep2Fragment : Fragment() {

    private var _binding: FragmentSignupStep2Binding? = null
    private val binding get() = _binding!!

    private val args: SignupStep2FragmentArgs by navArgs()

    private var selectedProofUri: Uri? = null

    private val filePicker =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    selectedProofUri = uri
                    binding.tvSelectedFile.text = uri.lastPathSegment ?: "Selected"
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAttachProof.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "application/pdf"
            }
            filePicker.launch(intent)
        }

        binding.btnContinue.setOnClickListener {

            val businessName = binding.etBusinessName.text.toString().trim()
            val informalName = binding.etInformalName.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()
            val city = binding.etCity.text.toString().trim()
            val state = binding.etState.text.toString().trim()
            val zipCodeStr = binding.etZipCode.text.toString().trim()

            if (businessName.isEmpty() ||
                informalName.isEmpty() ||
                address.isEmpty() ||
                city.isEmpty() ||
                state.isEmpty() ||
                zipCodeStr.isEmpty() ||
                selectedProofUri == null
            ) {
                Toast.makeText(requireContext(),
                    "Please fill all fields and attach proof",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val zipCode = zipCodeStr.toIntOrNull() ?: 0

            val action = SignupStep2FragmentDirections.actionSignupStep2FragmentToSignupStep3Fragment(
                fullName = args.fullName,
                email = args.email,
                    phone = args.phone,
                    password = args.password,
                    businessName = businessName,
                    informalName = informalName,
                    address = address,
                    city = city,
                    state = state,
                    zipCode = zipCode,
                    registrationProof = selectedProofUri.toString())

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}