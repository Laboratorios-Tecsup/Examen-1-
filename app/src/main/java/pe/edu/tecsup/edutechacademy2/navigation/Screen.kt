package pe.edu.tecsup.edutechacademy2.navigation

sealed class Screen(val route: String){

    object Login : Screen("login")
    object Home : Screen("home")
    object Courses : Screen("courses")


    object CourseDetail : Screen("course_detail/{courseId}"){
        fun createRoute(courseId: Int) = "course_detail/$courseId"
    }
    object Profile : Screen("profile")
}