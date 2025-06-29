package com.kronos.presentation.ui.views.app.admin.restaurant

import android.R.id.message
import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kronos.core.network.KronosApi
import com.kronos.core.network.RetrofitService
import com.kronos.enumeration.Inputs
import com.kronos.model.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RestaurantViewModel(private val context: Context) : ViewModel() {

    val _id = MutableStateFlow("")
    val _createdAt = MutableStateFlow("")
    val _updateAt = MutableStateFlow("")
    val _isDeleted = MutableStateFlow(false)

    val _name = MutableStateFlow<String>("")
    val name:StateFlow<String> = _name

    val _phone = MutableStateFlow<String>("")
    val phone: StateFlow<String> = _phone

    val _address = MutableStateFlow<String>("")
    val address: StateFlow<String> = _address

    val _comments = MutableStateFlow<String>("")
    val comments: StateFlow<String> = _comments

    private val _validName = MutableStateFlow(true)
    val validName: StateFlow<Boolean> = _validName

    private val _validAddress = MutableStateFlow(true)
    val validAddress: StateFlow<Boolean> = _validAddress

    private val _validPhone = MutableStateFlow(true)
    val validPhone: StateFlow<Boolean> = _validPhone

    private val _enableGuardar = MutableStateFlow(true)
    val enableGuardar: StateFlow<Boolean> = _enableGuardar

    private val _enableCampos = MutableStateFlow(true)
    val enableCampos: StateFlow<Boolean> = _enableCampos

    private val _cargando = MutableStateFlow(true)
    val cargando: StateFlow<Boolean> = _cargando

    val _restaurantes = MutableStateFlow(listOf(
        Restaurant(
            id = "1",
            createdAt = "2025-04-10T14:30:00Z",
            updateAt = "2025-04-10T14:30:00Z",
            name = "La Parrilla Gourmet",
            phone = "555-123-4567",
            address = "Calle Principal 123, Ciudad",
            comments = "Especialidad en carnes a la parrilla"
        ),
        Restaurant(
            id = "2",
            createdAt = "2025-04-11T10:15:00Z",
            updateAt = "2025-04-12T08:45:00Z",
            name = "El Rincón Marino",
            phone = "555-234-5678",
            address = "Avenida Costera 456, Ciudad",
            comments = "Mariscos frescos todos los días"
        ),
        Restaurant(
            id = "3",
            createdAt = "2025-04-12T16:20:00Z",
            updateAt = "2025-04-12T16:20:00Z",
            name = "Pasta Bella",
            phone = "555-345-6789",
            address = "Plaza Central 789, Ciudad",
            comments = "Auténtica cocina italiana"
        ),
//        Restaurant(
//            id = "4",
//            createdAt = "2025-04-13T12:00:00Z",
//            updateAt = "2025-04-14T09:30:00Z",
//            name = "Sushi House",
//            phone = "555-456-7890",
//            address = "Calle Oriental 234, Ciudad",
//            comments = "El mejor sushi de la ciudad"
//        ),
//        Restaurant(
//            id = "5",
//            createdAt = "2025-04-14T18:45:00Z",
//            updateAt = "2025-04-14T18:45:00Z",
//            name = "Taquería Don Pepe",
//            phone = "555-567-8901",
//            address = "Avenida Norte 567, Ciudad",
//            comments = "Tacos y comida mexicana tradicional"
//        )
    ))
    val restaurantes: MutableStateFlow<List<Restaurant>> = _restaurantes

    fun onInputChange(input: Inputs, value: String){
        when (input){
            Inputs.Name -> {
                _name.value = value
                _validName.value = value.length > 4
            }
            Inputs.Phone -> {
                _phone.value = value
                _validPhone.value = Patterns.PHONE.matcher(value).matches()
            }
            Inputs.Address -> {
                _address.value = value
                _validAddress.value = value.length > 5
            }
            Inputs.Comments -> {
                _comments.value = value
            }
            else -> {}
        }

        _enableGuardar.value =
                _validName.value as Boolean
                && _validPhone.value as Boolean
                && _validAddress.value as Boolean
    }
    fun onNuevoRestaurant(){
        limpiarCampos()
        _enableCampos.value = true
    }
    fun onCancelar(){
        limpiarCampos()
        _enableCampos.value = false
    }
    fun onGuardar() {
        val name = _name.value.trim()
        val phone = _phone.value.trim()
        val address = _address.value.trim()
        val comments = _comments.value.trim()

        // Validaciones previas
        if (name.isEmpty()) {
            Log.e("onGuardar", "Nombre del restaurante es obligatorio.")
            return
        }

        if (phone.isEmpty()) {
            Log.e("onGuardar", "Teléfono del restaurante es obligatorio.")
            return
        }

        if (address.isEmpty()) {
            Log.e("onGuardar", "Dirección del restaurante es obligatoria.")
            return
        }

        val request = Restaurant(
            id = _id.value,
            createdAt = _createdAt.value,
            updateAt = _updateAt.value,
            isDeleted = _isDeleted.value,
            name = name,
            phone = phone,
            address = address,
            comments = comments
        )

        Log.d("onGuardar", "Request a enviar: ${Gson().toJson(request)}")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _cargando.value = true

                val response = RetrofitService(context).apiService.restaurants(request)

                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("onGuardar", "Respuesta del servidor: ${Gson().toJson(data)}")

                    data?.let {
                        _restaurantes.value = _restaurantes.value + it
                    }
                    limpiarCampos()
                    _enableCampos.value = false
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("onGuardar", "Error ${response.code()}: $errorBody")
                }

            } catch (e: Exception) {
                Log.e("onGuardar", "Excepción: ${e.localizedMessage}")
            } finally {
                _cargando.value = false
            }
        }
    }

    fun onEditClick(restaurante: Restaurant){
        _id.value = restaurante.id
        _createdAt.value = restaurante.createdAt
        _updateAt.value = restaurante.updateAt
        _isDeleted.value = restaurante.isDeleted
        _name.value = restaurante.name
        _phone.value = restaurante.phone
        _address.value = restaurante.address
        _comments.value = restaurante.comments
    }
    fun onDeleteClick(restaurante: Restaurant){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _cargando.value = true
                val response = RetrofitService(context).apiService.deleteRestaurants(restaurante.id)
                if (response.isSuccessful) {
                    _restaurantes.value = _restaurantes.value.filterNot { it.id == restaurante.id }
                    Log.d("onDeleteClick", "Restaurante eliminado")
                } else {
                    Log.e("onDeleteClick", "Error ${response.code()}: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("onDeleteClick", "Excepción: ${e.localizedMessage}")
            } finally {
                _cargando.value = false
            }
        }

    }
    fun onSelectClick(restaurante: Restaurant){


    }
    fun limpiarCampos(){
        _id.value = ""
        _createdAt.value = ""
        _updateAt.value = ""
        _name.value = ""
        _phone.value = ""
        _address.value = ""
        _comments.value = ""
    }
}

