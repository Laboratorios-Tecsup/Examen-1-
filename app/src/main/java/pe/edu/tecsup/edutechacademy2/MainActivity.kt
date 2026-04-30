package pe.edu.tecsup.edutechacademy2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pe.edu.tecsup.edutechacademy2.navigation.AppNavigation
import pe.edu.tecsup.edutechacademy2.ui.theme.Edutechacademy2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Edutechacademy2Theme {
                AppNavigation()
            }
        }
    }
}