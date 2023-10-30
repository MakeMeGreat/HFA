package ru.easycode.hfa_first

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findBeer = findViewById<Button>(R.id.find_beer)
        val brands = findViewById<TextView>(R.id.brands)
        val beerColor = findViewById<Spinner>(R.id.beer_color)
        findBeer.setOnClickListener {
            val color = beerColor.selectedItem.toString()
            val beersList = getBeers(color)
            val beers = beersList.reduce { str, item -> str + "\n" + item }
            brands.text = beers
        }
    }

    fun getBeers(beerColor: String): List<String> {
        return when (beerColor) {
            "Light" -> listOf("Jail Pale Ale", "Lager Lite")
            "Amber" -> listOf("Jack Amber", "Red Moose")
            "Brown" -> listOf("Brown Bear Beer", "Bock Brownie")
            else -> listOf("Gout Stout", "Dark Daniel")
        }
    }


}