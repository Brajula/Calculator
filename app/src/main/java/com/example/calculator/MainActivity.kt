package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val main = findViewById<android.view.View>(R.id.main)
        val paddingLeft = main.paddingLeft
        val paddingTop = main.paddingTop
        val paddingRight = main.paddingRight
        val paddingBottom = main.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(main) { view, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            view.setPadding(
                paddingLeft + systemBars.left,
                paddingTop + systemBars.top,
                paddingRight + systemBars.right,
                paddingBottom + systemBars.bottom
            )
            insets
        }

        val firstNameInput = findViewById<EditText>(R.id.etFirstName)
        val lastNameInput = findViewById<EditText>(R.id.etLastName)
        val pointsInput = findViewById<EditText>(R.id.etPoints)
        val calculateButton = findViewById<Button>(R.id.btnCalculate)
        val resultText = findViewById<TextView>(R.id.tvResult)

        val calculator = GradeCalculator()

        calculateButton.setOnClickListener {
            resultText.text = ""
            firstNameInput.error = null
            lastNameInput.error = null
            pointsInput.error = null

            val firstName = firstNameInput.text.toString().trim()
            val lastName = lastNameInput.text.toString().trim()
            val points = pointsInput.text.toString().trim().toIntOrNull()

            if (firstName.isBlank()) {
                firstNameInput.error = "Wpisz imię"
                return@setOnClickListener
            }

            if (lastName.isBlank()) {
                lastNameInput.error = "Wpisz nazwisko"
                return@setOnClickListener
            }

            if (points == null || points < 0) {
                pointsInput.error = "Wpisz nieujemną liczbę całkowitą"
                return@setOnClickListener
            }

            val student = Student(firstName, lastName, points)
            val grade = calculator.calculateGrade(student.points)

            resultText.text =
                "Student: ${student.firstName} ${student.lastName}\n" +
                        "Punkty: ${student.points}\n" +
                        "Ocena: $grade"
        }
    }
}