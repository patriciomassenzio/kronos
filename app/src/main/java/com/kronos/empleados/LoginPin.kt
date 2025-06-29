package com.kronos.empleados

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

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

@Composable
fun LoginEmpleadoScreen(onLoginExitoso: (Empleado) -> Unit) {
    val context = LocalContext.current
    var pin by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pin,
            onValueChange = {
                if (it.length <= 4 && it.all { char -> char.isDigit() }) pin = it
            },
            label = { Text("Ingresar PIN") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val empleado = buscarEmpleadoPorPin(context, pin)
            if (empleado != null) {
                onLoginExitoso(empleado)
            } else {
                Toast.makeText(context, "PIN inválido", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Ingresar")
        }
    }
}

fun buscarEmpleadoPorPin(context: Context, pin: String): Empleado? {
    val sharedPrefs = context.getSharedPreferences("empleados", Context.MODE_PRIVATE)
    val json = sharedPrefs.getString(pin, null)
    return json?.let { Gson().fromJson(it, Empleado::class.java) }
}
