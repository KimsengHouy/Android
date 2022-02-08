package com.stamford.pos22021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Drink (name: String, price: Int, id: Long, alcoholic: Boolean ) : Product () {
    override val name : String = name
    override val price: Int = price
    override val id: Long = id
    val alcoholic : Boolean = alcoholic

    companion object {
        fun createDrinkList(): ArrayList<Drink> {
            val drinks = ArrayList<Drink>()

            drinks.add(Drink("Water", 10, 2001, false))
            drinks.add(Drink("Pepsi", 25, 2002, false))
            drinks.add(Drink("Milk", 35, 2003, false))
            drinks.add(Drink("Thai Tea", 50, 2004, false))
            drinks.add(Drink("Orange Juice", 80, 2005, false))
            drinks.add(Drink("Beer", 100, 2006, true))
            drinks.add(Drink("Wine", 250, 2007, true))
            return drinks
        }
    }
}