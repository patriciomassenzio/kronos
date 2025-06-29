package com.kronos.presentation.ui.views.app.admin.room

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kronos.enumeration.Inputs
import com.kronos.model.Restaurant
import com.kronos.model.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(val context: Context)  : ViewModel() {
    val _id = MutableLiveData("")
    val _createdAt = MutableLiveData("")
    val _updateAt = MutableLiveData("")
    val _isDeleted = MutableLiveData(false)

    val _numero = MutableLiveData(0)
    val numero: MutableLiveData<Int> = _numero

    val _descripcion = MutableLiveData("")
    val descripcion: MutableLiveData<String> = _descripcion

    private val _validNumero = MutableLiveData(true)
    val validNumero: LiveData<Boolean> = _validNumero

    private val _enableGuardar = MutableLiveData(false)
    val enableGuardar: LiveData<Boolean> = _enableGuardar

    private val _enableCampos = MutableLiveData(false)
    val enableCampos: LiveData<Boolean> = _enableCampos

    private val _cargando = MutableLiveData(false)
    val cargando: LiveData<Boolean> = _cargando


    val _restaurantes = MutableLiveData(listOf(
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
        Restaurant(
            id = "4",
            createdAt = "2025-04-13T12:00:00Z",
            updateAt = "2025-04-14T09:30:00Z",
            name = "Sushi House",
            phone = "555-456-7890",
            address = "Calle Oriental 234, Ciudad",
            comments = "El mejor sushi de la ciudad"
        ),
        Restaurant(
            id = "5",
            createdAt = "2025-04-14T18:45:00Z",
            updateAt = "2025-04-14T18:45:00Z",
            name = "Taquería Don Pepe",
            phone = "555-567-8901",
            address = "Avenida Norte 567, Ciudad",
            comments = "Tacos y comida mexicana tradicional"
        )
    ))
    val restaurantes: MutableLiveData<List<Restaurant>> = _restaurantes

    fun onInputChange(input: Inputs, value: String){
        when (input){
            Inputs.Numero -> {
                _numero.value = value.toInt()
                _validNumero.value = value.toInt() > 0
            }
            Inputs.Descripcion -> {
                _descripcion.value = value
            }

            else -> {}
        }

        _enableGuardar.value = _validNumero.value as Boolean

    }
    fun onNuevoRestaurant(){

        _enableCampos.value = true
        limpiarCampos()
    }
    fun onCancelar(){
        _enableCampos.value = false
        limpiarCampos()
    }
    fun onGuardar(){
        viewModelScope.launch(Dispatchers.IO) {
            val request = Room(
                id = _id.value!!,
                createdAt = _createdAt.value?:"",
                updateAt = _updateAt.value!!,
                isDeleted = _isDeleted.value!!,
                numero = _numero.value!!,
                descripcion = _descripcion.value!!

            )

        }
    }
    fun onDeleteClick(room: Room){}
    fun onSelectClick(room: Room){

    }
    fun limpiarCampos(){
        _id.value = ""
        _createdAt.value = ""
        _updateAt.value = ""
        _numero.value = 0


    }

}



