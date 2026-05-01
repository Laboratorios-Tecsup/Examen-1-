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
fun LoginScreen(navController: NavController) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("EduTech Academy", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { 
                correo = it 
                error = "" 
            },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth(),
            isError = error.contains("correo", ignoreCase = true)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { 
                password = it 
                error = "" 
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = error.contains("contraseña", ignoreCase = true)
        )

        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (correo.isBlank() || password.isBlank()) {
                    error = "Por favor, completa todos los campos"
                } else if (!correo.contains("@")) {
                    error = "El correo debe contener un @"
                } else if (password.length < 6) {
                    error = "La contraseña debe tener al menos 6 caracteres"
                } else {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
            Text("¿No tienes cuenta? Regístrate aquí")
        }
    }
}