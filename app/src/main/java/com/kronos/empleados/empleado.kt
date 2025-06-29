package com.kronos.empleados

data class Empleado(
    val nombre: String,
    val email: String,
    val pin: String, // 4 dígitos
    val rol: String,
    val restaurante: String
)
