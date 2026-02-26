package com.vishakha.softwarelabassignmentapp.repository
import com.vishakha.softwarelabassignmentapp.MainApplication
import com.vishakha.softwarelabassignmentapp.api.RetrofitClient
import com.vishakha.softwarelabassignmentapp.database.UserEntity
import com.vishakha.softwarelabassignmentapp.models.ApiResponse
import com.vishakha.softwarelabassignmentapp.models.ForgotPasswordRequest
import com.vishakha.softwarelabassignmentapp.models.LoginRequest
import com.vishakha.softwarelabassignmentapp.models.RegisterRequest
import com.vishakha.softwarelabassignmentapp.models.ResetPasswordRequest
import com.vishakha.softwarelabassignmentapp.models.VerifyOtpRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AuthRepository {
    private val api = RetrofitClient.apiService
    private val userDao = MainApplication.database.userDao()

    suspend fun register(request: RegisterRequest): Response<ApiResponse> {
        return api.register(request)
    }

    suspend fun login(request: LoginRequest): Response<ApiResponse> {
        val response = api.login(request)
        if (response.isSuccessful && response.body()?.token != null) {
            withContext(Dispatchers.IO) {
                userDao.insertUser(UserEntity(token = response.body()!!.token!!))
            }
        }
        return response
    }

    // Similar for forgot, verify, reset
    suspend fun forgotPassword(request: ForgotPasswordRequest): Response<ApiResponse> {
        return api.forgotPassword(request)
    }

    suspend fun verifyOtp(request: VerifyOtpRequest): Response<ApiResponse> {
        return api.verifyOtp(request)
    }

    suspend fun resetPassword(request: ResetPasswordRequest): Response<ApiResponse> {
        return api.resetPassword(request)
    }

    suspend fun getSavedToken(): String? {
        return userDao.getUser()?.token
    }
}