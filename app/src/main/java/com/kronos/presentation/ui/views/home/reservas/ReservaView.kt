package com.kronos.presentation.ui.views.home.reservas

import RegistroEmpleadoScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import com.kronos.empleados.Sesion


@Composable
fun ReservaView(
    navController: NavHostController
) {
    val context = LocalContext.current
    var mostrarRegistro by remember { mutableStateOf(false) }
    var hayEmpleados by remember { mutableStateOf(existenEmpleados(context)) }

    // Si no hay empleados registrados mostramos el registro automáticamente
    LaunchedEffect(Unit) {
        if (!hayEmpleados) {
            mostrarRegistro = true
        }
    }

    if (mostrarRegistro) {
        RegistroEmpleadoScreen(onRegistroExitoso = {
            mostrarRegistro = false
            hayEmpleados = existenEmpleados(context)
        })
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    // Solo el encargado puede registrar si ya existen empleados
                    if (!hayEmpleados || Sesion.empleadoActual?.rol == "Encargado") {
                        mostrarRegistro = true
                    }
                },
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

private fun existenEmpleados(context: android.content.Context): Boolean {
    val sharedPrefs = context.getSharedPreferences("empleados", android.content.Context.MODE_PRIVATE)
    return sharedPrefs.all.isNotEmpty()
}