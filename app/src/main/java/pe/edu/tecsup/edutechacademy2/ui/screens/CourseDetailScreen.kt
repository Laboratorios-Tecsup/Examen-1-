package pe.edu.tecsup.edutechacademy2.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(navController: NavController, courseId: Int) {
    val course = courses.find { it.id == courseId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del curso") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("←")
                    }
                }
            )
        }
    ) { padding ->
        if (course != null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Text("", style = MaterialTheme.typography.displayMedium)
                Text(course.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Instructor: ${course.instructor}")
                Text("Nivel: ${course.level}")
                Text("Duración: ${course.duration}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(course.description)

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Inscribirse")
                }
            }
        }
    }
}