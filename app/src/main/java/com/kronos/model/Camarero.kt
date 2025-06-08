package com.kronos.model

data class Camarero(
    val nombre: String = "",
    val mesas: List<Mesa> = emptyList()
)