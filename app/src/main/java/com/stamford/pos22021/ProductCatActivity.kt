package com.stamford.pos22021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


class ProductCatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order2)

        Toast.makeText(this, "Hello ! Pick a category", Toast.LENGTH_SHORT).show()
    }

    fun firstButtonClicked(view: View) {
        val intent = Intent (this, OrderActivity::class.java)
        intent.putExtra("CatID", MACARON_CAT_ID)
        startActivity(intent)
        Toast.makeText(this, "Macarons button Clicked", Toast.LENGTH_SHORT).show()

    }
    fun secondButtonClicked(view: View) {
        val intent = Intent (this, OrderActivity::class.java)
        intent.putExtra("CatID", DRINK_CAT_ID)
        startActivity(intent)
        Toast.makeText(this, "Drinks button Clicked", Toast.LENGTH_SHORT).show()
    }
    fun thirdButtonClicked(view: View) {
        val intent = Intent (this, OrderActivity::class.java)
        intent.putExtra("CatID", DESSERT_CAT_ID)
        startActivity(intent)
        Toast.makeText(this, "Dessert button Clicked", Toast.LENGTH_SHORT).show()
    }
    fun fourthButtonClicked(view: View) {
        val intent = Intent (this, OrderActivity::class.java)
        intent.putExtra("CatID", ICE_CREAM_CAT_ID)
        startActivity(intent)
        Toast.makeText(this, "Ice-creams button Clicked", Toast.LENGTH_SHORT).show()
    }
    fun fifthButtonClicked(view: View) {
        val intent = Intent (this, OrderActivity::class.java)
        intent.putExtra("CatID", THAI_DISH_CAT_ID)
        startActivity(intent)
        Toast.makeText(this, "Thai dishes button Clicked", Toast.LENGTH_SHORT).show()
    }
    fun sixthButtonClicked(view: View) {
        val intent = Intent (this, OrderActivity::class.java)
        intent.putExtra("CatID", FRUIT_CAT_ID)
        startActivity(intent)
        Toast.makeText(this, "Fruits button Clicked", Toast.LENGTH_SHORT).show()
    }
}