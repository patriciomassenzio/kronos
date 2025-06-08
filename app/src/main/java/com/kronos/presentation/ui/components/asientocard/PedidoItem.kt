package com.kronos.presentation.ui.components.asientocard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kronos.model.Producto
import com.kronos.presentation.ui.components.inputs.KronosInput
import com.kronos.presentation.ui.theme.dark00
import com.kronos.presentation.ui.theme.dark10
import com.kronos.presentation.ui.theme.dark50
import com.kronos.presentation.ui.theme.dark85
import com.kronos.presentation.ui.theme.dark90

@Preview
@Composable
fun PedidoItemPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        PedidoItem(producto = Producto("Pollo al horno", 53.74))
    }
}

@Composable
fun PedidoItem(producto: Producto){
    var editar by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf("00.00") }
    val formattedAmount = "+ €% .2f".format(producto.precio)
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = dark90)
            .padding(1.dp)
    ) {
        if(!editar){
            Text(
                text = producto.nombre,
                color = dark10,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = formattedAmount, fontSize = 17.sp, color = dark10)
        }
        else{
            KronosInput("", producto.nombre, KeyboardType.Number, true, {}, Modifier.weight(1f))
            Spacer(modifier = Modifier.width(10.dp))
            KronosInput("", formattedAmount, KeyboardType.Number, true, {}, Modifier.weight(1f), false)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Checkbox(
            checked = editar,
            onCheckedChange = { editar = !editar },
            modifier = Modifier.size(18.dp),
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF57474c),
                uncheckedColor = Color(0xFFDFB7C8),
                        disabledCheckedColor = Color(0xFF57474c),
                disabledUncheckedColor = Color(0xFFDFB7C8),
                checkmarkColor = Color.White
            )
        )
    }
}

fun formatPrecio(precio: Double): String {
    return "+ € ${String.format("%070.2f", precio)}"
}
