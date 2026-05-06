package pe.edu.tecsup.edutechacademy2.data.repository

import pe.edu.tecsup.edutechacademy2.data.model.Course
import pe.edu.tecsup.edutechacademy2.R

object CourseRepository {

    val courses = listOf(
        Course(1, "Kotlin desde cero", "Juan Pérez", "Básico", "Programación", "8 horas", "Aprende Kotlin desde lo más básico.", R.drawable.kotlin),
        Course(2, "Jetpack Compose", "María López", "Intermedio", "Programación", "10 horas", "Construye interfaces modernas.", R.drawable.compose),
        Course(3, "Diseño UI/UX", "Carlos Ramos", "Básico", "Diseño", "6 horas", "Aprende diseño de interfaces.", R.drawable.diseno),
        Course(4, "Figma", "Ana Torres", "Intermedio", "Diseño", "7 horas", "Prototipos en Figma.", R.drawable.photoshop),
        Course(5, "Marketing Digital", "Luis García", "Básico", "Negocios", "5 horas", "Estrategias digitales.", R.drawable.marketing),
        Course(6, "Emprendimiento Tech", "Rosa Díaz", "Avanzado", "Negocios", "9 horas", "Crea startups tech.", R.drawable.spring_boot)
    )
}
