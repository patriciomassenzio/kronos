package com.kronos.presentation.ui.views.auth.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.kronos.core.repositories.TokenRepository
import com.kronos.navigation.Routes
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context, private val navController: NavHostController) : ViewModel() {
    private val tokenRepository = TokenRepository(context)
//    fun saveUsername(UserModel: String) {
//        viewModelScope.launch {
//            UserDataStore.saveUsername(context, UserModel)
//        }
//    }

    private val _user = MutableLiveData<String>("perez@mail.com")
    val user: LiveData<String> = _user

    private val _email = MutableLiveData<String>("perez@mail.com")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>("password")
    val password: LiveData<String> = _password

    private val _passwordRepeat = MutableLiveData<String>()
    val passwordRepeat: LiveData<String> = _passwordRepeat

    private val _isLoginEnable = MutableLiveData<Boolean>(true)
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isRegistrar = MutableLiveData<Boolean>(true)
    val isRegistrars: LiveData<Boolean> = _isRegistrar

    private val _emailError = MutableLiveData<String?>(null)
    val emailError: LiveData<String?> = _emailError

    private val _typeUser = MutableLiveData<String>()
    val typeUser: LiveData<String> = _typeUser

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _validEmail = MutableLiveData(true)
    val validEmail: LiveData<Boolean> = _validEmail

    private val _validPassword = MutableLiveData(true)
    val validPassword: LiveData<Boolean> = _validPassword


    private var _validrepeatPassword = MutableLiveData(true)
    val validrepeatPassword: LiveData<Boolean> = _validrepeatPassword

    private var _validuser = MutableLiveData(true)
    val validuser: LiveData<Boolean> = _validuser

//    private var _validTypeUser =  MutableLiveData(true)
//    val validTypeUser:LiveData<Boolean> = _validTypeUser

    var showAlert by mutableStateOf(false)

    fun onKronosInputChanged(inputTitle: String, value: String) {
        if (inputTitle == "Correo electronico o numero de telefono") {
            _email.value = value
            _validEmail.value = Patterns.EMAIL_ADDRESS.matcher(value).matches()
        } else if (inputTitle == "Contraseña") {
            _password.value = value
            _validPassword.value = value.length > 7
        } else if (inputTitle == "Repetir Contraseña") {
            _passwordRepeat.value = value
            _validrepeatPassword.value = value.isNotEmpty() && value == _passwordRepeat.value

        }
//        else if(inputTitle == "Tipo usuario"){
//            _typeUser.value = value
//
//            _validTypeUser.value = value.isNotEmpty() && value == _validTypeUser.value
//        }
        else if (inputTitle == "Ususario") {
            _user.value = value
            _validuser.value = value.isNotEmpty() && value == _user.value

        }


        _isLoginEnable.value = _validEmail.value as Boolean
                && _validPassword.value as Boolean
                && _validrepeatPassword.value as Boolean
//                && _validTypeuser.value as Boolean
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

        _passwordError.value = if (password.length <= 7) ({

            Log.e("LoginError", "La contraseña debe tener más de 8 caracteressssssss")

        }).toString() else {
            null
        }

        //_isLoginEnable.value = enableLoginPassword(password)
    }

    fun onLoginChangedRepeatPassword(confirmPassword: String) {

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


    private fun enableLoginEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()


    private fun enableLoginPassword(password: String) = password.length > 8


    fun login(username: String, password: String, typeUser: String) {
        viewModelScope.launch {
            try {
                val response = tokenRepository.login(username, password, typeUser)
                response.fold(
                    onSuccess = { loginResponse ->
                        val token = loginResponse.access_token
                        Log.d("LoginSuccess", "Token: $token")

                        // Acceder al tipo de usuario guardado
                        val userType = tokenRepository.storeToken.getTypeUser.firstOrNull()
                        when (userType?.lowercase()) {
                            "admin" -> navController.navigate(Routes.HomeAdmin.route)
                            "empleado" -> navController.navigate(Routes.RegisterEmployee.route)
                            else -> Log.e("LoginError", "Tipo de usuario desconocido: $userType")
                        }
                    },
                    onFailure = { exception ->
                        Log.e("LoginError", "Error: ${exception.message}")
                    }
                )
            } catch (e: Exception) {
                Log.e("LoginError", "Excepción: ${e.message}")
            }
        }
    }


    fun closeAlert() {
        showAlert = false

    }
    init {
        _typeUser.value = "admin"
    }



}



//    fun login(
//        username: String,
//        password: String,
//        onResult: (String?) -> Unit,
//        onSuccess: () -> Unit,
//    ) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val request = LoginModel(username, password)
//
//
//            RetrofitService(context).apiService.login(request).enqueue(object : Callback<LoginResponse> {
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
//    }


