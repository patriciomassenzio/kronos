package com.kronos.presentation.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kronos.model.EmployeesLogin
import com.kronos.presentation.ui.views.login.data.network.LoginService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeesLoginViewModel(private val context: Context) : ViewModel() {

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> get() = _navigationEvent

    var showAlert by mutableStateOf(false)



    fun employeesPin(identifier: String) {
        if(identifier != ""){
            val request = identifier.let { EmployeesLogin(it.toInt()) }

            if (request != null) {
                LoginService(context).apiService.employeesLogin( request)
                    .enqueue(object : Callback<EmployeesLogin> {
                        override fun onResponse(
                            call: Call<EmployeesLogin>,
                            response: Response<EmployeesLogin>
                        ) {
                            if (response.isSuccessful) {
                                println("Login success")
                                // Emite el evento para navegar
                                _navigationEvent.postValue(true)
                            } else {
                                println("Login FALLO")
                                showAlert = true
                            }
                        }

                        override fun onFailure(p0: Call<EmployeesLogin>, p1: Throwable) {
                            showAlert = true
                        }

                    })
            }
        }
    }

    fun closeAlert() {
        showAlert = false

    }
}

