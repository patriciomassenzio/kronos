package com.kronos.presentation.ui.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.kronos.presentation.ui.theme.dark00

//@Preview
@Composable
fun InputDialogMesaRedonda(
    modifier: Modifier,
    onCancelar: () -> Unit,
    onGuardar:(String) -> Unit,

){
    var text by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
           //onCancelar()
        }
    ) {
        Column (modifier = Modifier.background(color = Color.White)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().background(color = Color.White).border(1.dp, color = Color(0xFFCF3C34) )
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
                        .align(Alignment.CenterHorizontally),
                    value = text,
                    label = {Text(text = "Sillas")},
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }){
                            text = newValue
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(

//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
                ) {
                    ButtonDialog(
                        "Cancelar",
                        onClick = {
                            onCancelar()

                    })

                    Spacer(modifier = Modifier.weight(1f))

                    ButtonDialog(
                        "Guardar",
                        onClick = {
                            if(text.isDigitsOnly()){
                                //es numero, esta todo bien
                                onGuardar(text)
                            }
                            else{
                                //no es digitos, tiene puntos u otra cosa
                            }
                        }
                    )
                }








            }

//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth().background(color = Color.White)
//        ) {
//            Spacer(modifier = Modifier.height(15.dp))
//            TextField(
//                modifier = Modifier
//                    .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
//                    .align(Alignment.CenterHorizontally),
//                value = text,
//                label = {Text(text = "Sillas")},
//                onValueChange = {text = it },
//                colors = TextFieldDefaults.colors(
//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedContainerColor = MaterialTheme.colorScheme.background,
//                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
//                    focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
//                    unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
//                )
//            )
//
//            Spacer(modifier = Modifier.height(15.dp))
//            Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
//            ) {
//                ButtonDialog("Cancelar")
//                Spacer(modifier = Modifier.weight(1f))
//                ButtonDialog("Guardar",)
//            }
//
//        }
        }
    }



}

//@Preview
@Composable
fun InputDialogMesaCuadrada(
    modifier: Modifier,
    onCancelar: () -> Unit,
    onGuardar:(String, String) -> Unit,

    ){
    var ancho by remember { mutableStateOf("") }
    var alto by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            //onCancelar()
        }
    ) {
        Column (modifier = Modifier.background(color = Color.White)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().background(color = Color.White).border(1.dp, color = Color(0xFFCF3C34) )
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
                        .align(Alignment.CenterHorizontally),
                    value = ancho,
                    label = {Text(text = "Ancho")},
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit()}){
                            ancho = newValue
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(

//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
                        .align(Alignment.CenterHorizontally),
                    value = alto,
                    label = {Text(text = "Alto")},
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }){
                            alto= newValue
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(

//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
                ) {
                    ButtonDialog(
                        "Cancelar",
                        onClick = {
                            onCancelar()

                        })

                    Spacer(modifier = Modifier.weight(1f))

                    ButtonDialog(
                        "Guardar",
                        onClick = {
                            if(ancho.isDigitsOnly()){
                                //es numero, esta todo bien
                                onGuardar(ancho, alto)
                            }
                            else{
                                //no es digitos, tiene puntos u otra cosa
                            }
                        }
                    )
                }

            }

//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth().background(color = Color.White)
//        ) {
//            Spacer(modifier = Modifier.height(15.dp))
//            TextField(
//                modifier = Modifier
//                    .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
//                    .align(Alignment.CenterHorizontally),
//                value = text,
//                label = {Text(text = "Sillas")},
//                onValueChange = {text = it },
//                colors = TextFieldDefaults.colors(
//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedContainerColor = MaterialTheme.colorScheme.background,
//                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
//                    focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
//                    unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
//                )
//            )
//
//            Spacer(modifier = Modifier.height(15.dp))
//            Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
//            ) {
//                ButtonDialog("Cancelar")
//                Spacer(modifier = Modifier.weight(1f))
//                ButtonDialog("Guardar",)
//            }
//
//        }
        }
    }

}


@Composable
fun InputDialogMesaOvalada(
    modifier: Modifier,
    onCancelar: () -> Unit,
    onGuardar:(String, String) -> Unit,

    ){
    var alto by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            //onCancelar()
        }
    ) {
        Column (modifier = Modifier.background(color = Color.White)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().background(color = Color.White).border(1.dp, color = Color(0xFFCF3C34) )
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
                        .align(Alignment.CenterHorizontally),
                    value = alto,
                    label = {Text(text = "Alto")},
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit()}){
                            alto = newValue
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(

//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
                        .align(Alignment.CenterHorizontally),
                    value = text2,
                    label = {Text(text = "Ancho")},
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }){
                            text2 = newValue
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(

//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
                ) {
                    ButtonDialog(
                        "Cancelar",
                        onClick = {
                            onCancelar()

                        })

                    Spacer(modifier = Modifier.weight(1f))

                    ButtonDialog(
                        "Guardar",
                        onClick = {
                            if(alto.isDigitsOnly()){
                                //es numero, esta todo bien
                                onGuardar(alto, text2)
                            }
                            else{
                                //no es digitos, tiene puntos u otra cosa
                            }
                        }
                    )
                }

            }

//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth().background(color = Color.White)
//        ) {
//            Spacer(modifier = Modifier.height(15.dp))
//            TextField(
//                modifier = Modifier
//                    .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
//                    .align(Alignment.CenterHorizontally),
//                value = text,
//                label = {Text(text = "Sillas")},
//                onValueChange = {text = it },
//                colors = TextFieldDefaults.colors(
//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedContainerColor = MaterialTheme.colorScheme.background,
//                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
//                    focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
//                    unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
//                )
//            )
//
//            Spacer(modifier = Modifier.height(15.dp))
//            Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
//            ) {
//                ButtonDialog("Cancelar")
//                Spacer(modifier = Modifier.weight(1f))
//                ButtonDialog("Guardar",)
//            }
//
//        }
        }
    }

}



@Composable
fun InputDialogMesaBarra(
    modifier: Modifier,
    onCancelar: () -> Unit,
    onGuardar:( String) -> Unit,


    ){
//    var text by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
//mostrar las 4 mesas
    //al hacer click, llamar a onGuardar(lado)
    Dialog(
        onDismissRequest = {
            //onCancelar()
        },

    ) {
        Column (modifier = Modifier.background(color = Color.White)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().background(color = Color.White).border(1.dp, color = Color(0xFFCF3C34) )
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
                        .align(Alignment.CenterHorizontally),
                    value = text2,
                    label = {Text(text = "Lado")},
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }){
                            text2 = newValue
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(

//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
                ) {
                    ButtonDialog(
                        "Cancelar",
                        onClick = {
                            onCancelar()

                        })

                    Spacer(modifier = Modifier.weight(1f))

                    ButtonDialog(
                        "Guardar",
                        onClick = {
                            if(text2.isDigitsOnly()){
                                //es numero, esta todo bien
                                onGuardar( text2)
                            }
                            else{
                                //no es digitos, tiene puntos u otra cosa
                            }
                        }
                    )
                }

            }

//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth().background(color = Color.White)
//        ) {
//            Spacer(modifier = Modifier.height(15.dp))
//            TextField(
//                modifier = Modifier
//                    .background( color = MaterialTheme.colorScheme.onSecondary,shape = RoundedCornerShape(25.dp))
//                    .align(Alignment.CenterHorizontally),
//                value = text,
//                label = {Text(text = "Sillas")},
//                onValueChange = {text = it },
//                colors = TextFieldDefaults.colors(
//                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    focusedContainerColor = MaterialTheme.colorScheme.background,
//                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
//                    focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
//                    unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary
//                )
//            )
//
//            Spacer(modifier = Modifier.height(15.dp))
//            Row(modifier = Modifier.fillMaxWidth().padding(5.dp)
//            ) {
//                ButtonDialog("Cancelar")
//                Spacer(modifier = Modifier.weight(1f))
//                ButtonDialog("Guardar",)
//            }
//
//        }
        }
    }

}

@Preview
@Composable
fun AlerDialogPreview() {
    CustomAlerDialog(
        show = true,
        onDismiss = {},
        onConfirm = {}
    )

}

@Composable
fun CustomAlerDialog(
    show:Boolean,
    onDismiss: () ->Unit,
    onConfirm: () ->Unit

){
    Box(modifier = Modifier.fillMaxSize().size(40.dp)){
        if (show) {
            AlertDialog(
                onDismissRequest = { onDismiss() },
                confirmButton = {},
                title = { Text(text = "toco") },

            )
        }
    }

}







@Composable
fun ButtonDialog(
   text: String,
   onClick: () -> Unit,
   enabled: Boolean = true
){
    Button(
        onClick = { onClick()},
        enabled = enabled,
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.tertiary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.primary,
        )

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = if (enabled) dark00 else dark00.copy(alpha = 0.5f)
        )
    }
}


