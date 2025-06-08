package com.kronos.presentation.ui.viewmodel

import android.content.Context
import android.util.Log

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson

import com.kronos.model.ErrorResponseModel
import com.kronos.model.UserPinModel
import com.kronos.presentation.ui.views.login.data.network.LoginService

import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPinViewModel(val context: Context) : ViewModel() {

    private val _operationCompleted = MutableLiveData<Boolean>()
    val operationCompleted: LiveData<Boolean> = _operationCompleted

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> get() = _navigationEvent

    var showAlert by mutableStateOf(false)

     fun usersPin(name: String, email: String, identifier: Int) {
         _navigationEvent.postValue(true)
         return
        viewModelScope.launch {

            val request = UserPinModel(identifier, name, "", "", "", "", email)

            LoginService(context).apiService.registerPin(request)
                .enqueue(object : Callback<UserPinModel> {

                    override fun onResponse(
                        call: Call<UserPinModel>,
                        response: Response<UserPinModel>
                    ) {
                        if (response.isSuccessful) {

                            Log.d("LoginSuccess", "User: ${response.body()}")
                            val pin = response.body()?.identifier
                            //onResult(pin)


                            // Emite el evento para navegar
                            _navigationEvent.postValue(true)



                        } else {

                            val errorBody = response.errorBody()?.string()
                            val gson = Gson()
                            val errorResponse =
                                gson.fromJson(errorBody, ErrorResponseModel::class.java)
                            val errorMessage = errorResponse.errors.firstOrNull()?.message
                            showAlert = true
                            Log.e("LoginError", "Error: ${errorMessage ?: "Unknown error"}")

                        }
                    }

                    override fun onFailure(p0: Call<UserPinModel>, p1: Throwable) {
                        showAlert = true
                    }

                })

            // Una vez que termine, actualiza el estado
            _operationCompleted.value = true

        }

    }

    fun closeAlert() {
        showAlert = false

    }

//    fun onPinChanged(newPin: String)  {
//        if ( == newPin){
//            println(_textValue.value)
//            _navigationEvent.postValue(true)
//        }else{
//            println(_textValue)
//            Log.e("LoginError", "Error: ${"Unknown error"}")
//        }
//
//    }


}

//    suspend fun usersPin2(name: String, email: String, identifier: Int, context: Context) {
//
//        // Obtener el jwtToken desde DataStore
//        val jwtToken = StoreToken(context)
//
//        val request = UserPinModel(identifier, name, email)
//        val bearerToken = "Bearer $jwtToken"
//
//        LoginService().apiService.registerPin(bearerToken, request).enqueue(object : Callback<UserPinModel> {
//
//            override fun onResponse(call: Call<UserPinModel>, response: Response<UserPinModel>) {
//                if (response.isSuccessful) {
//                    Log.d("LoginSuccess", "User: ${response.body()}")
//                } else {
//                    val errorBody = response.errorBody()?.string()
//                    val gson = Gson()
//                    val errorResponse = gson.fromJson(errorBody, ErrorResponseModel::class.java)
//                    val errorMessage = errorResponse.errors.firstOrNull()?.message
//                    showAlert = true
//                    Log.e("LoginError", "Error: ${errorMessage ?: "Unknown error"}")
//                }
//            }
//
//            override fun onFailure(call: Call<UserPinModel>, t: Throwable) {
//                showAlert = true
//            }
//        })
//    }

