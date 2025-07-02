package com.kronos.empleados

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson

object EmpleadoStorage {
    fun guardarEmpleado(context: Context, empleado: Empleado) {
        val sharedPrefs = context.getSharedPreferences("empleados", Context.MODE_PRIVATE)
        sharedPrefs.edit {
            val json = Gson().toJson(empleado)
            putString(empleado.pin, json)
        }
    }

    fun buscarEmpleadoPorPin(context: Context, pin: String): Empleado? {
        val sharedPrefs = context.getSharedPreferences("empleados", Context.MODE_PRIVATE)
        val json = sharedPrefs.getString(pin, null)
        return json?.let { Gson().fromJson(it, Empleado::class.java) }
    }
}