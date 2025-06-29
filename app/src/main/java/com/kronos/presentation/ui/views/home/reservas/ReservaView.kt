package com.kronos.presentation.ui.views.home.reservas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kronos.empleados.RegistroEmpleadoScreen

@Composable
fun ReservaView(
    navController: NavHostController
) {
    var mostrarRegistro by remember { mutableStateOf(false) }

    if (mostrarRegistro) {
        RegistroEmpleadoScreen(onRegistroExitoso = {
            mostrarRegistro = false
        })
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { mostrarRegistro = true },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Reservas",
                textAlign = TextAlign.Center,
                fontSize = 100.sp
            )
        }
    }
}
