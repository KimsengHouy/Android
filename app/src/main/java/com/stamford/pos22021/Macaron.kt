package com.stamford.pos22021



class Macaron (Name: String, Price: Int, ID: Long, Color: String, Quantity: Int) : Product() {

    override val name : String = Name
    override val price: Int = Price
    override val id: Long = ID
    val quantity: Int = Quantity
    val color : String = Color



    companion object {
        fun createMacaronsList(): ArrayList<Macaron> {
            val macarons = ArrayList<Macaron>()

            macarons.add(Macaron("Blue Macaron", 55, 1001, "Blue", 1))
            macarons.add(Macaron("Yellow Macaron", 60, 1002, "Yellow", 1))
            macarons.add(Macaron("Red Macaron", 50, 1003, "Red", 1))
            macarons.add(Macaron("Pink Macaron", 15, 1004, "Pink", 1))
            macarons.add(Macaron("Navy Macaron", 25, 1005, "Navy", 1))
            macarons.add(Macaron("Green Macaron", 35, 1006, "Green", 1))
            macarons.add(Macaron("Black Macaron", 45, 1007, "Black", 1))

            return macarons

        }
    }
}