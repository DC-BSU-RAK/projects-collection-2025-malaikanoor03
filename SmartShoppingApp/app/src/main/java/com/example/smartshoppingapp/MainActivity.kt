package com.example.smartshoppingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val categories = listOf("Books", "Clothes", "Electronics", "Groceries", "Toys")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinnerCategory)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnViewDetails = findViewById<Button>(R.id.btnViewDetails)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val tvSaved = findViewById<TextView>(R.id.tvSavedPreference)

        // Adapter for Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Load saved preference
        val sharedPrefs = getSharedPreferences("ShoppingPrefs", Context.MODE_PRIVATE)
        val savedCategory = sharedPrefs.getString("preferred_category", null)

        if (savedCategory != null) {
            tvSaved.text = "Your saved preference: $savedCategory"
            spinner.setSelection(categories.indexOf(savedCategory))
        }

        // Save button listener
        btnSave.setOnClickListener {
            val selected = spinner.selectedItem.toString()
            sharedPrefs.edit().putString("preferred_category", selected).apply()
            tvSaved.text = "Your saved preference: $selected"
            Toast.makeText(this, "Preference Saved!", Toast.LENGTH_SHORT).show()
        }

        // View Details button listener
        btnViewDetails.setOnClickListener {
            val selected = spinner.selectedItem.toString()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("category_name", selected)
            startActivity(intent)
        }

        // Login button listener
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Register button listener
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Settings button listener
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

    }
}
