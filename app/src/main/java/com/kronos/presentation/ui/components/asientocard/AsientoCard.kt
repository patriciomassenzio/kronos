package com.kronos.presentation.ui.components.asientocard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kronos.presentation.ui.theme.dark90

import com.example.kronos.R
import com.kronos.model.Asiento
import com.kronos.presentation.ui.theme.dark00
import com.kronos.presentation.ui.theme.dark10
import com.kronos.presentation.ui.theme.neutral50
import com.kronos.presentation.ui.views.home.mesaFija


@Preview
@Composable
fun AsientoCardPreview(){
    Column {
        AsientoCard(mesaFija.asientos[2])
//        Spacer(modifier = Modifier.height(15.dp))
//        AsientoCard("A2", "María Pérez", allowChicken = false, allowWeath = true)
//        AsientoCard("A3", "Juan Gómez", allowChicken = true, allowWeath = true)
//        AsientoCard("A4", "Ana Rodríguez", allowChicken = false, allowWeath = false)
//        AsientoCard("A5", "Pedro Sánchez", allowChicken = true, allowWeath = false)
//        AsientoCard("A6", "Laura Fernández", allowChicken = false, allowWeath = true)
//        AsientoCard("A7", "Sofía Ramírez", allowChicken = true, allowWeath = true)
//        AsientoCard("A8", "Diego Herrera", allowChicken = false, allowWeath = false)
//        AsientoCard("A9", "Elena Torres", allowChicken = true, allowWeath = false)
//        AsientoCard("A10", "Miguel Castro", allowChicken = false, allowWeath = true)
//        AsientoCard("B1", "Fernanda López", allowChicken = true, allowWeath = false)
//        AsientoCard("B2", "Javier Ortega", allowChicken = false, allowWeath = true)
//        AsientoCard("B3", "Patricia Vargas", allowChicken = true, allowWeath = true)
//        AsientoCard("B4", "Alejandro Ríos", allowChicken = false, allowWeath = false)
//        AsientoCard("B5", "Gabriela Méndez", allowChicken = true, allowWeath = false)
//        AsientoCard("B6", "Ricardo Jiménez", allowChicken = false, allowWeath = true)
//        AsientoCard("B7", "Natalia Fuentes", allowChicken = true, allowWeath = true)
//        AsientoCard("B8", "Hugo Castillo", allowChicken = false, allowWeath = false)
//        AsientoCard("B9", "Clara Paredes", allowChicken = true, allowWeath = false)
//        AsientoCard("B10", "Daniela Suárez", allowChicken = false, allowWeath = true)
    }

}

@Composable
fun AsientoCard(asiento: Asiento){
    var count by remember { mutableStateOf(2) }

    var expandirPedido by remember{mutableStateOf(true)}
    val chickenIcon = if(asiento.enablePollo) R.drawable.chicken else R.drawable.nochicken

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = dark90)
            .border(
                width = 1.dp,
                color = Color(0xFFDFB7C8),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ){
        //region row titulo
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                modifier = Modifier.weight(1f),
                text = "A${asiento.numero}",
                color = Color(0xFFE5A9B0),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Icon(
                painter = painterResource(chickenIcon),
                contentDescription = "",
                tint = dark10,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
            if(!asiento.enableTrigo){
                Icon(
                    painter = painterResource(R.drawable.no_weath),
                    contentDescription = "",
                    tint = dark10,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { }
                )
            }
        }
        //endregion row titulo

        Text(
            text = asiento.nombreCliente,
            color = dark00,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 2,
            lineHeight = 24.sp,
        )
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(modifier = Modifier.padding(5.dp), color = neutral50)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = dark90)
                .clickable {
                    expandirPedido = !expandirPedido
                }
        ) {
            Text(
                text = "Pedido",
                color = dark10,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.arrow_down),
                contentDescription = "",
                tint = dark00,
                modifier = Modifier.size(30.dp)
            )
        }

        if(expandirPedido){
            asiento.productos.forEach {
                PedidoItem(producto = it)
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(modifier = Modifier.padding(5.dp), color = neutral50)
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(color = dark90).padding(10.dp)
        ) {

            Icon(
                painter = if(count <= 2) painterResource(R.drawable.container) else painterResource(R.drawable.minus_solid) ,
                contentDescription = "",
                tint = dark10,
                modifier = Modifier.size(50.dp).clickable { if (count > 2 ) count = 3 else count-- }
            )
            Text(
                text = count.toString(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = dark10
            )
            Icon(
                painter = painterResource(R.drawable.add),
                contentDescription = "",
                tint = dark10,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { count++ }
            )

        }
}

//    val precioTotal by remember { mutableIntStateOf(0) }
//    Row(
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .width(170.dp)
//            .height(60.dp)
//            .border(
//                width = 1.dp,
//                color = neutral50,
//                shape = RoundedCornerShape(10.dp)
//            )
//            .background(color = dark90)
//            .padding(10.dp)
//
//    ) {
//        Text(
//            text = "Total:",
//            textAlign = TextAlign.Center,
//            fontSize = 30.sp,
//            color = dark50
//
//        )
//        Text(
//            text = "€ %.2f".format(precioTotal),
//            textAlign = TextAlign.Center,
//            fontSize = 30.sp,
//            color = dark00
//
//        )
//
//
//    }





}