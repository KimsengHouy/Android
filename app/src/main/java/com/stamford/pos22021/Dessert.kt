package com.stamford.pos22021



class Dessert (Name: String, Price: Int, ID: Long, Sweet: String, Quantity: Int) : Product() {

    override val name : String = Name
    override val price: Int = Price
    override val id: Long = ID
    var quantity: Int = Quantity
    val sweet : String = Sweet



    companion object {
        fun createDessertsList(): ArrayList<Dessert> {
            val desserts = ArrayList<Dessert>()

            desserts.add(Dessert("Waffle", 45, 3001, "Normal", 1))
            desserts.add(Dessert("Donut", 35, 3002, "Normal", 1))
            desserts.add(Dessert("Apple Pie", 75, 3003, "Medium", 1))
            desserts.add(Dessert("Chocolate Cake", 65, 3004, "Normal", 1))
            desserts.add(Dessert("Cookies", 15, 3005, "Low", 1))
            desserts.add(Dessert("Chocolate", 25, 3006, "Normal",1))
            desserts.add(Dessert("Cup Cake", 50, 3007, "Medium",1))

            return desserts

        }
    }
}