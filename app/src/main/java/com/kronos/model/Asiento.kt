package com.kronos.model

data class Asiento(
    val numero: Int,
    val enablePollo: Boolean = true,
    val enableTrigo: Boolean = true,
    val nombreCliente: String,
    val productos: List<Producto> = emptyList()
)