package com.kronos.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import com.kronos.presentation.ui.theme.mesaGris
import com.kronos.enumeration.TipoMesa

open class Mesa(
    open val id: Int,
    open val tipoMesa: TipoMesa,
    open val estado: EstadoMesa,
    open var posicion: MutableState<Offset>,
    open var color: MutableState<Color> = mutableStateOf(mesaGris),
    open val asientos: List<Asiento> = emptyList()
)