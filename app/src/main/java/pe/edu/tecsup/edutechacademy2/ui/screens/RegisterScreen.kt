package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.tecsup.edutechacademy2.navigation.Screen

@Composable
fun RegisterScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    // Colores de marca refinados (Idénticos al Login)
    val mainTeal = Color(0xFF2E9EAE)
    val lightTealBackground = Color(0xFFD1F2F5)
    val darkGrayLabel = Color(0xFF333333)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 1. Fondo decorativo (Mismo que el Login)
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = lightTealBackground.copy(alpha = 0.6f),
                radius = 110.dp.toPx(),
                center = Offset(size.width * 0.2f, size.height * 0.1f)
            )
            drawCircle(
                color = lightTealBackground.copy(alpha = 0.5f),
                radius = 80.dp.toPx(),
                center = Offset(size.width * 0.8f, size.height * 0.22f)
            )

            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width, size.height * 0.25f)
                quadraticTo(
                    size.width * 0.5f, size.height * 0.45f,
                    0f, size.height * 0.32f
                )
                close()
            }
            drawPath(path = path, color = mainTeal)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            
            Text(
                text = "CREAR CUENTA",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.2.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 2. Tarjeta Blanca de Registro Refinada
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(24.dp), // Esquinas pronunciadas 24dp
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.dp, mainTeal), // Borde teal marcado de 2dp
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Campo: Nombre completo
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Nombre completo", color = darkGrayLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = mainTeal) },
                            placeholder = { Text("Tu nombre", color = Color.LightGray) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = mainTeal,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = mainTeal,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Correo electrónico
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Correo electrónico", color = darkGrayLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = mainTeal) },
                            placeholder = { Text("ejemplo@correo.com", color = Color.LightGray) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = mainTeal,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = mainTeal,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Contraseña
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Contraseña", color = darkGrayLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = mainTeal) },
                            placeholder = { Text("••••••••", color = Color.LightGray) },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = mainTeal,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = mainTeal,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo: Confirmar Contraseña
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Confirmar contraseña", color = darkGrayLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = mainTeal) },
                            placeholder = { Text("••••••••", color = Color.LightGray) },
                            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                    Icon(
                                        imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = mainTeal,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = mainTeal,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }

                    if (error.isNotEmpty()) {
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 3. Botón CREAR CUENTA
                    Button(
                        onClick = {
                            if (nombre.isBlank() || email.isBlank() || password.isBlank()) {
                                error = "Completa todos los campos"
                            } else if (password != confirmPassword) {
                                error = "Las contraseñas no coinciden"
                            } else {
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.Register.route) { inclusive = true }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = mainTeal)
                    ) {
                        Text("CREAR CUENTA", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Footer
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("¿Ya tienes cuenta? ", color = Color.Gray, fontSize = 13.sp)
                        TextButton(onClick = { navController.popBackStack() }) {
                            Text("Inicia sesión", color = mainTeal, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
