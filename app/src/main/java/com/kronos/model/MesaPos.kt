package com.kronos.model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Offset
import com.kronos.enumeration.TipoMesa


data class MesaPos(
    override val id: Int,
    override val tipoMesa: TipoMesa,
    override val estado: EstadoMesa = EstadoMesa.None,
    override var posicion: MutableState<Offset>
): Mesa(id,tipoMesa,estado,posicion)
