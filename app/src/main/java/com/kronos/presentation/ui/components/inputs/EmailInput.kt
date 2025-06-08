package com.kronos.presentation.ui.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EmailInput(
    email: String,
    isError: Boolean,
    //errorText: String?,
    onValueChange: (String) -> Unit,

    ) {
    //var isEmailValid by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = email,
        onValueChange = {
            onValueChange(it)

            //isEmailValid = email.contains("@") || email.startsWith("+5411")
        },
        label = {
            Text(
                text = "Correo electronico o numero de telefono",
                color = MaterialTheme.colorScheme.onPrimary
            )
        },

        shape = RoundedCornerShape(4.dp),
        isError = isError,
        //!isEmailValid,
        modifier = Modifier.fillMaxWidth(),

        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary

        )
    )
    if (isError) {
        Text(
            text = "Ingresar un correo electronico valido.",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

