package com.kronos.presentation.ui.views.home.pedidos.pedidodetalle.menucombo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kronos.R
import com.kronos.presentation.ui.components.configuracionPedidos.Categorias
import com.kronos.presentation.ui.components.platos.PlatoMenu
import com.kronos.presentation.ui.components.topBar.TopBarMenuCombo
import com.kronos.presentation.ui.theme.dark00
import com.kronos.presentation.ui.theme.dark10
import com.kronos.presentation.ui.theme.dark50
import com.kronos.presentation.ui.theme.dark80
import com.kronos.presentation.ui.theme.dark90
@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun MenuComboScreenPreview(){
    MenuComboScreen(asiento = "A1", NavController(LocalContext.current))

}


@Composable
fun MenuComboScreen(
    asiento: String,
    navController: NavController,
//    nombreCliente: String,
//    allowChicken: Boolean,
//    allowWeath: Boolean,
//      mesa: Mesa,
//    menu: Menu
    modifier: Modifier = Modifier,
//    precio: Double
//    onClick: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dark80)
            .padding(20.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarMenuCombo()
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp).align(Alignment.CenterHorizontally)
                .background(color = dark80)
        ) {
            Text(
                text = "Orden",
                color = dark10,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = asiento,
                color = Color(0xFFE5A9B0),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "€ 000.0",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = dark00

            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.move),
                contentDescription = "",
                tint = dark50,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
            Icon(
                painter = painterResource(R.drawable.edit),
                contentDescription = "",
                tint = dark50,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
            Icon(
                painter = painterResource(R.drawable.cancel),
                contentDescription = "",
                tint = dark50,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row (  horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ){
            PlatoMenu(
                titulo = "Primero",
                produto =  "Risotto",
                modifier = Modifier.weight(1f)

            )
            PlatoMenu(
                titulo = "Segundo",
                produto =  "Ñoquis",
                modifier = Modifier.weight(1f),
                opciones = listOf("4 quesos","Boloñesa", "Presto" )
            )

            PlatoMenu(
                titulo = "Segundo",
                produto =  "Ñoquis",
                modifier = Modifier.weight(1f),
                opciones = listOf("4 quesos","Boloñesa", "Presto" )
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Categorias()


    }


}