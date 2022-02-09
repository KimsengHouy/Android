package com.stamford.pos22021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Drink (name: String, price: Int, id: Long, alcoholic: Boolean, Quantity: Int ) : Product () {
    override val name : String = name
    override val price: Int = price
    override val id: Long = id
    var quantity: Int = Quantity
    val alcoholic : Boolean = alcoholic

    companion object {
        fun createDrinkList(): ArrayList<Drink> {
            val drinks = ArrayList<Drink>()

            drinks.add(Drink("Water", 10, 2001, false, 1))
            drinks.add(Drink("Pepsi", 25, 2002, false, 1))
            drinks.add(Drink("Milk", 35, 2003, false, 1))
            drinks.add(Drink("Thai Tea", 50, 2004, false, 1))
            drinks.add(Drink("Orange Juice", 80, 2005, false, 1))
            drinks.add(Drink("Beer", 100, 2006, true, 1))
            drinks.add(Drink("Wine", 250, 2007, true, 1))
            return drinks
        }
    }
}