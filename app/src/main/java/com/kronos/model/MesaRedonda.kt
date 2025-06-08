package com.kronos.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import com.kronos.presentation.ui.theme.mesaGris
import com.kronos.enumeration.TipoMesa

data class MesaRedonda(
    override val id: Int,
    override val tipoMesa: TipoMesa,
    override val estado: EstadoMesa = EstadoMesa.LIBRE,
    override var posicion: MutableState<Offset>,
    override var color: MutableState<Color> = mutableStateOf(mesaGris),
    override val asientos: List<Asiento> = emptyList(),
    val sillas: Int,
): Mesa(id,tipoMesa,estado,posicion,color, asientos)