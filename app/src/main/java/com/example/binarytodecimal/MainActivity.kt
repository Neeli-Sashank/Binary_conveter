package com.example.binarytodecimal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("conversion_history", Context.MODE_PRIVATE)

        // Retrieve references to the relevant UI elements
        val input: EditText = findViewById(R.id.edittext)
        val output: EditText = findViewById(R.id.output)
        val submit: Button = findViewById(R.id.submit)
        val reset: Button = findViewById(R.id.reset)
        val historyButton: Button = findViewById(R.id.historyButton)

        // Set click listener for the Submit button
        submit.setOnClickListener {
            val binaryString = input.text.toString()
            val decimal = binaryString.toIntOrNull(2) ?: 0
            output.setText(decimal.toString())

            // Save conversion to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString(binaryString, decimal.toString())
            editor.apply()

            // Log the SharedPreferences contents
            val allEntries: Map<String, *> = sharedPreferences.all
            for (entry in allEntries) {
                Log.d("SharedPreferences", "${entry.key}: ${entry.value}")
            }
        }

        // Set click listener for the Reset button
        reset.setOnClickListener {
            input.setText("")
            output.setText("")
        }

        // Set click listener for the History button
        historyButton.setOnClickListener {
            // Start the com.example.binarytodecimal.HistoryActivity
            val historyIntent = Intent(this, HistoryActivity::class.java)
            startActivity(historyIntent)
        }
    }
}
