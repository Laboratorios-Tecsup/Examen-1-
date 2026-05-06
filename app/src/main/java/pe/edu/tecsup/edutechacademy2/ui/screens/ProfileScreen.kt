package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.tecsup.edutechacademy2.R
import pe.edu.tecsup.edutechacademy2.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val mainTeal = Color(0xFF2E9EAE)
    val darkTealText = Color(0xFF004D40)
    val lightGrayBg = Color(0xFFF7F9FA)

    // Valores iniciales
    val initialNombre = "Andy Campos"
    val initialEmail = "andy@tecsup.edu.pe"
    val initialTelefono = "+51 987 654 321"

    var nombre by remember { mutableStateOf(initialNombre) }
    var email by remember { mutableStateOf(initialEmail) }
    var telefono by remember { mutableStateOf(initialTelefono) }

    val hayCambios = nombre != initialNombre || email != initialEmail || telefono != initialTelefono

    Box(modifier = Modifier.fillMaxSize().background(lightGrayBg)) {
        // 1. Fondo de onda celeste teal
        Canvas(modifier = Modifier.fillMaxWidth().height(290.dp)) {
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width, size.height * 0.75f)
                quadraticTo(
                    size.width * 0.5f, size.height * 1.05f,
                    0f, size.height * 0.85f
                )
                close()
            }
            drawPath(path = path, color = mainTeal)
        }

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Mi Perfil", color = Color.White, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = Color.White)
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Settings, "Ajustes", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                // 2. Avatar con borde blanco grueso y sombra pronunciada
                Surface(
                    modifier = Modifier
                        .size(100.dp)
                        .shadow(20.dp, CircleShape),
                    shape = CircleShape,
                    color = Color.White,
                    border = BorderStroke(4.dp, Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.fillMaxSize().clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nombre en blanco bold grande
                Text(
                    text = nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )

                // Badge "Estudiante Premium" blanco semitransparente
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.25f),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Estudiante Premium",
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelLarge,
                        color = darkTealText,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                // 3. Sección de Cards con fondo blanco puro y sombra visible
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProfileFieldCard("NOMBRE COMPLETO", nombre, mainTeal) { nombre = it }
                    ProfileFieldCard("CORREO ELECTRÓNICO", email, mainTeal) { email = it }
                    ProfileFieldCard("TELÉFONO", telefono, mainTeal) { telefono = it }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Botón Guardar Cambios
                    AnimatedVisibility(visible = hayCambios) {
                        Button(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = mainTeal)
                        ) {
                            Text("Guardar Cambios", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }

                    // Botón Cerrar Sesión con rojo más visible
                    Button(
                        onClick = {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFEBEE),
                            contentColor = Color(0xFFD32F2F)
                        ),
                        border = BorderStroke(1.dp, Color(0xFFEF9A9A)),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Logout, null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Cerrar Sesión", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun ProfileFieldCard(
    label: String,
    value: String,
    accentColor: Color,
    onValueChange: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf(value) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
            Box(modifier = Modifier.fillMaxHeight().width(4.dp).background(accentColor))

            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    // Label celeste teal bold pequeño
                    Text(
                        text = label, 
                        fontSize = 11.sp, 
                        fontWeight = FontWeight.Black, 
                        color = accentColor,
                        letterSpacing = 0.5.sp
                    )
                    
                    if (isEditing) {
                        TextField(
                            value = textState,
                            onValueChange = { textState = it },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = accentColor
                            ),
                            singleLine = true
                        )
                    } else {
                        // Valor negro bold 16sp
                        Text(
                            text = value, 
                            fontSize = 16.sp, 
                            fontWeight = FontWeight.Bold, 
                            color = Color.Black,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }

                IconButton(onClick = {
                    if (isEditing) onValueChange(textState)
                    isEditing = !isEditing
                }) {
                    Icon(
                        imageVector = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = accentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
