package com.example.nancoffeeapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val ingredients = listOf(
        "Espresso",
        "Milk",
        "Sugar",
        "Ice",
        "Chocolate",
        "Foam",
        "Caramel",
        "Vanilla",
        "Whipped Cream",
        "Hazelnut"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner1 = findViewById<Spinner>(R.id.spinnerIngredient1)
        val spinner2 = findViewById<Spinner>(R.id.spinnerIngredient2)
        val btnMix = findViewById<Button>(R.id.btnMix)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ingredients)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = adapter
        spinner2.adapter = adapter

        btnMix.setOnClickListener {
            val ing1 = spinner1.selectedItem.toString()
            val ing2 = spinner2.selectedItem.toString()

            if (ing1 == ing2) {
                Toast.makeText(this, "Please select two different ingredients", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = getMixResult(ing1, ing2)
            tvResult.text = result
        }
    }

    private fun getMixResult(ing1: String, ing2: String): String {
        return when (setOf(ing1, ing2)) {
            setOf("Espresso", "Milk") -> "You made a Latte!"
            setOf("Espresso", "Chocolate") -> "You made a Mocha!"
            setOf("Milk", "Ice") -> "You made Iced Milk!"
            setOf("Espresso", "Foam") -> "That's a Macchiato!"
            setOf("Espresso", "Sugar") -> "Sweet Espresso!"
            setOf("Chocolate", "Milk") -> "Chocolate Milk!"
            setOf("Caramel", "Milk") -> "Caramel Latte!"
            setOf("Vanilla", "Espresso") -> "Vanilla Espresso!"
            setOf("Whipped Cream", "Chocolate") -> "Chocolate Delight!"
            setOf("Hazelnut", "Espresso") -> "Hazelnut Coffee!"
            setOf("Sugar", "Milk") -> "Sweet Milk!"
            else -> "You mixed $ing1 and $ing2 — a unique blend!"
        }
    }
}
