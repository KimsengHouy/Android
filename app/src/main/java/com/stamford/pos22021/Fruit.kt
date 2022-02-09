package com.stamford.pos22021



class Fruit (Name: String, Price: Int, ID: Long, Vitamin: String, Quantity:Int) : Product() {

    override val name : String = Name
    override val price: Int = Price
    override val id: Long = ID
    var quantity: Int = Quantity
    val vitamin : String = Vitamin



    companion object {
        fun createFruitsList(): ArrayList<Fruit> {
            val fruits = ArrayList<Fruit>()

            fruits.add(Fruit("Apple", 45, 6001, "A", 1))
            fruits.add(Fruit("Orange", 60, 6002, "C", 1))
            fruits.add(Fruit("Banana", 75, 6003, "A", 1))
            fruits.add(Fruit("Grape", 90, 6004, "A", 1))
            fruits.add(Fruit("Straw Berry", 100, 6005, "C", 1))
            fruits.add(Fruit("Papaya", 55, 6006, "C", 1))
            fruits.add(Fruit("Mango", 50, 6007, "C", 1))

            return fruits

        }
    }
}