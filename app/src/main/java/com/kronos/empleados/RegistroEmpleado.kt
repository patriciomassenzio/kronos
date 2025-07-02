import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import androidx.core.content.edit
import com.kronos.empleados.Empleado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroEmpleadoScreen(onRegistroExitoso: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("") }
    var restaurante by remember { mutableStateOf("") }
    var rolExpanded by remember { mutableStateOf(false) }
    var restauranteExpanded by remember { mutableStateOf(false) }

    val roles = listOf("Gerente", "Cocinero", "Mesero", "Cajero")
    val restaurantes = listOf("Restaurante A", "Restaurante B", "Restaurante C")
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(
            value = pin,
            onValueChange = {
                if (it.length <= 4 && it.all { char -> char.isDigit() }) pin = it
            },
            label = { Text("PIN (4 dígitos)") },
            visualTransformation = PasswordVisualTransformation()
        )
        ExposedDropdownMenuBox(
            expanded = rolExpanded,
            onExpandedChange = { rolExpanded = !rolExpanded }
        ) {
            OutlinedTextField(
                value = rol,
                onValueChange = {},
                readOnly = true,
                label = { Text("Rol en el restaurante") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = rolExpanded) },
                modifier = Modifier.menuAnchor()
            )
            DropdownMenu(
                expanded = rolExpanded,
                onDismissRequest = { rolExpanded = false }
            ) {
                roles.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            rol = option
                            rolExpanded = false
                        }
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            expanded = restauranteExpanded,
            onExpandedChange = { restauranteExpanded = !restauranteExpanded }
        ) {
            OutlinedTextField(
                value = restaurante,
                onValueChange = {},
                readOnly = true,
                label = { Text("Nombre del restaurante") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = restauranteExpanded) },
                modifier = Modifier.menuAnchor()
            )
            DropdownMenu(
                expanded = restauranteExpanded,
                onDismissRequest = { restauranteExpanded = false }
            ) {
                restaurantes.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            restaurante = option
                            restauranteExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (pin.length == 4) {
                val nuevoEmpleado = Empleado(nombre, email, pin, rol, restaurante)
                guardarEmpleado(context, nuevoEmpleado)
                onRegistroExitoso()
            }
        }) {
            Text("Registrarse")
        }
    }
}
fun guardarEmpleado(context: Context, empleado: Empleado) {
    val sharedPrefs = context.getSharedPreferences("empleados", Context.MODE_PRIVATE)
    sharedPrefs.edit {
        val json = Gson().toJson(empleado)
        putString(empleado.pin, json)
    }
}