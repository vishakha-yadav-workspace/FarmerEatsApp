package com.vishakha.softwarelabassignmentapp.api

import com.vishakha.softwarelabassignmentapp.models.ApiResponse
import com.vishakha.softwarelabassignmentapp.models.ForgotPasswordRequest
import com.vishakha.softwarelabassignmentapp.models.LoginRequest
import com.vishakha.softwarelabassignmentapp.models.RegisterRequest
import com.vishakha.softwarelabassignmentapp.models.ResetPasswordRequest
import com.vishakha.softwarelabassignmentapp.models.VerifyOtpRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface ApiService {
    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse>

    @POST("forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<ApiResponse>

    @POST("verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Response<ApiResponse>

    @POST("reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<ApiResponse>
}