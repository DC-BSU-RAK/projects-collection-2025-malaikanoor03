package com.example.smartshoppingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var etNewEmail: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var btnSaveSettings: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        etNewEmail = findViewById(R.id.etNewEmail)
        etNewPassword = findViewById(R.id.etNewPassword)
        btnSaveSettings = findViewById(R.id.btnSaveSettings)
        btnLogout = findViewById(R.id.btnLogout)

       
        val sharedPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        etNewEmail.setText(sharedPrefs.getString("email", ""))

        btnSaveSettings.setOnClickListener {
            val newEmail = etNewEmail.text.toString().trim()
            val newPassword = etNewPassword.text.toString()

            if (newEmail.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Please fill both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save new credentials
            sharedPrefs.edit()
                .putString("email", newEmail)
                .putString("password", newPassword)
                .apply()

            Toast.makeText(this, "Settings updated!", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
     
            sharedPrefs.edit().clear().apply()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
