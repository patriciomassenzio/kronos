package com.kronos.presentation.ui.views.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kronos.presentation.ui.views.login.data.network.LoginService
import com.kronos.presentation.ui.views.login.data.network.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val context: Context) : ViewModel() {

    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pin = MutableLiveData<String>()
    val pin: LiveData<String> = _pin

    // StateFlow para almacenar el pin convertido a Int
    private val _pinInt = MutableStateFlow<Int>(0)
    val pinInt = _pinInt.asStateFlow()

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _passwordRepeat = MutableLiveData<String>()
    val passwordRepeat: LiveData<String> = _passwordRepeat

    private val _isLoginEnable = MutableLiveData<Boolean>(true)
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable
//
//    private val _isRegistrase =  MutableLiveData<Boolean>(true)
////    val

    private val _emailError = MutableLiveData<String?>(null)
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError


    private val _validEmail = MutableLiveData(true)
    val validEmail: LiveData<Boolean> = _validEmail

    private val _validPassword = MutableLiveData(true)
    val validPassword: LiveData<Boolean> = _validPassword

    private val _validLogin = MutableLiveData(true)
    val validLogin: LiveData<Boolean> = _validLogin

    private var _validrepeatPassword =  MutableLiveData(true)
     val validrepeatPassword : LiveData<Boolean> = _validrepeatPassword

    private var _validuser =  MutableLiveData(true)
    val validuser:LiveData<Boolean> = _validuser


    private var _validpin =  MutableLiveData(true)
    val validpin:LiveData<Boolean> = _validpin






    var showAlert by mutableStateOf(false)

    fun onKronosInputChanged(inputTitle: String, value: String){
        if(inputTitle == "Correo electronico o numero de telefono"){
            _email.value = value
            _validEmail.value = Patterns.EMAIL_ADDRESS.matcher(value).matches()
        }
        else if(inputTitle == "Contraseña"){
            _password.value = value
            _validPassword.value = value.length > 7
        }
        else if(inputTitle == "Repetir Contraseña"){
            _passwordRepeat.value = value
            _validrepeatPassword.value = value.isNotEmpty() && value == _passwordRepeat.value

        }
        else if(inputTitle == "Pin de Usuario"){
            _pin.value = value
            _validpin.value = value.isNotEmpty() && value == _pin.value
        }
        else if(inputTitle == "Ususario"){
            _user.value = value
            _validuser.value =  value.isNotEmpty() && value == _user.value

        }


        _isLoginEnable.value = _validEmail.value as Boolean
                && _validPassword.value as Boolean
                && _validrepeatPassword.value as Boolean
                && _validpin.value as Boolean
                && _validuser.value as Boolean
    }

    fun onLoginChangedEmail(email: String) {
        _email.value = email

        _emailError.value = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) ({
            Log.e("LoginError", "Correo no válido")
        }).toString() else {
            null
        }

        //_isLoginEnable.value = enableLoginEmail(email)
    }

    fun onLoginChangedPassword(password: String) {
        _password.value = password

        _passwordError.value =  if (password.length <= 7) ({

            Log.e("LoginError", "La contraseña debe tener más de 8 caracteressssssss")

        }).toString() else {
            null
        }

        //_isLoginEnable.value = enableLoginPassword(password)
    }

    fun onLoginChangedRepeatPassword( confirmPassword: String){

        _passwordRepeat.value = confirmPassword

//        if (_password.value != _passwordRepeat.value){
//            _passwordError.value = "Las contraseñas no coincidennnnnnnn"
//        }
        //_isLoginEnable.value =  (_password.value  == confirmPassword)
    }

    fun isValidUsername(user: String) {
        _user.value = user

        // Define el patrón: solo letras, números, y guiones bajos. Longitud entre 4 y 15 caracteres.
        val usernamePattern = "^[a-zA-Z0-9_]{4,15}$"

         user.matches(Regex(usernamePattern))
    }




    private fun enableLoginEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun enableLoginPassword(password: String) =
        password.length > 8




    fun login(
        username: String,
        password: String,
        onResult: (String?) -> Unit,
        onSuccess: () -> Unit,
    ) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val request = LoginModel(username, password)
//
//
//            LoginService(context).apiService.login(request).enqueue(object : Callback<LoginResponse> {
//
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val token = response.body()?.access_token
//                        onResult(token)
//                        onSuccess()
//                        //authViewModel.updateAccessToken(token)
//                        Log.d("LoginSuccess", "Token: ${response.body()?.access_token}")
//
//                        //navController.navigate("homeView")
//
//
//                    } else {
//                        showAlert = true
//                        Log.e("LoginErrorLOGIN", "Error: ${response.code()} - ${response.message()}")
//                        //onResult(null)
//                        //val error = showAlert
//
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    showAlert = true
//                    // onResult(null)
//
//
//                }
//            })
//        }
    }

    fun closeAlert() {
        showAlert = false

    }

//    fun onLoginChangedPin(newPin: String) {
//
//        if (newPin.length <= 4 && newPin.all { it.isDigit() }) {
//            _pin.value = newPin
//
//            if (newPin.length == 4) {
//                // Convierte el pin a Int
//                val pinValue = newPin.toIntOrNull()
//                _pinInt.value = pinValue
//            }
//        }
//    }


}