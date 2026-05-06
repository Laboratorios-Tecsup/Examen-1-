package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import pe.edu.tecsup.edutechacademy2.data.repository.CourseRepository
import pe.edu.tecsup.edutechacademy2.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val courses = CourseRepository.courses
    val mainTeal = Color(0xFF2E9EAE)
    val lightGrayBg = Color(0xFFF7F9FA)
    val popularLazyListState = rememberLazyListState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") },
                    selected = true,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = mainTeal,
                        selectedTextColor = mainTeal,
                        indicatorColor = mainTeal.copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Cursos") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Courses.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Book, contentDescription = null) },
                    label = { Text("Mis Cursos") },
                    selected = false,
                    onClick = { navController.navigate(Screen.MyCourses.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Profile.route) }
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(lightGrayBg)
            .padding(padding)) {

            // 1. Fondo de Onda Celeste Teal (Igual al Login)
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)) {
                val path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height * 0.7f)
                    quadraticTo(
                        size.width * 0.5f, size.height * 1.1f,
                        0f, size.height * 0.8f
                    )
                    close()
                }
                drawPath(path = path, color = mainTeal)
            }

            Column(modifier = Modifier.fillMaxSize()) {
                // Header: Título y Avatar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(40.dp)) // Espaciador para centrar título
                    Text(
                        "EduTech Academy",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Surface(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navController.navigate(Screen.Profile.route) },
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.3f)
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Perfil",
                            tint = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    // Bienvenida
                    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
                        Text(
                            "Hola, Andy 👋",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                        Text(
                            "Impulsa tu carrera hoy",
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Fila de 3 Cards de acceso rápido
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        QuickAccessCard("Cursos", Icons.Default.GridView, mainTeal, Modifier.weight(1f)) {
                            navController.navigate(Screen.Courses.route)
                        }
                        QuickAccessCard("Mis Cursos", Icons.Default.LibraryBooks, mainTeal, Modifier.weight(1f)) {
                            navController.navigate(Screen.MyCourses.route)
                        }
                        QuickAccessCard("Perfil", Icons.Default.Person, mainTeal, Modifier.weight(1f)) {
                            navController.navigate(Screen.Profile.route)
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    // Sección Cursos Populares
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Cursos Populares",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            "Ver todos",
                            color = mainTeal,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { navController.navigate(Screen.Courses.route) }
                        )
                    }

                    // Carrusel de Cursos con Imágenes Reales y más anchos
                    LazyRow(
                        state = popularLazyListState,
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(courses) { course ->
                            PopularCourseCard(course) {
                                navController.navigate(Screen.CourseDetail.createRoute(course.id))
                            }
                        }
                    }

                    // Indicador de puntos (Dot Indicator)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(courses.size) { index ->
                            val isSelected = remember {
                                derivedStateOf { popularLazyListState.firstVisibleItemIndex == index }
                            }
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(if (isSelected.value) 10.dp else 8.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected.value) mainTeal else Color.LightGray)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun QuickAccessCard(title: String, icon: ImageVector, color: Color, modifier: Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier
            .aspectRatio(1f)
            .shadow(8.dp, RoundedCornerShape(20.dp))
            .clickable { onClick() },
        color = Color.White,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

@Composable
fun PopularCourseCard(course: pe.edu.tecsup.edutechacademy2.data.model.Course, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(280.dp) // Card más ancha solicitado
            .shadow(6.dp, RoundedCornerShape(24.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Imagen Real del Curso con ContentScale.Crop y sin íconos encima
            Image(
                painter = painterResource(id = course.imageRes),
                contentDescription = course.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = course.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1
                )
                Text(
                    text = course.instructor,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Timer,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = " " + course.duration,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
