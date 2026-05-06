package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.tecsup.edutechacademy2.data.repository.CourseRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCoursesScreen(navController: NavController) {
    val mainTeal = Color(0xFF2E9EAE)
    val lightGrayBg = Color(0xFFF7F9FA)

    // Datos vinculados al repositorio para obtener las imágenes reales
    val courses = CourseRepository.courses
    val enrolledCourses = listOf(
        Pair(courses[0], 0.75f),
        Pair(courses[1], 0.45f),
        Pair(courses[2], 0.30f)
    )

    val avgProgress = (enrolledCourses.map { it.second }.average() * 100).toInt()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGrayBg)
    ) {
        // 1. Fondo de Onda Celeste Teal
        Canvas(modifier = Modifier.fillMaxWidth().height(180.dp)) {
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

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("Mis Cursos", color = Color.White, fontWeight = FontWeight.Bold)
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.White)
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
            ) {
                // Header de Estadísticas Mejorado
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    StatsCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.School,
                        label = "Total Cursos",
                        value = "${enrolledCourses.size}",
                        accentColor = mainTeal
                    )
                    StatsCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Timeline,
                        label = "Progreso Prom.",
                        value = "$avgProgress%",
                        accentColor = mainTeal
                    )
                }

                Text(
                    text = "Continuar aprendiendo",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                )

                LazyColumn(
                    contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(enrolledCourses) { enrolled ->
                        CourseProgressCard(
                            course = enrolled.first,
                            progress = enrolled.second,
                            accentColor = mainTeal
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatsCard(
    modifier: Modifier,
    icon: ImageVector,
    label: String,
    value: String,
    accentColor: Color
) {
    Surface(
        modifier = modifier
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 24.sp,
                color = accentColor,
                fontWeight = FontWeight.Black
            )
            Text(
                text = label,
                fontSize = 11.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CourseProgressCard(course: pe.edu.tecsup.edutechacademy2.data.model.Course, progress: Float, accentColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
            // Borde izquierdo de 4dp celeste teal
            Box(modifier = Modifier.fillMaxHeight().width(4.dp).background(accentColor))

            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Imagen real del curso a la izquierda
                Image(
                    painter = painterResource(id = course.imageRes),
                    contentDescription = course.title,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1
                    )
                    Text(
                        text = "Por ${course.instructor}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Barra de progreso celeste teal redondeada
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .weight(1f)
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = accentColor,
                            trackColor = accentColor.copy(alpha = 0.1f),
                            strokeCap = StrokeCap.Round
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "${(progress * 100).toInt()}%",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentColor
                        )
                    }
                }
            }
        }
    }
}