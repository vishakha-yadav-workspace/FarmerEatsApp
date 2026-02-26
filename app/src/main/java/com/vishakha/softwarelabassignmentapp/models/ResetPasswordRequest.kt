package com.vishakha.softwarelabassignmentapp.models

data class ResetPasswordRequest(
    val token: String,
    val password: String,
    val cpassword: String
)