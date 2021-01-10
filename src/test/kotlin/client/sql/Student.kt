package client.sql

/**
 * @author steve
 */
enum class Gender {
    MALE,
    FEMALE
}

data class Student(
    val id: String,
    val name: String,
    val age: Int,
    val gender: Gender,
    val score: Float?
)