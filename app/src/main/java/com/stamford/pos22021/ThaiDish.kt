package com.stamford.pos22021



class ThaiDish (Name: String, Price: Int, ID: Long, Spicy: Boolean, Quantity: Int) : Product() {

    override val name : String = Name
    override val price: Int = Price
    override val id: Long = ID
    var quantity: Int = Quantity
    val spicy : Boolean = Spicy



    companion object {
        fun createThaiDishesList(): ArrayList<ThaiDish> {
            val thaidishs = ArrayList<ThaiDish>()

            thaidishs.add(ThaiDish("Pad KraPao", 45, 5001, true, 1))
            thaidishs.add(ThaiDish("Pad Thai", 60, 5002, true, 1))
            thaidishs.add(ThaiDish("PineApple Fried Rice", 75, 5003, true, 1))
            thaidishs.add(ThaiDish("Tom Yum Kung", 90, 5004, true, 1))
            thaidishs.add(ThaiDish("Stew Pork Leg", 100, 5005, false, 1))
            thaidishs.add(ThaiDish("Thai Omelette Rice", 55, 5006, false, 1))
            thaidishs.add(ThaiDish("Som Tum", 50, 5007, true, 1))

            return thaidishs

        }
    }
}