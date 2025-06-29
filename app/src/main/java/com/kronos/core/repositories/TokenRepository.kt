package com.kronos.core.repositories

import android.content.Context
import com.kronos.core.network.KronosApi
import com.kronos.core.network.RetrofitClient
import com.kronos.data.datastore.StoreToken
import com.kronos.model.UserEmployeeModel
import com.kronos.model.responses.LoginResponse
import com.kronos.presentation.ui.views.auth.login.LoginModel
import retrofit2.Response

class TokenRepository(context: Context) {
    private val api: KronosApi = RetrofitClient.createApi(context)
    val storeToken: StoreToken = StoreToken(context)

    suspend fun login(username: String, password: String,  typeUser: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginModel(username, password))
            if (response.isSuccessful) {
                val loginResponse = response.body()!!
                storeToken.saveCredentials(username, password)
                storeToken.saveTypeUser(typeUser)
                storeToken.saveToken(loginResponse.access_token)
                Result.success(loginResponse)
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

//    suspend fun validatePin(pin: String): Response<UserEmployeeModel> {
//        return api.loginWithPin(pin)
//    }
}