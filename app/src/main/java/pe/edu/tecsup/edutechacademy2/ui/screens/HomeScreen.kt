package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.tecsup.edutechacademy2.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Inicio") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Hola, Andy ",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Bienvenido a EduTech Academy",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Screen.Courses.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(" Ver catálogo de cursos")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate(Screen.Profile.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(" Mi perfil")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { navController.navigate(Screen.MyCourses.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(" Mis cursos inscritos")
            }
        }
    }
}