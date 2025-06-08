package com.kronos.presentation.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kronos.model.EstadoMesa

class EstadoMesaViewModel : ViewModel()  {



    // Estado observable para el estado de la mesa
    private val _estadoMesa = mutableStateOf(EstadoMesa.LIBRE)
    val estadoMesa: State<EstadoMesa> = _estadoMesa

    // Función para cambiar el estado a OCUPADO
    fun setOcupado() {
        _estadoMesa.value = EstadoMesa.OCUPADO
    }
    // Función para cambiar el estado a BLOQUEADO
    fun setBloqueado() {
        _estadoMesa.value = EstadoMesa.BLOQUEADO
    }

    // Función para cambiar el estado a LIBRE
    fun setLibre() {
        _estadoMesa.value = EstadoMesa.LIBRE
    }

    // Alternar entre estados
    fun alternarEstado(value: String) {
        _estadoMesa.value = if (_estadoMesa.value == EstadoMesa.LIBRE) {
            EstadoMesa.OCUPADO
        } else if(_estadoMesa.value == EstadoMesa.OCUPADO) {
            EstadoMesa.BLOQUEADO
        } else{
            EstadoMesa.LIBRE

        }

    }
}