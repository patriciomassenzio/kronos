package com.kronos.presentation.ui.views.register

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import androidx.navigation.NavController
import com.google.gson.Gson
import com.kronos.core.network.RetrofitService
import com.kronos.data.datastore.StorePin
import com.kronos.data.datastore.StoreToken
import com.kronos.enumeration.Inputs

import com.kronos.model.UserEmployeeModel
import com.kronos.navigation.Routes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterEmployeeViewModel(private val context: Context, private val navController: NavController): ViewModel() {

    private val _name = MutableStateFlow<String>("Maria")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow<String>("maria@mail.com")
    val email: StateFlow<String> = _email

    private val _pin = MutableStateFlow<String>("4433")
    val pin: StateFlow<String> = _pin

    // StateFlow para almacenar el pin convertido a Int
    private val _pinInt = MutableStateFlow<Int?>(null)
    val pinInt = _pinInt.asStateFlow()

    private val _rol = MutableStateFlow<String>("")
    val rol: StateFlow<String> = _rol

    private val _restaurante = MutableStateFlow<String>("")
    val restaurante: StateFlow<String> = _restaurante

//    private val _isRegistrar = MutableLiveData<Boolean>(false)
//    val isRegistrar: LiveData<Boolean> = _isRegistrar

    // ✅ Inicializar validaciones como false para campos requeridos
    private val _validName = MutableStateFlow(true)
    val validName: StateFlow<Boolean> = _validName

    private val _validEmail = MutableStateFlow(true)
    val validEmail: StateFlow<Boolean> = _validEmail

    private val _validPin = MutableStateFlow(true)
    var validPin: StateFlow<Boolean> = _validPin

    private val _validRol = MutableStateFlow(true)
    val validRol: StateFlow<Boolean> = _validRol

    private val _validRestaurante = MutableStateFlow(true)
    val validRestaurante: StateFlow<Boolean> = _validRestaurante

    private val _isRegisterEnabled = MutableStateFlow(true)
    val isRegisterEnabled: StateFlow<Boolean> = _isRegisterEnabled


    fun onInputChange(input: Inputs, value: String){
        when (input){
            Inputs.Nombre -> {
                _name.value = value
                _validName.value = value.length >= 2
            }
            Inputs.Email -> {
                _email.value = value
                _validEmail.value = value.isNotBlank()&& Patterns.EMAIL_ADDRESS.matcher(value).matches()
            }
            Inputs.Pin -> {
                onLoginChangedPin(value)
                _validPin.value = value.isNotBlank() && value.length == 4 && value.all { it.isDigit() }
            }
            Inputs.Rol -> {
                _rol.value = value
                _validRol.value = value.isNotBlank()
            }
            Inputs.Restaurante -> {
                _restaurante.value = value
                _validRestaurante.value = value.isNotBlank()
            }else -> {}


        }



        // ✅ Habilitar botón solo si TODOS los campos son válidos
        _isRegisterEnabled.value =
              (_validName.value)
             && (_validEmail.value)
             && (_validPin.value)
             && (_validRol.value)
             && (_validRestaurante.value)
//

        Log.d("InputChange", "validName=${_validName.value}, validEmail=${_validEmail.value}, validPin=${_validPin.value}, validRol=${_validRol.value}, validRestaurante=${_validRestaurante.value}")
       // Log.d("Botón", "¿Habilitado? ${_isRegisterEnable}")
    }


    private val storeToken = StoreToken(context)
    var showAlert by mutableStateOf(false)

    var alertMessage by mutableStateOf("")
    private set


//    fun registerEmployee(navController: NavController) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val name = _name.value.trim()
//                val role = _rol.value.trim()
//                val email = _email.value.trim()
//                val pin = _pinInt.value ?: 0
//                val phone = "0000000000"
//                val address = "Dirección de ejemplo"
//                val comments = "Empleado registrado desde app"
//                val emergencyContact = generateRandomEmergencyContact()
//                val schedule = "Lunes a Viernes 9am-6pm"
//                val assignedTables = emptyList<Int>()
//
//                val userEmployee = UserEmployeeModel(
//                    identifier = pin,
//                    name = name,
//                    role = role,
//                    comments = comments,
//                    phone = phone,
//                    address = address,
//                    email = email,
//                    emergency_contact = emergencyContact,
//                    work_schedule = schedule,
//                    assigned_tables = assignedTables
//                )
//
//                Log.d("RegistroDebug", "Datos a enviar: ${Gson().toJson(userEmployee)}")
//
//                val response = RetrofitService(context).apiService.registerPin(userEmployee)
//
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        val storePin = StorePin(context)
//                        _pinInt.value?.let { storePin.savePin(it) }
//                        Log.d("Registro", "Empleado registrado: ${Gson().toJson(response.body())}")
//                        navController.popBackStack()
//                    } else {
//                        val errorBody = response.errorBody()?.string()
//                        Log.e("Registro", "Error ${response.code()}: $errorBody")
//
//                        when (response.code()) {
//                            422 -> Log.e("Registro", "Datos inválidos - verificar campos requeridos")
//                            409 -> Log.e("Registro", "Conflicto - email o PIN ya existe")
//                            400 -> Log.e("Registro", "Petición mal formada")
//                            500 -> Log.e("Registro", "Error interno del servidor")
//                        }
//
//                        showAlert = true
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    showAlert = true
//                    Log.e("Registro", "Excepción: ${e.localizedMessage}")
//                }
//            }
//        }
//    }






    fun registerEmployee(navController: NavController) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Validación adicional antes de enviar


                val name = _name.value.trim()
                val role = _rol.value.trim()
                val email = _email.value.trim()
                val pin = _pinInt.value ?: 0
                val phone = "0000000000"
                val address = "Dirección de ejemplo"
                val comments = "Empleado registrado desde app"
                val emergencyContact = generateRandomEmergencyContact() // Generar número único
                val schedule = "Lunes a Viernes 9am-6pm"
                val assignedTables = emptyList<Int>()

                val userEmployee = UserEmployeeModel(
                    identifier = pin,
                    name = name,
                    role = role,
                    comments = comments,
                    phone = phone,
                    address = address,
                    email = email,
                    emergency_contact = emergencyContact,
                    work_schedule = schedule,
                    assigned_tables = assignedTables
                )

                // Log para debugging
                Log.d("RegistroDebug", "Datos a enviar: ${Gson().toJson(userEmployee)}")

                RetrofitService(context).apiService.registerPin(userEmployee)
                    .enqueue(object : Callback<UserEmployeeModel> {
                        override fun onResponse(
                            call: Call<UserEmployeeModel>,
                            response: Response<UserEmployeeModel>
                        ) {
                            if (response.isSuccessful) {
                                val storePin = StorePin(context)
                                Log.d("Registro", "Empleado registrado: ${Gson().toJson(response.body())}")
                                viewModelScope.launch {
                                    _pinInt.value?.let { storePin.savePin(it) }
                                }
                                navController.popBackStack()
                            } else {
                                // Manejo mejorado de errores
                                val errorBody = response.errorBody()?.string()
                                Log.e("Registro", "Error ${response.code()}: $errorBody")


                                when (response.code()) {
                                    422 -> Log.e("Registro", "Datos inválidos - verificar campos requeridos")
                                    409 -> Log.e("Registro", "Conflicto - email o PIN ya existe")
                                    400 -> Log.e("Registro", "Petición mal formada")
                                    500 -> Log.e("Error interno del servidor","500")

                                }

                                if (errorBody?.contains("Employee already registered") == true) {
                                    showAlert = true
                                    alertMessage = "Este empleado ya está registrado (por PIN o email)."
                                }
                            }
                        }

                        override fun onFailure(call: Call<UserEmployeeModel>, t: Throwable) {
                            alertMessage = "Error de red o del servidor: ${t.localizedMessage}"
                            showAlert = true
                        }
                    })
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showAlert = true
                    Log.e("Registro", "Excepción: ${e.message}")
                }
            }
        }
    }

//    private fun validateAllFields(): Boolean {
//        val name = _name.value?.trim() ?: ""
//        val email = _email.value?.trim() ?: ""
//        val pin = _pinInt.value
//        val role = _rol.value?.trim() ?: ""
//        val restaurante = _restaurante.value?.trim() ?: ""
//
//        return when {
//            name.isEmpty() || name.length < 2 -> {
//                Log.e("Validación", "Nombre inválido: '$name'")
//                false
//            }
//            email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
//                Log.e("Validación", "Email inválido: '$email'")
//                false
//            }
//            pin == null || pin <= 0 || pin.toString().length != 4 -> {
//                Log.e("Validación", "PIN inválido: $pin")
//                false
//            }
//            role.isEmpty() -> {
//                Log.e("Validación", "Rol inválido: '$role'")
//                false
//            }
//            restaurante.isEmpty() -> {
//                Log.e("Validación", "Restaurante inválido: '$restaurante'")
//                false
//            }
//            else -> true
//        }
//    }

    private fun generateRandomEmergencyContact(): Int {
        // Generar un número de emergencia único basado en timestamp
        return (System.currentTimeMillis() % 100000000).toInt()
    }

    fun closeAlert() {
        showAlert = false
    }




    fun onLoginChangedPin(newPin: String) {
        if (newPin.length <= 4 && newPin.all { it.isDigit() }) {
            _pin.value = newPin

            // Solo convertir si el PIN tiene exactamente 4 dígitos
            if (newPin.length == 4) {
                _pinInt.value = newPin.toIntOrNull()
            } else {
                _pinInt.value = null
            }
        }
    }

    fun verificarPin(){
        viewModelScope.launch {
            val pinActual = _pin.value ?: ""
            val pinValido = pinActual.isNotEmpty() && pinActual.length == 4

            if (pinValido){
                storeToken.saveTypeUser("empleado")
                navController.navigate(Routes.Home.route) {
                    popUpTo(0) { inclusive = true }
                }
            } else {
                Log.e("EmpleadoLogin", "PIN inválido: '$pinActual'")
            }
        }
    }
}







//class RegisterEmployeeViewModel(private val context: Context, private val navController: NavController): ViewModel() {
//    private val _name = MutableLiveData<String>("")
//    val name: LiveData<String> = _name
//
//    private val _email = MutableLiveData<String>("")
//    val email: LiveData<String> = _email
//
//    private val _pin = MutableLiveData<String>("")
//    val pin: LiveData<String> = _pin
//
//    // StateFlow para almacenar el pin convertido a Int
//    private val _pinInt = MutableStateFlow<Int>(0)
//    val pinInt = _pinInt.asStateFlow()
//
//    private val _rol = MutableLiveData<String>("")
//    val rol: LiveData<String> = _rol
//
//    private val _restaurante = MutableLiveData<String>("")
//    val restaurante: LiveData<String> = _restaurante
//
//    private val _isLoginEnable = MutableLiveData<Boolean>(false)
//    val isRegisterEnabled: LiveData<Boolean> = _isLoginEnable
//
//
//
//
//    private val _validName = MutableLiveData(false)
//    val validName: LiveData<Boolean> = _validName
//
//    private val _validEmail = MutableLiveData(false)
//    val validEmail: LiveData<Boolean> = _validEmail
//
//    private val _validPin = MutableLiveData(false)
//    var validPin: LiveData<Boolean> = _validPin
//
//    private val _validRol = MutableLiveData(false)
//    val validRol: LiveData<Boolean> = _validRol
//
//    private val _validRestaurante = MutableLiveData(false)
//    val validRestaurante: LiveData<Boolean> = _validRestaurante
//
//
//
//
//
//    fun onInputChange(input: Inputs, value: String){
//        when (input){
//            Inputs.Nombre -> {
//                _name.value = value
//                _validName.value = value.length > 7
//            }
//            Inputs.Email -> {
//                _email.value = value
//                _validEmail.value = Patterns.EMAIL_ADDRESS.matcher(value).matches()
//            }
//            Inputs.Pin -> {
//
//                _pin.value = value
//                _validPin.value = value.length == 4
//                onLoginChangedPin(newPin = value)
////                _validPin.value = value.length == 4
////                _pin.value = value
////                _validPin.value = value.length == 4
////                onLoginChangedPin(newPin = value)
//
//
//            }
//            Inputs.Rol -> {
//                _rol.value = value
//                _validRol.value = value.isNotEmpty()
//            }
//            Inputs.Restaurante -> {
//                _restaurante.value = value
//                _validRestaurante.value = value.isNotEmpty()
//            }
//            else -> {}
//        }
//
//        _isLoginEnable.value = (
//                        _validName.value!! &&
//                        _validEmail.value!! &&
//                        _validPin.value!! &&
//                        _validRol.value!! &&
//                        _validRestaurante.value!!
//                )
//    }
//    private val storeToken = StoreToken(context)
//    var showAlert by mutableStateOf(false)
//
//    fun registerEmployee() {
//        viewModelScope.launch(Dispatchers.IO) {
//
//            val name = _name.value ?: ""
//            val role = _rol.value ?: ""
//            val email = _email.value ?: ""
//            val pin = _pinInt.value
//            val phone = "0000000000"
//            val address = "Dirección de ejemplo"
//            val comments = "Empleado registrado desde app"
//            val emergencyContact = 12345678
//            val schedule = "Lunes a Viernes 9am-6pm"
//            val assignedTables = emptyList<Int>()// o una lista de IDs si se asignan
//
//            val userEmployee = UserEmployeeModel(
//                identifier = pin, // ✅ Ahora se guarda el PIN
//                name = name,
//                role = role,
//                comments = comments,
//                phone = phone,
//                address = address,
//                email = email,
//                emergency_contact = emergencyContact,
//                work_schedule = schedule,
//                assigned_tables = assignedTables
//            )
//
//            RetrofitService(context).apiService.registerPin(userEmployee)
//                .enqueue(object : Callback<UserEmployeeModel> {
//                    override fun onResponse(
//                        call: Call<UserEmployeeModel>,
//                        response: Response<UserEmployeeModel>
//                    ) {
//                        if (response.isSuccessful) {
//                            val storePin = StorePin(context)
//                            Log.d("Registro", "Empleado registrado: ${Gson().toJson(response.body())}")
//                            viewModelScope.launch {
//                                storePin.savePin(_pinInt.value)
//                            }
//                            navController.popBackStack()
//
//                        } else {
//                            val errorBody = response.errorBody()?.string()
//                            Log.e("Registro", "Error ${response.code()}: $errorBody")
//
//                            when (response.code()) {
//                                422 -> Log.e("Registro", "Datos inválidos - verificar campos requeridos")
//                                409 -> Log.e("Registro", "Conflicto - email o PIN ya existe")
//                                400 -> Log.e("Registro", "Petición mal formada")
//                            }
//
//                            showAlert = true
//                            Log.e("Registro", "Error al registrar: ${response.code()}")
//                        }
//                    }
//
//                    override fun onFailure(
//                        call: Call<UserEmployeeModel>,
//                        t: Throwable
//                    ) {
//                        showAlert = true
//                        Log.e("Registro", "Fallo de red: ${t.message}")
//                    }
//                })
//        }
//    }
//    fun closeAlert() {
//        showAlert = false
//    }
//    fun onLoginChangedPin(newPin: String) {
//
//
////        if (newPin.length <= 4 && newPin.all { it.isDigit() }) {
////            _pin.value = newPin
////            _pinInt.value = newPin.toIntOrNull()!!
////        }
//
//
//
//        if (newPin.length <= 4 && newPin.all { it.isDigit() }) {
//            _pin.value = newPin
//
//            if (newPin.length == 4) {
//                // Convierte el pin a Int
//                val pinValue = newPin.toIntOrNull()
//                if (pinValue != null) {
//                    _pinInt.value = pinValue
//                }
//            }
//        }
//    }
//    fun verificarPin(){
//
//        viewModelScope.launch {
//            val valido = _pin.value == true.toString()
//            if (valido){
//                storeToken.saveTypeUser("empleado")
//                navController.navigate(Routes.Home.route) {
//                    popUpTo(0) { inclusive = true }
//                }
//            }else{
//                Log.e("EmpleadoLogin", "PIN inválido")
//            }
//        }
//    }
//
//
//}

