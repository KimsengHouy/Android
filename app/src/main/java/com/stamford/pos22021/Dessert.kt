package com.stamford.pos22021



class Dessert (Name: String, Price: Int, ID: Long, Sweet: String) : Product() {

    override val name : String = Name
    override val price: Int = Price
    override val id: Long = ID
    val sweet : String = Sweet



    companion object {
        fun createDessertsList(): ArrayList<Dessert> {
            val desserts = ArrayList<Dessert>()

            desserts.add(Dessert("Waffle", 45, 3001, "Normal"))
            desserts.add(Dessert("Donut", 35, 3002, "Normal"))
            desserts.add(Dessert("Apple Pie", 75, 3003, "Medium"))
            desserts.add(Dessert("Chocolate Cake", 65, 3004, "Normal"))
            desserts.add(Dessert("Cookies", 15, 3005, "Low"))
            desserts.add(Dessert("Chocolate", 25, 3006, "Normal"))
            desserts.add(Dessert("Cup Cake", 50, 3007, "Medium"))

            return desserts

        }
    }
}