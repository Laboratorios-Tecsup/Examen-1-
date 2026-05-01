package pe.edu.tecsup.edutechacademy2.navigation

sealed class Screen(val route: String){

    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Courses : Screen("courses")
    object MyCourses : Screen("my_courses")

    object CourseDetail : Screen("course_detail/{courseId}"){
        fun createRoute(courseId: Int) = "course_detail/$courseId"
    }
    object Profile : Screen("profile")
}