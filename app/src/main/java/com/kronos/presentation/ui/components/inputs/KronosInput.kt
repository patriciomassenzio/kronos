package com.kronos.presentation.ui.components.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kronos.presentation.ui.theme.AppColors
import com.kronos.presentation.ui.theme.InputColors
import com.kronos.presentation.ui.theme.dark90

@Preview
@Composable
fun preview(){
    Column {
        KronosInput("Titulo", "Valor", KeyboardType.Email, true, {}, Modifier.fillMaxWidth())
        KronosInput("Titulo", "Valor", KeyboardType.Email, false, {}, Modifier.fillMaxWidth())
        KronosInput("Titulo", "Valor", KeyboardType.Password, true, {}, Modifier.fillMaxWidth())
        KronosInput("Titulo", "Valor", KeyboardType.Password, false, {}, Modifier.fillMaxWidth())
    }
}
@Composable
fun KronosInput(
    title: String,
    value: String,
    keyboardType: KeyboardType,
    isValid: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    alignLeft: Boolean = true,
) {
    val height = if(title.isBlank()) 30.dp else 48.dp
    val alignment = if(alignLeft) TextAlign.Start else TextAlign.End
    val isValidText = if(isValid) "" else "Ingresar un ${title.lowercase()} válido."
    var passwordVisibility by remember { mutableStateOf(false) }
    val isPassword = keyboardType == KeyboardType.NumberPassword || keyboardType == KeyboardType.Password

    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (!isValid) MaterialTheme.colorScheme.error else InputColors.border

    Column(modifier = modifier
        .background(dark90)
        .padding(top=7.dp)) {
        Box(){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Campo de texto básico
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = InputColors.text,
                            textAlign = alignment
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester)
                            .onFocusChanged { isFocused = it.isFocused },
                        visualTransformation = if (isPassword && !passwordVisibility) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                        cursorBrush = SolidColor(InputColors.text)
                    )

                    // Icono de visibilidad para contraseñas
                    if (isPassword) {
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = "Toggle password visibility",
                                tint = InputColors.title,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            if(title.isNotBlank()){
                // Label flotante
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = InputColors.title,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .offset(y = (-6).dp)
                        .background(dark90)
                        .padding(horizontal = 4.dp)
                )
            }
        }

        // Mensaje de error
        AnimatedVisibility(
            visible = !isValid,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = isValidText,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun KronosInput(
//    title: String,
//    value: String,
//    keyboardType: KeyboardType,
//    isValid: Boolean,
//    onValueChange: (String) -> Unit,
//    modifier: Modifier = Modifier
//    ) {
//    val isValidText = if(isValid) "" else "Ingresar un ${title.lowercase()} válido."
//    var passwordVisibility by remember { mutableStateOf(false) }
//    val isPassword = keyboardType == KeyboardType.NumberPassword || keyboardType ==KeyboardType.Password
//
//    val interactionSource = remember { MutableInteractionSource() }
//
//    Column(modifier = modifier) {
//        BasicTextField(
//            value = value,
//            onValueChange = onValueChange,
//            modifier = modifier,
//            //visualTransformation = visualTransformation,
//            interactionSource = interactionSource,
//            //enabled = enabled,
//            singleLine = true,
//        ) { innerTextField ->
//            TextFieldDefaults.DecorationBox(
//                value = value,
//                visualTransformation = VisualTransformation.None,
//                innerTextField = innerTextField,
//                singleLine = true,
//                enabled = true,
//                interactionSource = interactionSource,
//                contentPadding = PaddingValues(0.dp), // this is how you can remove the padding
//            )
//        }
//
//        OutlinedTextField(
//            label = {
//                Text(
//                    text = title,
//                    color = InputColors.title,
//                    fontSize = 12.sp
//                )
//            },
//            textStyle = TextStyle(color = InputColors.text, fontSize = 16.sp),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = InputColors.border,
//                unfocusedBorderColor = InputColors.border,
//                errorBorderColor = MaterialTheme.colorScheme.error
//            ),
//            value = value,
//            onValueChange = { onValueChange(it) },
//            shape = RoundedCornerShape(4.dp),
//            isError = !isValid,
//            modifier = Modifier.fillMaxWidth(),//.height(40.dp),
//            maxLines = 1,
//            singleLine = true,
//            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
//            trailingIcon = {
//                if(isPassword){
//                    val imagen = if (passwordVisibility) {
//                        Icons.Filled.VisibilityOff
//                    } else {
//                        Icons.Filled.Visibility
//                    }
//                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
//                        Icon(imageVector = imagen, contentDescription = "show password")
//                    }
//                }
//            },
//            visualTransformation = (
//                if(isPassword){
//                    if (passwordVisibility) {
//                        VisualTransformation.None
//                    } else {
//                        PasswordVisualTransformation()
//                    }
//                }
//                else{ VisualTransformation.None }
//            )
//        )
//        Text(
//            text = isValidText,
//            color = MaterialTheme.colorScheme.error,
//            style = MaterialTheme.typography.bodySmall
//        )
//    }
//}