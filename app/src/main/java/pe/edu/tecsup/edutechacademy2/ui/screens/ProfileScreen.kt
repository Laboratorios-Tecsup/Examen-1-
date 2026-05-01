package pe.edu.tecsup.edutechacademy2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
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
            Text("Andy Campos", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Estudiante de Diseño y Desarrollo de Software", style = MaterialTheme.typography.bodyLarge)
            Text("Correo: andy@tecsup.edu.pe", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(32.dp))
            
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Información Académica", style = MaterialTheme.typography.titleMedium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Institución: Tecsup")
                    Text("Ciclo: 4to Ciclo")
                }
            }
        }
    }
}