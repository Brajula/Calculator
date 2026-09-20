package com.example.calculator

class GradeCalculator {

    fun calculateGrade(points: Int): String {
        return when {
            points < 50 -> "2.0"
            points <= 60 -> "3.0"
            points <= 70 -> "3.5"
            points <= 80 -> "4.0"
            points <= 90 -> "4.5"
            else -> "5.0"
        }
    }
}