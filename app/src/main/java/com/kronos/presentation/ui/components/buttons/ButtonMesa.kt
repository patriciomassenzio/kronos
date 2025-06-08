package com.kronos.presentation.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kronos.R
import com.kronos.model.EstadoMesa
import com.kronos.model.Mesa
import com.kronos.presentation.ui.theme.dark00
import com.kronos.presentation.ui.theme.dark90
import com.kronos.presentation.ui.theme.darkOcupada
import com.kronos.presentation.ui.theme.mesaAzul
import com.kronos.presentation.ui.theme.mesaGris
import com.kronos.presentation.ui.theme.mesaVerde
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun ButtonMesa(
    mesa: Mesa,
    onClick: () -> Unit
){

    val colorFondo = when (mesa.estado) {
        EstadoMesa.LIBRE -> mesaVerde
        EstadoMesa.OCUPADO, -> mesaAzul
        else -> mesaGris
    }

    Box(
        modifier = Modifier
            .clickable {onClick() }
            .size(50.dp)
            .background(
                colorFondo ,
                shape = RoundedCornerShape(5.dp)
            ) ,
        contentAlignment = Alignment.Center

    ) {
        Column(
            //modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (mesa.estado== EstadoMesa.OCUPADO){
                Icon(
                    painter = painterResource(R.drawable.cooking),
                    contentDescription = "",
                    tint = dark00
                )
            }
            Text(
                text = "Mesa 01",
                textAlign = TextAlign.Center,
                modifier = Modifier,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 10.sp
            )
            Cronometro()
        }
    }
}

@Composable
fun Cronometro(){
    var tiempoSegundos by remember { mutableStateOf(0) } // Almacena el tiempo en segundos
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L) // Espera 1 segundo
            tiempoSegundos++ // Incrementa el contador
        }


    }
    val horas = tiempoSegundos / 3600
    val minutos = (tiempoSegundos % 3600) / 60
    val segundos = tiempoSegundos % 60
    Text(
        text = String.format(Locale.ROOT,"%02d:%02d:%02d", horas,minutos,segundos),
        textAlign = TextAlign.Center ,
        modifier = Modifier,
        color = dark90,
        fontSize = 10.sp
    )


}
