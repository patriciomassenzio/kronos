package com.kronos.presentation.ui.components.configuracionPedidos

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kronos.R
import com.kronos.presentation.ui.theme.dark10
import com.kronos.presentation.ui.theme.dark40
import com.kronos.presentation.ui.theme.dark50
import com.kronos.presentation.ui.theme.dark80
import com.kronos.presentation.ui.theme.dark85
import com.kronos.presentation.ui.theme.divider
import com.kronos.presentation.ui.theme.neutral50

@Preview
@Composable
fun CategoriaPedidosPreview(){
    Categorias()
//    Text(text = "hola")
}

@Composable
fun Categorias(
    checked: Boolean = false,
    checkedChange: (() -> Unit) ? = null,

){
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = dark80)
    ) {
        Text(
            text = "Categorias",
            color = dark10,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(5.dp)
        )
        Switch(
            onCheckedChange = {},
            checked = checked,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFDFB7C8),
                checkedTrackColor =  Color(0xFFDFB7C8),
                uncheckedThumbColor =  Color(0xFF57474c),
                uncheckedTrackColor =  Color(0xFFDFB7C8),
            )
//            modifier = Modifier.padding(5.dp)
        )
        Icon(
            painter = painterResource(R.drawable.b_cake),
            contentDescription = "",
            tint = dark10,
            modifier = Modifier.padding(5.dp)
                .size(50.dp)
                .clickable { }
        )
        VerticalDivider( Modifier.padding(5.dp).height(34.dp), color = divider)

        IconCategorias(
            checked = true,
            onCheckedChange = {},
            iconChecked = R.drawable.peanut,
            iconUnchecked = R.drawable.peanut
        )

        IconCategorias(
            checked = true,
            onCheckedChange = {},
            iconChecked = R.drawable.nochicken,
            iconUnchecked = R.drawable.chicken
        )
        IconCategorias(
            checked = true,
            onCheckedChange = {},
            iconChecked = R.drawable.milk,
            iconUnchecked = R.drawable.milk
        )
        IconCategorias(
            checked = true,
            onCheckedChange = {},
            iconChecked = R.drawable.no_weath,
            iconUnchecked = R.drawable.no_weath
        )
        IconCategorias(
            checked = true,
            onCheckedChange = {},
            iconChecked = R.drawable.fish,
            iconUnchecked = R.drawable.fish
        )
        IconCategorias(
            checked = false,
            onCheckedChange = {},
            iconChecked = R.drawable.vegan_1,
            iconUnchecked = R.drawable.vegan_1
        )
        IconCategorias(
            checked = false,
            onCheckedChange = {},
            iconChecked = R.drawable.vegan,
            iconUnchecked = R.drawable.vegan
        )


    }
}
@Composable
fun IconCategorias(
    iconUnchecked: Int,
    iconChecked: Int,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier .padding(5.dp)
    ) {

        Checkbox(
            checked = checked ,
            onCheckedChange = { onCheckedChange(it)},
            modifier = Modifier.size(32.dp),
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFFDFB7C8),
                uncheckedColor = Color(0xFF57474c),
                checkmarkColor = Color(0xFFDFB7C8)
            )
        )

        val iconPainter = painterResource(if (checked) iconChecked else iconUnchecked)
        Icon(
            painter = iconPainter,
            contentDescription =  if (checked) "Seleccionado" else "No seleccionado",
            tint = if (checked )dark50 else dark40,
            modifier = Modifier.padding(5.dp)
                .size(50.dp)
                .clickable { onCheckedChange(!checked) }
        )
    }

}
