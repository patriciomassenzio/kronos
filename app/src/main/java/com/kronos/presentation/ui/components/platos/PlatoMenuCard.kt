package com.kronos.presentation.ui.components.platos

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
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
import com.kronos.presentation.ui.theme.dark10
import com.kronos.presentation.ui.theme.dark90
import com.kronos.presentation.ui.theme.neutral50

@Composable
fun PlatoMenu(
    titulo: String,
    produto: String,
    opciones: List<String> = emptyList(),
    modifier: Modifier


){
    var countItem by remember { mutableStateOf(0) }
    val opcionesSeleccionadas = remember { mutableStateMapOf<String, Boolean>() }
    Column(
        modifier = modifier
            .width(300.dp)
//            .height(424.dp)
            .background(color = dark90)
            .border(
                width = 1.dp,
                color = Color(0xFFDFB7C8),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = titulo,
            color = dark10,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
        HorizontalDivider(modifier = Modifier.padding(5.dp), color = neutral50)
        Text(
            text = produto,
            color = dark10,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal

        )
        Spacer(modifier = Modifier.height(20.dp))
        if (opciones.isNotEmpty()) {

            Column {
                opciones.forEach { opcion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                opcionesSeleccionadas[opcion] =
                                    !(opcionesSeleccionadas[opcion] ?: false)
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = opcion,
                            color = dark10,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Checkbox(
                            checked = opcionesSeleccionadas[opcion] ?: false,
                            onCheckedChange = { isChecked ->
                                opcionesSeleccionadas[opcion] = isChecked
                            }
                        )

                    }

                }
            }

        }

        HorizontalDivider(modifier = Modifier.padding(5.dp), color = neutral50)
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = dark90).padding(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.container),
                contentDescription = "Reset",
                tint = dark10,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { countItem = 0 }
            )
            Text(
                text = "%02d".format(countItem),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = dark10
            )
            Icon(
                painter = painterResource(R.drawable.add),
                contentDescription = "agregar",
                tint = dark10,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { countItem++ }
            )

        }


    }

}