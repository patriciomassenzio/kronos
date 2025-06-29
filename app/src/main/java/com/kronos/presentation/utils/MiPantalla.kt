package com.kronos.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kronos.presentation.ui.components.buttons.KronosFilledButton
import com.kronos.presentation.ui.components.inputs.KronosDropdown

@Composable
fun MiPantalla(
    navController: NavController,
    viewModel: MiPantallaViewModelprivate

   ){
    val isElegir by viewModel.isElegir.observeAsState(initial = false)

    Box(modifier = Modifier.fillMaxSize()){

        Pastilla()

        Button(onClick = {}, ) {
            Text(text = "elegir")
        }


    }
    
}
@Composable
fun Pastilla(){
    val pastilla : String by rememberSaveable { mutableStateOf("") }
    val validPastilla by rememberSaveable { mutableStateOf(true) }
    KronosDropdown(
        title = "Elegir pastilla",
        options = listOf("acetamenofen", " valtan", "presion", "fosforo", "b3"),
        selectedOption = pastilla,
        isValid = validPastilla,
        onOptionSelected = TODO(),
        modifier = Modifier.fillMaxWidth(),
    )
}