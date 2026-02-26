package com.vishakha.softwarelabassignmentapp.models

data class RegisterRequest(
    val full_name: String,
    val email: String,
    val phone: String,
    val password: String,
    val role: String = "farmer",
    val business_name: String,
    val informal_name: String,
    val address: String,
    val city: String,
    val state: String,
    val zip_code: Int,
    val registration_proof: String, // file path or url
    val business_hours: Map<String, List<String>>, // e.g., mapOf("M" to listOf("8:00am-10:00pm"))
    val device_token: String,
    val type: String = "email",
    val social_id: String? = null
)