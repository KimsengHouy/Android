package com.stamford.pos22021



class Icecream (Name: String, Price: Int, ID: Long, Flavour: String, Quantity: Int) : Product() {

    override val name : String = Name
    override val price: Int = Price
    override val id: Long = ID
    var quantity: Int = Quantity
    val flavour : String = Flavour



    companion object {
        fun createIcecreamsList(): ArrayList<Icecream> {
            val icecreams = ArrayList<Icecream>()

            icecreams.add(Icecream("Chocolate IceCream", 55, 4001, "Chocolate", 1))
            icecreams.add(Icecream("Coconut IceCream", 45, 4002, "Coconut", 1))
            icecreams.add(Icecream("Taro IceCream", 65, 4003, "Taro", 1))
            icecreams.add(Icecream("Mint IceCream", 75, 4004, "Mint", 1))
            icecreams.add(Icecream("Vanilla IceCream", 35, 4005, "Vanilla", 1))
            icecreams.add(Icecream("Orange IceCream", 30, 4006, "Orange", 1))
            icecreams.add(Icecream("Strawberry IceCream", 50, 4007, "Strawberry", 1))

            return icecreams

        }
    }
}