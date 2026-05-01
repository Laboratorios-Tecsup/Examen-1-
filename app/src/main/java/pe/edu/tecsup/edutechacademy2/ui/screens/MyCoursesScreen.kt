package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCoursesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Cursos") },
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
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text("Cursos en los que estás inscrito", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            CourseProgressItem("Kotlin desde cero", 0.75f)
            CourseProgressItem("Jetpack Compose", 0.45f)
            CourseProgressItem("Diseño UI/UX", 0.30f)
        }
    }
}

@Composable
fun CourseProgressItem(title: String, progress: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            Text("${(progress * 100).toInt()}% completado")
        }
    }
}