package com.kronos.presentation.ui.components.appBars

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kronos.model.Camarero
import com.kronos.navigation.Routes
import com.kronos.presentation.ui.components.buttons.ButtonMesa

@Composable
fun CamareroBar(camarero: Camarero, navController: NavController) {
    Text(
        text = "Mesas a cargo de ${camarero.nombre}",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        style = TextStyle(color = Color.White),
        fontSize = 15.sp
    )

    Row(
        modifier = Modifier.fillMaxWidth().padding(),
        horizontalArrangement = Arrangement.spacedBy(18.dp)

    ) {
        camarero.mesas.forEach {
            ButtonMesa(mesa = it, onClick ={ navController.navigate(Routes.ConfiguracionPedidos.route) })
        }
    }
}