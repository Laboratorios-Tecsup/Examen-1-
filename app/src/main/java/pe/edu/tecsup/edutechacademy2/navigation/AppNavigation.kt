package pe.edu.tecsup.edutechacademy2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import pe.edu.tecsup.edutechacademy2.ui.screens.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Courses.route) {
            CoursesScreen(navController)
        }

        composable(
            route = Screen.CourseDetail.route
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")?.toInt() ?: 0
            CourseDetailScreen(navController, courseId)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(Screen.MyCourses.route) {
            MyCoursesScreen(navController)
        }
    }
}