package com.kronos.presentation.ui.views.app.employee.pad

import android.content.Context
import android.util.Log

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kronos.core.network.RetrofitService
import com.kronos.data.datastore.StoreToken
import com.kronos.model.EmployeesLogin

import com.kronos.model.responses.ErrorResponseModel
import com.kronos.model.UserEmployeeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterPinViewModel(private val context: Context) : ViewModel() {

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> = _navigationEvent

    private val _currentEmployee = MutableLiveData<EmployeesLogin?>()
    val currentEmployee: LiveData<EmployeesLogin?> = _currentEmployee

    var showAlert by mutableStateOf(false)
    private var alertMessage by mutableStateOf("")

    private val storeToken = StoreToken(context)

    private val storePin = StorePin(context)

    fun employeesPin(identifier: String) {
        if (identifier.isNotBlank()) {
            val request = EmployeesLogin(identifier.toInt())

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = RetrofitService(context).apiService.employeesLogin(request)

                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            println("Login success")
                            _navigationEvent.value = true
                        } else {
                            println("Login FALLO")
                            showAlert = true
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        showAlert = true
                        Log.e("employeesPin", "Excepción: ${e.localizedMessage}")
                    }
                }
            }
        }
    }







//    fun employeesPin(pin: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val pinInt = pin.toIntOrNull()
//                if (pinInt == null || pin.length != 4) {
//                    withContext(Dispatchers.Main) {
//                        alertMessage = "PIN debe tener exactamente 4 dígitos"
//                        showAlert = true
//                    }
//                    return@launch
//                }
//
//                Log.d("EmployeeLogin", "Intentando login con PIN: $pinInt")
//
//                // Crear request para login con PIN usando el modelo correcto
//                val loginRequest = EmployeesLogin(identifier = pinInt)
//
//                RetrofitService(context).apiService.employeesLogin(loginRequest)
//                    .enqueue(object : Callback<EmployeesLogin> {
//                        override fun onResponse(
//                            call: Call<EmployeesLogin>,
//                            response: Response<EmployeesLogin>
//                        ) {
//                            if (response.isSuccessful && response.body() != null) {
//                                val employee = response.body()!!
//
//                                Log.d("EmployeeLogin", "Login exitoso: ${employee.identifier}")
//
//                                // Guardar datos del empleado logueado
//                                _currentEmployee.postValue(loginRequest)
//
//                                // Guardar el PIN y tipo de usuario
//                                viewModelScope.launch {
//                                    storePin.savePin(pinInt)
//                                    storeToken.saveTypeUser("empleado")
//                                    storeToken.saveToken("employee_${employee.identifier}") // Token temporal
//                                }
//
//                                // Navegar al home
//                                _navigationEvent.postValue(true)
//
//                            } else {
//                                val errorBody = response.errorBody()?.string()
//                                Log.e("EmployeeLogin", "Error ${response.code()}: $errorBody")
//
//                                alertMessage = when (response.code()) {
//                                    404 -> "PIN no encontrado"
//                                    401 -> "PIN incorrecto"
//                                    else -> "Error al iniciar sesión"
//                                }
//                                showAlert = true
//                            }
//                        }
//
//                        override fun onFailure(p0: Call<EmployeesLogin>, t: Throwable) {
//                            Log.e("EmployeeLogin", "Error de conexión: ${t.message}")
//                            alertMessage = "Error de conexión"
//                            showAlert = true
//                        }
//                    })
//
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Log.e("EmployeeLogin", "Excepción: ${e.message}")
//                    alertMessage = "Error inesperado"
//                    showAlert = true
//                }
//            }
//        }
//    }

    fun closeAlert() {
        showAlert = false
        alertMessage = ""
    }

    // Función para verificar si hay un empleado logueado
    fun checkLoggedEmployee() {
        viewModelScope.launch {
            val savedPin = storePin.getPin.first()
            val userType = storeToken.getTypeUser.first()

            if (savedPin != null && userType == "empleado") {
                Log.d("EmployeeLogin", "Empleado ya logueado con PIN: $savedPin")
                _navigationEvent.postValue(true)
            }
        }
    }

    // Función para logout
    fun logout() {
        viewModelScope.launch {
            storePin.clearPin()
            storeToken.clearToken()
//            storeToken.clearTypeUser()
            _currentEmployee.postValue(null)
            _navigationEvent.postValue(false)
        }
    }
}

// Modelo para el request de login con PIN (ya tienes EmployeesLogin)
// data class EmployeesLogin(val identifier: Int) - usar el que ya tienes

// Clase para manejar el store del PIN
class StorePin(private val context: Context) {

//    companion object {
//        private const val PIN_KEY = "employee_pin"
//    }
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pin_prefs")
        val PIN_KEY = intPreferencesKey("pin")
    }

    private val dataStore = context.dataStore

    suspend fun savePin(pin: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(PIN_KEY.toString())] = pin
        }
    }

    val getPin = dataStore.data.map { preferences ->
        preferences[intPreferencesKey(PIN_KEY.toString())]
    }

    suspend fun clearPin() {
        dataStore.edit { preferences ->
            preferences.remove(intPreferencesKey(PIN_KEY.toString()))
        }
    }




}








//class RegisterPinViewModel(val context: Context) : ViewModel() {
//
//    private val _operationCompleted = MutableLiveData<Boolean>()
//    val operationCompleted: LiveData<Boolean> = _operationCompleted
//
//    private val _navigationEvent = MutableLiveData<Boolean>()
//    val navigationEvent: LiveData<Boolean> get() = _navigationEvent
//
//    var showAlert by mutableStateOf(false)
//
//     fun usersPin(name: String, email: String, identifier: Int) {
////         _navigationEvent.postValue(true)
////         return
//        viewModelScope.launch {
//
//            val request = UserEmployeeModel(identifier, name, "", "", "", "", email)
//
//            RetrofitService(context).apiService.registerPin(request)
//                .enqueue(object : Callback<UserEmployeeModel> {
//
//                    override fun onResponse(
//                        call: Call<UserEmployeeModel>,
//                        response: Response<UserEmployeeModel>
//                    ) {
//                        if (response.isSuccessful) {
//
//                            Log.d("LoginSuccess", "User: ${response.body()}")
//                            val pin = response.body()?.identifier
//                            //onResult(pin)
//
//
//                            // Emite el evento para navegar
//                            _navigationEvent.postValue(true)
//
//
//
//                        } else {
//
//                            val errorBody = response.errorBody()?.string()
//                            val gson = Gson()
//                            val errorResponse =  gson.fromJson(errorBody, ErrorResponseModel::class.java)
//
//                            val errorMessage = errorResponse.errors.firstOrNull()?.message
//                            showAlert = true
//                            Log.e("LoginError", "Error: ${errorMessage ?: "Unknown error"}")
//
//                        }
//                    }
//
//                    override fun onFailure(p0: Call<UserEmployeeModel>, p1: Throwable) {
//                        showAlert = true
//                    }
//
//                })
//
//            // Una vez que termine, actualiza el estado
//            _operationCompleted.value = true
//
//        }
//
//    }
//
//    fun closeAlert() {
//        showAlert = false
//
//    }
//}








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

