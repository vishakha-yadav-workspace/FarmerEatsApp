package com.vishakha.softwarelabassignmentapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishakha.softwarelabassignmentapp.models.ApiResponse
import com.vishakha.softwarelabassignmentapp.models.ForgotPasswordRequest
import com.vishakha.softwarelabassignmentapp.models.LoginRequest
import com.vishakha.softwarelabassignmentapp.models.RegisterRequest
import com.vishakha.softwarelabassignmentapp.models.ResetPasswordRequest
import com.vishakha.softwarelabassignmentapp.models.VerifyOtpRequest
import com.vishakha.softwarelabassignmentapp.repository.AuthRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    // Login
    private val _loginResult = MutableLiveData<Response<ApiResponse>>()
    val loginResult: LiveData<Response<ApiResponse>> get() = _loginResult

    // Register
    private val _registerResult = MutableLiveData<Response<ApiResponse>>()
    val registerResult: LiveData<Response<ApiResponse>> get() = _registerResult

    // Forgot Password
    private val _forgotResult = MutableLiveData<Response<ApiResponse>>()
    val forgotResult: LiveData<Response<ApiResponse>> get() = _forgotResult

    // Verify OTP
    private val _verifyOtpResult = MutableLiveData<Response<ApiResponse>>()
    val verifyOtpResult: LiveData<Response<ApiResponse>> get() = _verifyOtpResult

    // Reset Password
    private val _resetPasswordResult = MutableLiveData<Response<ApiResponse>>()
    val resetPasswordResult: LiveData<Response<ApiResponse>> get() = _resetPasswordResult

    // Loading state (optional – UI mein progress show karne ke liye)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Error message (optional – UI mein show kar sakte ho)
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    /**
     * User Login
     */
    fun login(request: LoginRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.login(request)
                _loginResult.value = response
                if (!response.isSuccessful || response.body()?.success != true) {
                    _errorMessage.value = response.body()?.message ?: "Login failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Network error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * User Register (Signup)
     */
    fun register(request: RegisterRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.register(request)
                _registerResult.value = response
                if (!response.isSuccessful || response.body()?.success != true) {
                    _errorMessage.value = response.body()?.message ?: "Registration failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Network error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Forgot Password (Send OTP)
     */
    fun forgotPassword(request: ForgotPasswordRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.forgotPassword(request)
                _forgotResult.value = response
                if (!response.isSuccessful || response.body()?.success != true) {
                    _errorMessage.value = response.body()?.message ?: "Failed to send OTP"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Network error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Verify OTP
     */
    fun verifyOtp(request: VerifyOtpRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.verifyOtp(request)
                _verifyOtpResult.value = response
                if (!response.isSuccessful || response.body()?.success != true) {
                    _errorMessage.value = response.body()?.message ?: "Invalid OTP"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Network error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Reset Password
     */
    fun resetPassword(request: ResetPasswordRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.resetPassword(request)
                _resetPasswordResult.value = response
                if (!response.isSuccessful || response.body()?.success != true) {
                    _errorMessage.value = response.body()?.message ?: "Password reset failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Network error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Optional: Clear error message after showing
    fun clearError() {
        _errorMessage.value = null
    }
}