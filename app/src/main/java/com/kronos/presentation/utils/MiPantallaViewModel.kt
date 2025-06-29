package com.kronos.presentation.utils

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.protobuf.Value
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MiPantallaViewModelprivate( val context: Context, private val navController: NavController): ViewModel()  {
    private val _Pastilla= MutableLiveData<String>("")
    val pastilla: LiveData<String> = _Pastilla

    private val _validPastilla = MutableLiveData<Boolean>(true)
    val validPastilla: LiveData<Boolean> = _validPastilla

    private  val _isElegir = MutableLiveData(false)
    val isElegir:LiveData<Boolean> = _isElegir

    fun onSelected (value: String,){
        if (value == "pastilla"){
            _Pastilla.value
            _validPastilla.value

        }else{}

        _isElegir.value = _validPastilla as Boolean
        Log.d("value", "validPastilla${_validPastilla}")


    }

}