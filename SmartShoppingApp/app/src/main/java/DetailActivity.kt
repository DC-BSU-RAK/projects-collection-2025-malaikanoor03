package com.example.smartshoppingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private val categoryDetails = mapOf(
        "Books" to Pair("All kinds of books for every reader.", R.drawable.ic_books),
        "Clothes" to Pair("Fashionable and comfortable clothing.", R.drawable.ic_clothes),
        "Electronics" to Pair("Latest gadgets and devices.", R.drawable.ic_electronics),
        "Groceries" to Pair("Fresh and daily groceries.", R.drawable.ic_groceries),
        "Toys" to Pair("Fun toys for all ages.", R.drawable.ic_toys)
    )

    // Sample products by category (name and price)
    private val productsByCategory = mapOf(
        "Books" to listOf(
            Product("Book One", 12.99, "A thrilling adventure book."),
            Product("Book Two", 15.50, "Learn Kotlin programming."),
            Product("Book Three", 9.99, "Mystery novel for all ages.")
        ),
        "Clothes" to listOf(
            Product("T-Shirt", 19.99, "Comfortable cotton T-shirt."),
            Product("Jeans", 39.99, "Stylish blue jeans."),
            Product("Jacket", 79.99, "Warm winter jacket.")
        ),
        "Electronics" to listOf(
            Product("Smartphone", 299.99, "Latest model with great features."),
            Product("Headphones", 89.99, "Noise-cancelling over-ear headphones."),
            Product("Smartwatch", 149.99, "Keep track of your fitness.")
        ),
        "Groceries" to listOf(
            Product("Milk", 2.50, "Fresh whole milk."),
            Product("Bread", 1.99, "Baked daily, fresh bread."),
            Product("Eggs", 3.00, "Free-range chicken eggs.")
        ),
        "Toys" to listOf(
            Product("Action Figure", 14.99, "Superhero collectible figure."),
            Product("Puzzle", 9.49, "500-piece jigsaw puzzle."),
            Product("Board Game", 24.99, "Fun family board game.")
        )
    )

    private lateinit var cbFavorite: CheckBox
    private lateinit var categoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        categoryName = intent.getStringExtra("category_name") ?: "Unknown"

        val tvTitle = findViewById<TextView>(R.id.tvCategoryTitle)
        val tvDescription = findViewById<TextView>(R.id.tvCategoryDescription)
        val imgIcon = findViewById<ImageView>(R.id.imgCategoryIcon)
        cbFavorite = findViewById(R.id.cbFavorite)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val lvProducts = findViewById<ListView>(R.id.lvProducts)

        // Set category title and description
        tvTitle.text = categoryName
        val details = categoryDetails[categoryName]
        if (details != null) {
            tvDescription.text = details.first
            imgIcon.setImageResource(details.second)
        } else {
            tvDescription.text = "No details available."
            imgIcon.setImageResource(android.R.color.transparent)
        }

        // Load favorite state from SharedPreferences
        val sharedPrefs = getSharedPreferences("ShoppingPrefs", Context.MODE_PRIVATE)
        val isFavorite = sharedPrefs.getBoolean("favorite_$categoryName", false)
        cbFavorite.isChecked = isFavorite

        // Checkbox change listener
        cbFavorite.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean("favorite_$categoryName", isChecked).apply()
            Toast.makeText(
                this,
                if (isChecked) "Marked as favorite!" else "Unmarked favorite!",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Back button closes activity
        btnBack.setOnClickListener {
            finish()
        }

        // Load products for this category into ListView
        val productList = productsByCategory[categoryName] ?: emptyList()
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            productList.map { "${it.name} - $${String.format("%.2f", it.price)}" }
        )
        lvProducts.adapter = adapter

        // Click listener on products to open ProductDetailActivity
        lvProducts.setOnItemClickListener { _, _, position, _ ->
            val product = productList[position]
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("product_name", product.name)
                putExtra("product_price", product.price)
                putExtra("product_description", product.description)
            }
            startActivity(intent)
        }
    }
}

// Updated Product data class with description
data class Product(val name: String, val price: Double, val description: String)