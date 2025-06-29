package com.kronos.empleados

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import androidx.core.content.edit

@Composable
fun RegistroEmpleadoScreen(onRegistroExitoso: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("") }
    var restaurante by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(
            value = pin,
            onValueChange = {
                if (it.length <= 4 && it.all { char -> char.isDigit() }) pin = it
            },
            label = { Text("PIN (4 dígitos)") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(value = rol, onValueChange = { rol = it }, label = { Text("Rol en el restaurante") })
        OutlinedTextField(value = restaurante, onValueChange = { restaurante = it }, label = { Text("Nombre del restaurante") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (pin.length == 4) {
                val nuevoEmpleado = Empleado(nombre, email, pin, rol, restaurante)
                guardarEmpleado(context, nuevoEmpleado)
                onRegistroExitoso()
            }
        }) {
            Text("Registrarse")
        }
    }
}
fun guardarEmpleado(context: Context, empleado: Empleado) {
    val sharedPrefs = context.getSharedPreferences("empleados", Context.MODE_PRIVATE)
    sharedPrefs.edit {
        val json = Gson().toJson(empleado)
        putString(empleado.pin, json)
    }
}

