package com.example.smartshoppingapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val tvProductName = findViewById<TextView>(R.id.tvProductName)
        val tvProductPrice = findViewById<TextView>(R.id.tvProductPrice)
        val tvProductDescription = findViewById<TextView>(R.id.tvProductDescription)
        val btnBack = findViewById<Button>(R.id.btnBackProduct)

        // Receive data from Intent extras
        val name = intent.getStringExtra("product_name") ?: "Unknown"
        val price = intent.getDoubleExtra("product_price", 0.0)
        val description = intent.getStringExtra("product_description") ?: "No description."

        // Set UI values
        tvProductName.text = name
        tvProductPrice.text = "$${String.format("%.2f", price)}"
        tvProductDescription.text = description

        btnBack.setOnClickListener {
            finish()
        }
    }
}
