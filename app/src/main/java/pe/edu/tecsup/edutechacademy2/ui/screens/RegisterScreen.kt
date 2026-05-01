package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.tecsup.edutechacademy2.navigation.Screen

@Composable
fun RegisterScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Cuenta", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (nombre.isBlank() || correo.isBlank() || password.isBlank()) {
                    error = "Por favor, completa todos los campos"
                } else if (!correo.contains("@")) {
                    error = "Correo electrónico no válido"
                } else if (password.length < 6) {
                    error = "La contraseña debe tener al menos 6 caracteres"
                } else if (password != confirmPassword) {
                    error = "Las contraseñas no coinciden"
                } else {
                    // Simular registro exitoso y volver al login
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        TextButton(onClick = { navController.popBackStack() }) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }
    }
}