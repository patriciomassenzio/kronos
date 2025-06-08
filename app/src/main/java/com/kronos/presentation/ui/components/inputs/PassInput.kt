package com.kronos.presentation.ui.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PassInput(
    isError: Boolean,
    pass: String? = null,
    repeatPass: String? = null,
    textHolder: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    //var isPasswordValid by remember { mutableStateOf(true) }
    if (pass != null) {
        OutlinedTextField(
            value = pass,
            onValueChange = {
                onValueChange(it)
                //isPasswordValid = pass.length >= 8
            },
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            label = { Text(text = textHolder, color = MaterialTheme.colorScheme.onPrimary) },

            shape = RoundedCornerShape(4.dp),
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val imagen = if (passwordVisibility) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = imagen, contentDescription = "show password")
                }
            },
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None

            } else {
                PasswordVisualTransformation()
            }

        )
    }
    if (repeatPass != null) {
        OutlinedTextField(
            value = repeatPass,
            onValueChange = {
                //isPasswordValid = repeatPass.length >= 8
                onValueChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            label = { Text(textHolder, color = MaterialTheme.colorScheme.onPrimary) },
            shape = RoundedCornerShape(4.dp),
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val imagen = if (passwordVisibility) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = imagen, contentDescription = "show password")
                }
            },
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None

            } else {
                PasswordVisualTransformation()
            }

        )
    }
    if (isError) {
        Text(
            text = "La contrasena debe contener al menos 8 caracteresssssss",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Composable
fun RepeatPassword(
    isError: Boolean,
    pass: String? = null,
    textHolder: String,
    onValueChange: (String) -> Unit

) {

    var passwordVisibility by remember { mutableStateOf(false) }


    if (pass != null) {

        OutlinedTextField(
            value = pass,
            onValueChange = {
                onValueChange(it)
                //isPasswordValid = pass.length >= 8

            },
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            label = { Text(text = textHolder, color = MaterialTheme.colorScheme.onPrimary) },

            shape = RoundedCornerShape(4.dp),
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val imagen = if (passwordVisibility) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = imagen, contentDescription = "show password")
                }
            },
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None

            } else {
                PasswordVisualTransformation()
            }

        )
    }

    if (isError) {
        Text(
            text = "La contrasena debe contener al menos 8 caracteresssssss",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}