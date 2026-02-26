package com.vishakha.softwarelabassignmentapp.models
data class LoginRequest(
    val email: String,
    val password: String,
    val role: String = "farmer", // default
    val device_token: String,
    val type: String = "email", // default email
    val social_id: String? = null
)




