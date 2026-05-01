package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.tecsup.edutechacademy2.navigation.Screen

data class Course(
    val id: Int,
    val title: String,
    val instructor: String,
    val level: String,
    val category: String,
    val duration: String,
    val description: String
)

val courses = listOf(
    Course(1, "Kotlin desde cero", "Juan Pérez", "Básico", "Programación ", "8 horas", "Aprende Kotlin desde lo más básico."),
    Course(2, "Jetpack Compose", "María López", "Intermedio", "Programación ", "10 horas", "Construye interfaces modernas en Android."),
    Course(3, "Diseño UI/UX", "Carlos Ramos", "Básico", "Diseño ", "6 horas", "Aprende principios de diseño para apps."),
    Course(4, "Figma para prototipos", "Ana Torres", "Intermedio", "Diseño ", "7 horas", "Crea prototipos modernos en Figma."),
    Course(5, "Marketing Digital", "Luis García", "Básico", "Negocios ", "5 horas", "Aprende estrategias digitales para negocios."),
    Course(6, "Emprendimiento Tech", "Rosa Díaz", "Avanzado", "Negocios ", "9 horas", "Desarrolla ideas de negocio tecnológico.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("Todos") }

    val filteredCourses = if (selectedCategory == "Todos") {
        courses
    } else {
        courses.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cursos") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("←")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = selectedCategory == "Todos",
                    onClick = { selectedCategory = "Todos" },
                    label = { Text("Todos") }
                )
                FilterChip(
                    selected = selectedCategory == "Programación ",
                    onClick = { selectedCategory = "Programación " },
                    label = { Text("Programación") }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = selectedCategory == "Diseño ",
                    onClick = { selectedCategory = "Diseño " },
                    label = { Text("Diseño") }
                )
                FilterChip(
                    selected = selectedCategory == "Negocios ",
                    onClick = { selectedCategory = "Negocios " },
                    label = { Text("Negocios") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filteredCourses) { course ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(Screen.CourseDetail.createRoute(course.id))
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("", style = MaterialTheme.typography.headlineMedium)
                            Text(course.title, style = MaterialTheme.typography.titleLarge)
                            Text("Instructor: ${course.instructor}")
                            Text("Nivel: ${course.level}")
                            Text("Categoría: ${course.category}")
                        }
                    }
                }
            }
        }
    }
}