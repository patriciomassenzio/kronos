package com.kronos.presentation.ui.views.register

import android.content.Context
import com.kronos.model.ErrorResponseModel
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.kronos.model.UserModel
import com.kronos.presentation.ui.views.login.data.network.LoginService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterViewModel(private val context: Context): ViewModel() {
    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pin = MutableLiveData<String>()
    val pin: LiveData<String> = _pin

    private val _pinInt = MutableStateFlow<Int>(0)
    val pinInt = _pinInt.asStateFlow()

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _passwordRepeat = MutableLiveData<String>()
    val passwordRepeat: LiveData<String> = _passwordRepeat

    private val _isLoginEnable = MutableLiveData<Boolean>(true)
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _validEmail = MutableLiveData(true)
    val validEmail: LiveData<Boolean> = _validEmail

    private val _validPassword = MutableLiveData(true)
    val validPassword: LiveData<Boolean> = _validPassword

    private val _validUser = MutableLiveData(true)
    val validUser: LiveData<Boolean> = _validUser

    private val _validRepeatPassword = MutableLiveData(true)
    val validRepeatPassword: LiveData<Boolean> = _validRepeatPassword

    private val _validPin = MutableLiveData(true)
    val validPin: LiveData<Boolean> = _validPin

    private val _emailError = MutableLiveData<String?>(null)
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    fun onKronosInputChanged(inputTitle: String, value: String){
        if(inputTitle == "Correo electronico o numero de telefono"){
            _email.value = value
            _validEmail.value = Patterns.EMAIL_ADDRESS.matcher(value).matches()
        }
        else if(inputTitle == "Usuario"){
            _user.value = value
            _validUser.value = value.length > 7
        }
        else if(inputTitle == "Contraseña"){
            _password.value = value
            _validPassword.value = value.length > 7
        }
        else if(inputTitle == "Repetir Contraseña"){
            _passwordRepeat.value = value
            _validRepeatPassword.value = value.isNotEmpty() && value == _passwordRepeat.value

        }
        else if(inputTitle == "Pin de Usuario"){
            _pin.value = value
            _validPin.value = value.isNotEmpty() && value == _pin.value
        }


        _isLoginEnable.value = _validEmail.value as Boolean
                && _validPassword.value as Boolean
                && _validRepeatPassword.value as Boolean
                && _validPin.value as Boolean
                && _validUser.value as Boolean
    }

    var showAlert by mutableStateOf(false)

    fun users (username: String,email: String, password: String, onSuccess: () -> Unit,) {

        val request = UserModel(username,"nada",email, password, "moso", restaurantes = "Kfc")

        LoginService(context).apiService.usersGet(request).enqueue(object : Callback<UserModel> {


            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful) {
                    Log.d("LoginSuccess", "User: ${response.body()}")
                    onSuccess()

                } else {

                    val errorBody = response.errorBody()?.string()
                    val gson = Gson()
                    val errorResponse = gson.fromJson(errorBody, ErrorResponseModel::class.java)
                    val errorMessage = errorResponse.errors.firstOrNull()?.message
                    showAlert = true
                    Log.e("LoginError", "Error: ${errorMessage ?: "Unknown error"}")



                }
            }

            override fun onFailure(p0: Call<UserModel>, p1: Throwable) {
               showAlert = true
            }


        })
    }
    fun closeAlert() {
        showAlert = false

    }


}