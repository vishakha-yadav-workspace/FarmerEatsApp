package com.vishakha.softwarelabassignmentapp.models

data class ApiResponse(
    val success: Boolean,
    val message: String,
    val token: String? = null
)