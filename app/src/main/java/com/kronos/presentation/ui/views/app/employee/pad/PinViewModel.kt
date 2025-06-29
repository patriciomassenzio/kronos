package com.kronos.presentation.ui.views.app.employee.pad



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PinViewModel: ViewModel() {


    private val _textValue =  MutableLiveData<String>()
    val textValue: LiveData<String> = _textValue

    private val _intValue = MutableLiveData<Int?>(null)
    val intValue: LiveData<Int?> = _intValue

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> get() = _navigationEvent

    private val _pin =  MutableLiveData<String>()
    val pin: LiveData<String> = _pin

    // Cambia el valor de texto y lo convierte a Int si es válido
    fun onTextChanged(newValue: String) {
        if (newValue.all { it.isDigit() } && newValue.length <= 4) {
            _textValue.value = newValue
            _intValue.value = newValue.toIntOrNull()
        } else if (newValue.isEmpty()) {
            _textValue.value = newValue
            _intValue.value = null // Restablecer a null si el valor es vacío
        }
    }


}














//    private var _textValue = MutableLiveData<String>()
//    var textValue: LiveData<String> = _textValue
//
//    private var _intValue = MutableLiveData<Int?>(null)
//    var intValue: MutableLiveData<Int?> = _intValue
//
//
//
//    fun onTextChanged(newValue: String) {
//        _textValue.value = newValue
//        val intValue = newValue.toIntOrNull()
//        _intValue.value = intValue
//
//
//    }
//
//
//
//
//    fun onPinChanged(newPin: String) {
//        if (newPin.length <= 4 && newPin.all { it.isDigit() }) {
//            _pin.value = newPin
//        }
//    }
