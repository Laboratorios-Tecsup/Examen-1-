package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Colores de marca refinados
    val mainTeal = Color(0xFF2E9EAE)
    val lightTealBackground = Color(0xFFD1F2F5)
    val darkGrayLabel = Color(0xFF333333)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 1. Fondo decorativo (Ondas y círculos)
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
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            
            Text(
                text = "EDUTECH ACADEMY",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.2.sp
            )

            Spacer(modifier = Modifier.weight(0.4f))

            // 2. Tarjeta Blanca de Login Refinada con borde de 2dp y radio de 24dp
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(24.dp), 
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.dp, mainTeal), 
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Campo: Correo electrónico
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Correo electrónico", 
                            color = darkGrayLabel, 
                            fontSize = 14.sp, 
                            fontWeight = FontWeight.ExtraBold // Más visible
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                            leadingIcon = {
                                Icon(Icons.Default.Email, contentDescription = null, tint = mainTeal)
                            },
                            placeholder = { Text("ejemplo@correo.com", color = Color.LightGray) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = mainTeal, // Borde inferior más visible al estar activo
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = mainTeal,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Campo: Contraseña
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Contraseña", 
                            color = darkGrayLabel, 
                            fontSize = 14.sp, 
                            fontWeight = FontWeight.ExtraBold // Más visible
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                            leadingIcon = {
                                Icon(Icons.Default.Lock, contentDescription = null, tint = mainTeal)
                            },
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
                                focusedIndicatorColor = mainTeal, // Borde inferior más visible al estar activo
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = mainTeal,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }

                    TextButton(
                        onClick = { /* Acción recuperación */ },
                        modifier = Modifier.align(Alignment.End).padding(top = 4.dp)
                    ) {
                        Text("¿Olvidaste tu contraseña?", color = mainTeal, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 3. Botón INICIAR SESIÓN Sólido
                    Button(
                        onClick = {
                            if (email.isNotBlank() && password.length >= 6) {
                                navController.navigate(Screen.Home.route)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = mainTeal)
                    ) {
                        Text("INICIAR SESIÓN", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Footer para registro
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("¿No tienes cuenta? ", color = Color.Gray, fontSize = 12.sp)
                        TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
                            Text("Crea una cuenta", color = mainTeal, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
