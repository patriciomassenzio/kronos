package com.kronos.presentation.ui.components.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kronos.presentation.ui.theme.InputColors
import com.kronos.presentation.ui.theme.dark90

@Preview
@Composable
fun DropdownPreview() {
    val options = listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4")
    Column {
        KronosDropdown(
            title = "Selecciona una opción",
            options = options,
            selectedOption = "Opción 1",
            isValid = true,
            onOptionSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
        KronosDropdown(
            title = "Selecciona una opción",
            options = options,
            selectedOption = "Opción 2",
            isValid = false,
            onOptionSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
        KronosDropdown(
            title = "",
            options = options,
            selectedOption = "Opción 3",
            isValid = true,
            onOptionSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun KronosDropdown(
    title: String,
    options: List<String>,
    selectedOption: String,
    isValid: Boolean,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    alignLeft: Boolean = true,
) {
    val height = if(title.isBlank()) 30.dp else 48.dp
    val alignment = if(alignLeft) TextAlign.Start else TextAlign.End
    val isValidText = if(isValid) "" else "Selecciona un ${title.lowercase()} válido."

    var expanded by remember { mutableStateOf(false) }
    val borderColor = if (!isValid) MaterialTheme.colorScheme.error else InputColors.border

    Column(modifier = modifier
        .background(dark90)
        .padding(top = 7.dp)) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable { expanded = true }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Texto seleccionado
                    Text(
                        text = selectedOption,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = InputColors.text,
                            textAlign = alignment
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    // Ícono de flecha
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = "Expandir/Contraer dropdown",
                        tint = InputColors.title,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Menú desplegable
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(dark90)
                        //.fillMaxWidth(0.9f)
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = option,
                                    color = InputColors.text,
                                    fontSize = 16.sp
                                )
                            },
                            onClick = {
                                onOptionSelected(option)
                                expanded = false
                            }
                        )
                    }
                }
            }

            if(title.isNotBlank()) {
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