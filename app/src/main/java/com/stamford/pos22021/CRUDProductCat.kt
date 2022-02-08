package com.stamford.pos22021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class CRUDProductCat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudproduct_cat)
        Toast.makeText(this, "Hello ! Pick a category", Toast.LENGTH_SHORT).show()
    }

    fun Mac1ButtonClicked(view: View) {
        val intent = Intent (this, ProductCRUDActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Macarons button Clicked", Toast.LENGTH_SHORT).show()

    }
    fun DrinksBtnButtonClicked(view: View) {
        val intent = Intent (this, ProductCRUDActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Drinks button Clicked", Toast.LENGTH_SHORT).show()
    }
    fun DessertBtnClicked(view: View) {
        val intent = Intent (this, ProductCRUDActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Dessert button Clicked", Toast.LENGTH_SHORT).show()
    }
    fun IceCreamBtnClicked(view: View) {
        val intent = Intent (this, ProductCRUDActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Ice-creams button Clicked", Toast.LENGTH_SHORT).show()
    }
    fun ThaiBtnClicked(view: View) {
        val intent = Intent (this, ProductCRUDActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Thai dishes button Clicked", Toast.LENGTH_SHORT).show()
    }
    fun FruitBtnClicked(view: View) {
        val intent = Intent (this, ProductCRUDActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Fruits button Clicked", Toast.LENGTH_SHORT).show()
    }
}