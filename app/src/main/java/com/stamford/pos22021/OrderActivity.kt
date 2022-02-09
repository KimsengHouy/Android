package com.stamford.pos22021

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch
import androidx.lifecycle.Observer
import android.util.Log
import android.widget.Toast
import android.widget.TextView

import androidx.activity.viewModels
import kotlinx.android.synthetic.main.layout_item_product.*


class OrderActivity : AppCompatActivity() {

    var itemList = arrayListOf<Macaron>()
    var itemList2 = arrayListOf<Drink>()
    var itemList3 = arrayListOf<Dessert>()
    var itemList4 = arrayListOf<Icecream>()
    var itemList5 = arrayListOf<ThaiDish>()
    var itemList6 = arrayListOf<Fruit>()


    companion object {
        var Condition: String? = null
    }

    private val TAG = "OrderActivity"
    private var totalAmount: Int = 0

    private val model: OrderViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val total_price_tv = findViewById<TextView>(R.id.total_price_textView)


//        val amountObserver = Observer<Int> { newAmount ->
//
//            total_price_tv.text = newAmount.toString()
//
//        }

//        model.totalAmount.observe(this, amountObserver)

        val amountObserver = Observer<Int> { newAmount ->

            total_price_tv.text = getString(R.string.total_amount_str , newAmount)
        }

        model.getTotalAmount().observe(this, amountObserver)






        val intent = intent

        val catID = intent.getIntExtra("CatID", totalAmount)


        when (catID) {
            MACARON_CAT_ID -> {


                val rvMacarons = findViewById<View>(R.id.rvProductList) as RecyclerView

                // Initialize macarons

                val macarons = Macaron.createMacaronsList()

                val rvOrderList1 = findViewById<View>(R.id.rvOrderList11) as RecyclerView


                val orderlist = OrderListAdapter(itemList)




                // Create adapter passing in the sample user data

                //val adapter = MacaronAdapter(macarons)

                val adapter = MacaronAdapter(macarons) { macaron ->

                    val getOrder = itemList.indexOf(macaron)

                    Log.d(TAG, "A row is clicked. Macaron price is ${macaron.price}")

                    if (Condition.equals("addProductButton")) {
                        totalAmount += macaron.price

                        model.totalAmount.value = model.totalAmount.value?.plus(macaron.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)

                        if (itemList.contains(macaron)) {
                            macaron.quantity = macaron.quantity + 1
                            itemList.set(getOrder, macaron)
                            orderlist.notifyItemChanged(itemList.size)
                        } else {
                            itemList.add(macaron)
                            orderlist.notifyItemChanged(itemList.size)
                        }

                    } else if (Condition.equals("delProductButton")) {
                        val getOrder = itemList.indexOf(macaron)
                        if (itemList.contains(macaron) && macaron.quantity >= 1) {
                            macaron.quantity = macaron.quantity - 1
                            itemList.set(getOrder, macaron)
                            orderlist.notifyItemChanged(getOrder)

                            totalAmount -= macaron.price
                            model.totalAmount.value = model.totalAmount.value?.minus(macaron.price)
                            Log.d(TAG, "model value ${model.totalAmount.value}")


                            total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                        }
                    } else if (itemList.contains(macaron) && macaron.quantity == 0) {
                        itemList.removeAt(getOrder)
                        orderlist.notifyItemChanged(getOrder)
                        totalAmount -= macaron.price

                        model.totalAmount.value = model.totalAmount.value?.minus(macaron.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                    }




                }


                // Attach the adapter to the recyclerview to populate items

                rvMacarons.adapter = adapter

                // Set layout manager to position the items

                rvMacarons.layoutManager = LinearLayoutManager(this)

                rvOrderList1.adapter = orderlist

                rvOrderList1.layoutManager = LinearLayoutManager(this)






                Toast.makeText(this, "Macaron Category!", Toast.LENGTH_SHORT).show()

            }
            DRINK_CAT_ID -> {
                val rvDrinks = findViewById<View>(R.id.rvProductList) as RecyclerView

                val drinks = Drink.createDrinkList()

                val rvOrderList2 = findViewById<View>(R.id.rvOrderList11) as RecyclerView


                val orderlist2 = OrderListAdapter2(itemList2)



                val adapter = DrinkAdapter(drinks) { drinks ->

                    val getOrder = itemList2.indexOf(drinks)

                    Log.d(TAG, "A row is clicked. Macaron price is ${drinks.price}")

                    if (Condition.equals("addProductButton")) {
                        totalAmount += drinks.price

                        model.totalAmount.value = model.totalAmount.value?.plus(drinks.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)

                        if (itemList2.contains(drinks)) {
                            drinks.quantity = drinks.quantity + 1
                            itemList2.set(getOrder, drinks)
                            orderlist2.notifyItemChanged(itemList2.size)
                        } else {
                            itemList2.add(drinks)
                            orderlist2.notifyItemChanged(itemList2.size)
                        }

                    } else if (Condition.equals("delProductButton")) {
                        val getOrder = itemList2.indexOf(drinks)
                        if (itemList2.contains(drinks) && drinks.quantity >= 1) {
                            drinks.quantity = drinks.quantity - 1
                            itemList2.set(getOrder, drinks)
                            orderlist2.notifyItemChanged(getOrder)

                            totalAmount -= drinks.price
                            model.totalAmount.value = model.totalAmount.value?.minus(drinks.price)
                            Log.d(TAG, "model value ${model.totalAmount.value}")


                            total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                        }
                    } else if (itemList2.contains(drinks) && drinks.quantity == 0) {
                        itemList2.removeAt(getOrder)
                        orderlist2.notifyItemChanged(getOrder)
                        totalAmount -= drinks.price

                        model.totalAmount.value = model.totalAmount.value?.minus(drinks.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                    }


                }

                rvDrinks.adapter = adapter

                rvDrinks.layoutManager = LinearLayoutManager(this)

                rvOrderList2.adapter = orderlist2

                rvOrderList2.layoutManager = LinearLayoutManager(this)


                Toast.makeText(this, "Dessert Category!", Toast.LENGTH_SHORT).show()
            }
            DESSERT_CAT_ID -> {
                val rvDesserts = findViewById<View>(R.id.rvProductList) as RecyclerView

                val desserts = Dessert.createDessertsList()

                val rvOrderList3 = findViewById<View>(R.id.rvOrderList11) as RecyclerView


                val orderlist3 = OrderListAdapter3(itemList3)

                val adapter = DessertAdapter(desserts) { dessert ->
                    val getOrder = itemList3.indexOf(dessert)

                    Log.d(TAG, "A row is clicked. Macaron price is ${dessert.price}")

                    if (Condition.equals("addProductButton")) {
                        totalAmount += dessert.price

                        model.totalAmount.value = model.totalAmount.value?.plus(dessert.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)

                        if (itemList3.contains(dessert)) {
                            dessert.quantity = dessert.quantity + 1
                            itemList3.set(getOrder, dessert)
                            orderlist3.notifyItemChanged(itemList3.size)
                        } else {
                            itemList3.add(dessert)
                            orderlist3.notifyItemChanged(itemList3.size)
                        }

                    } else if (Condition.equals("delProductButton")) {
                        val getOrder = itemList3.indexOf(dessert)
                        if (itemList3.contains(dessert) && dessert.quantity >= 1) {
                            dessert.quantity = dessert.quantity - 1
                            itemList3.set(getOrder, dessert)
                            orderlist3.notifyItemChanged(getOrder)

                            totalAmount -= dessert.price
                            model.totalAmount.value = model.totalAmount.value?.minus(dessert.price)
                            Log.d(TAG, "model value ${model.totalAmount.value}")


                            total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                        }
                    } else if (itemList3.contains(dessert) && dessert.quantity == 0) {
                        itemList3.removeAt(getOrder)
                        orderlist3.notifyItemChanged(getOrder)
                        totalAmount -= dessert.price

                        model.totalAmount.value = model.totalAmount.value?.minus(dessert.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                    }

                }

                rvDesserts.adapter = adapter

                rvDesserts.layoutManager = LinearLayoutManager(this)

                rvOrderList3.adapter = orderlist3

                rvOrderList3.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "Dessert Category!", Toast.LENGTH_SHORT).show()
            }
            ICE_CREAM_CAT_ID -> {
                val rvIcecreams = findViewById<View>(R.id.rvProductList) as RecyclerView

                val iceceams = Icecream.createIcecreamsList()

                val rvOrderList4 = findViewById<View>(R.id.rvOrderList11) as RecyclerView


                val orderlist4 = OrderListAdapter4(itemList4)

                val adapter = IcecreamAdapter(iceceams) { icecream ->

                    val getOrder = itemList4.indexOf(icecream)

                    Log.d(TAG, "A row is clicked. Macaron price is ${icecream.price}")

                    if (Condition.equals("addProductButton")) {
                        totalAmount += icecream.price

                        model.totalAmount.value = model.totalAmount.value?.plus(icecream.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)

                        if (itemList4.contains(icecream)) {
                            icecream.quantity = icecream.quantity + 1
                            itemList4.set(getOrder, icecream)
                            orderlist4.notifyItemChanged(itemList4.size)
                        } else {
                            itemList4.add(icecream)
                            orderlist4.notifyItemChanged(itemList4.size)
                        }

                    } else if (Condition.equals("delProductButton")) {
                        val getOrder = itemList4.indexOf(icecream)
                        if (itemList4.contains(icecream) && icecream.quantity >= 1) {
                            icecream.quantity = icecream.quantity - 1
                            itemList4.set(getOrder, icecream)
                            orderlist4.notifyItemChanged(getOrder)

                            totalAmount -= icecream.price
                            model.totalAmount.value = model.totalAmount.value?.minus(icecream.price)
                            Log.d(TAG, "model value ${model.totalAmount.value}")


                            total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                        }
                    } else if (itemList4.contains(icecream) && icecream.quantity == 0) {
                        itemList4.removeAt(getOrder)
                        orderlist4.notifyItemChanged(getOrder)
                        totalAmount -= icecream.price

                        model.totalAmount.value = model.totalAmount.value?.minus(icecream.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                    }

                }

                rvIcecreams.adapter = adapter

                rvIcecreams.layoutManager = LinearLayoutManager(this)

                rvOrderList4.adapter = orderlist4

                rvOrderList4.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "IceCream Category!", Toast.LENGTH_SHORT).show()
            }
            THAI_DISH_CAT_ID -> {
                val rvThaiDishs = findViewById<View>(R.id.rvProductList) as RecyclerView

                val thaidishs = ThaiDish.createThaiDishesList()

                val rvOrderList5 = findViewById<View>(R.id.rvOrderList11) as RecyclerView


                val orderlist5 = OrderListAdapter5(itemList5)

                val adapter = ThaiDishAdapter(thaidishs) { thaidish ->

                    val getOrder = itemList5.indexOf(thaidish)

                    Log.d(TAG, "A row is clicked. Macaron price is ${thaidish.price}")

                    if (Condition.equals("addProductButton")) {
                        totalAmount += thaidish.price

                        model.totalAmount.value = model.totalAmount.value?.plus(thaidish.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)

                        if (itemList5.contains(thaidish)) {
                            thaidish.quantity = thaidish.quantity + 1
                            itemList5.set(getOrder, thaidish)
                            orderlist5.notifyItemChanged(itemList5.size)
                        } else {
                            itemList5.add(thaidish)
                            orderlist5.notifyItemChanged(itemList5.size)
                        }

                    } else if (Condition.equals("delProductButton")) {
                        val getOrder = itemList5.indexOf(thaidish)
                        if (itemList5.contains(thaidish) && thaidish.quantity >= 1) {
                            thaidish.quantity = thaidish.quantity - 1
                            itemList5.set(getOrder, thaidish)
                            orderlist5.notifyItemChanged(getOrder)

                            totalAmount -= thaidish.price
                            model.totalAmount.value = model.totalAmount.value?.minus(thaidish.price)
                            Log.d(TAG, "model value ${model.totalAmount.value}")


                            total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                        }
                    } else if (itemList5.contains(thaidish) && thaidish.quantity == 0) {
                        itemList5.removeAt(getOrder)
                        orderlist5.notifyItemChanged(getOrder)
                        totalAmount -= thaidish.price

                        model.totalAmount.value = model.totalAmount.value?.minus(thaidish.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                    }

                }

                rvThaiDishs.adapter = adapter

                rvThaiDishs.layoutManager = LinearLayoutManager(this)

                rvOrderList5.adapter = orderlist5

                rvOrderList5.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "ThaiDish Category!", Toast.LENGTH_SHORT).show()
            }
            FRUIT_CAT_ID -> {
                val rvFruits = findViewById<View>(R.id.rvProductList) as RecyclerView

                val fruits = Fruit.createFruitsList()

                val rvOrderList6 = findViewById<View>(R.id.rvOrderList11) as RecyclerView


                val orderlist6 = OrderListAdapter6(itemList6)

                val adapter = FruitAdapter(fruits) { fruit ->

                    val getOrder = itemList6.indexOf(fruit)

                    Log.d(TAG, "A row is clicked. Macaron price is ${fruit.price}")

                    if (Condition.equals("addProductButton")) {
                        totalAmount += fruit.price

                        model.totalAmount.value = model.totalAmount.value?.plus(fruit.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)

                        if (itemList6.contains(fruit)) {
                            fruit.quantity = fruit.quantity + 1
                            itemList6.set(getOrder, fruit)
                            orderlist6.notifyItemChanged(itemList6.size)
                        } else {
                            itemList6.add(fruit)
                            orderlist6.notifyItemChanged(itemList6.size)
                        }

                    } else if (Condition.equals("delProductButton")) {
                        val getOrder = itemList6.indexOf(fruit)
                        if (itemList6.contains(fruit) && fruit.quantity >= 1) {
                            fruit.quantity = fruit.quantity - 1
                            itemList6.set(getOrder, fruit)
                            orderlist6.notifyItemChanged(getOrder)

                            totalAmount -= fruit.price
                            model.totalAmount.value = model.totalAmount.value?.minus(fruit.price)
                            Log.d(TAG, "model value ${model.totalAmount.value}")


                            total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                        }
                    } else if (itemList6.contains(fruit) && fruit.quantity == 0) {
                        itemList6.removeAt(getOrder)
                        orderlist6.notifyItemChanged(getOrder)
                        totalAmount -= fruit.price

                        model.totalAmount.value = model.totalAmount.value?.minus(fruit.price)
                        Log.d(TAG, "model value ${model.totalAmount.value}")


                        total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)
                    }

                }

                rvFruits.adapter = adapter

                rvFruits.layoutManager = LinearLayoutManager(this)

                rvOrderList6.adapter = orderlist6

                rvOrderList6.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "Fruits Category!", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val rvMacarons = findViewById<View>(R.id.rvProductList) as RecyclerView

                val macarons = Macaron.createMacaronsList()

                val adapter = MacaronAdapter(macarons) {
                    Log.d(TAG, "Unknown Category")
                }

                rvMacarons.adapter = adapter

                rvMacarons.layoutManager = LinearLayoutManager(this)
                Toast.makeText(this, "Unknown Category", Toast.LENGTH_SHORT).show()

            }
        }

    }

    fun onclickSubmitOrderBtn (view: View) {

        Log.i(TAG, "Submit order button clicked, Order is going to be stored.")

        GlobalScope.launch {
            val order1 = Order(null, 5001, 2001)

            val db = POSAppDatabase.getInstance(applicationContext)
            val orderID : Long = db.orderDao().insert(order1)

            val orderLine1= OrderLine(null, orderID, 1001, 55, 3)
            val orderLine2 = OrderLine(null, orderID, 1005, 25, 2)
            db.orderLineDao().insertAll(orderLine1, orderLine2)
        }
    }

    fun onclickRetrieveOrderBtn (view: View) {
        Log.i(TAG, "Retrieve Orders button clicked.")

        GlobalScope.launch {

            val db = POSAppDatabase.getInstance(applicationContext)
            val orders = db.orderDao().getAll()

            Log.i(TAG, "orders:")
            for (order in orders) {
                Log.i (TAG, "Order ID = ${order.uid}, " + "Branch ID = ${order.branchID} " + "Staff ID = ${order.staffID}")
            }
            Log.i(TAG, "orderLines:")
            val orderLines = db.orderLineDao().getAll()
            for (orderLine in orderLines) {
                Log.i(TAG, "orderLine ID = ${orderLine.uid}, " + "Order ID = ${orderLine.orderID} " + "Product ID = ${orderLine.productID}")
            }
        }
    }



}