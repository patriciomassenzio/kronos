package com.kronos.presentation.ui.views.auth.register

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kronos.enumeration.Inputs
import com.kronos.model.UserModel

class RegisterViewModel(private val context: Context, private val navController: NavController): ViewModel() {
    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _passwordRepeat = MutableLiveData<String>()
    val passwordRepeat: LiveData<String> = _passwordRepeat

    private val _isLoginEnable = MutableLiveData<Boolean>(false)
    val isRegisterEnabled: LiveData<Boolean> = _isLoginEnable

    private val _validEmail = MutableLiveData(true)
    val validEmail: LiveData<Boolean> = _validEmail

    private val _validPassword = MutableLiveData(true)
    val validPassword: LiveData<Boolean> = _validPassword

    private val _validUser = MutableLiveData(true)
    val validUser: LiveData<Boolean> = _validUser

    private val _validRepeatPassword = MutableLiveData(true)
    val validRepeatPassword: LiveData<Boolean> = _validRepeatPassword

    fun onInputChange(input: Inputs, value: String){
        when (input){
            Inputs.User -> {
                _user.value = value
                _validUser.value = value.length > 7
            }
            Inputs.Email -> {
                _email.value = value
                _validEmail.value = Patterns.EMAIL_ADDRESS.matcher(value).matches()
            }
            Inputs.Password -> {
                _password.value = value
                _validPassword.value = value.length > 7
            }
            Inputs.PasswordRepeat -> {
                _passwordRepeat.value = value
                _validRepeatPassword.value = value.isNotEmpty() && value == _passwordRepeat.value
            }
            else -> {}
        }

        _isLoginEnable.value = _validEmail.value as Boolean
                && _validPassword.value as Boolean
                && _validRepeatPassword.value as Boolean
                && _validUser.value as Boolean
    }

    var showAlert by mutableStateOf(false)

    fun register(){
        val obj = UserModel(
            first_name = "TODO",
            last_name = "TODO",
            email = "TODO",
            password = "TODO",
            rol = "TODO",
            restaurantes = "TODO"
        )

//        LoginService(context).apiService.usersPost(request).enqueue(object : Callback<UserModel> {
//            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
//                if (response.isSuccessful) {
//                    Log.d("LoginSuccess", "User: ${response.body()}")
//                    onSuccess()
//
//                } else {
//
//                    val errorBody = response.errorBody()?.string()
//                    val gson = Gson()
//                    val errorResponse = gson.fromJson(errorBody, ErrorResponseModel::class.java)
//                    val errorMessage = errorResponse.errors.firstOrNull()?.message
//                    showAlert = true
//                    Log.e("LoginError", "Error: ${errorMessage ?: "Unknown error"}")
//
//
//
//                }
//            }
//
//            override fun onFailure(p0: Call<UserModel>, p1: Throwable) {
//                showAlert = true
//            }
//
//
//        })

        navController.popBackStack()
    }
    fun closeAlert() {
        showAlert = false
    }



}