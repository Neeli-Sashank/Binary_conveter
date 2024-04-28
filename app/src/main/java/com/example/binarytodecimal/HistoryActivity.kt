package com.example.binarytodecimal

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("conversion_history", Context.MODE_PRIVATE)

        // Retrieve the conversion history from SharedPreferences
        val conversionHistory = mutableListOf<String>()
        val allEntries: Map<String, *> = sharedPreferences.all
        for (entry in allEntries) {
            conversionHistory.add("Binary: ${entry.key}, Decimal: ${entry.value}")
        }

        // Display the conversion history in a ListView
        val listView: ListView = findViewById(R.id.historyListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, conversionHistory)
        listView.adapter = adapter
    }
}
