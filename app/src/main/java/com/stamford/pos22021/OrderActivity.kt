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

class OrderActivity : AppCompatActivity() {

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

                // Create adapter passing in the sample user data

                //val adapter = MacaronAdapter(macarons)

                val adapter = MacaronAdapter(macarons) { macaron ->

                    Log.d(TAG, "A row is clicked. Macaron price is ${macaron.price}")

                    totalAmount += macaron.price

                    model.totalAmount.value = model.totalAmount.value?.plus(macaron.price)
                    Log.d(TAG, "model value ${model.totalAmount.value}")


//                    total_price_tv.text = getString(R.string.total_amount_str, model.totalAmount.value)


                }


                // Attach the adapter to the recyclerview to populate items

                rvMacarons.adapter = adapter

                // Set layout manager to position the items

                rvMacarons.layoutManager = LinearLayoutManager(this)

                val rvorderList = findViewById<View>(R.id.rvOrderList1) as RecyclerView




                Toast.makeText(this, "Macaron Category!", Toast.LENGTH_SHORT).show()

            }
            DRINK_CAT_ID -> {
                val rvDrinks = findViewById<View>(R.id.rvProductList) as RecyclerView

                val drinks = Drink.createDrinkList()

                val adapter = DrinkAdapter(drinks) { drink ->

                    Log.d(TAG, "A row is clicked. Drink price is ${drink.price}")
                    totalAmount += drink.price

                    model.totalAmount.value = model.totalAmount.value?.plus(drink.price)
                    Log.d(TAG, "model value ${model.totalAmount.value}")

                }

                rvDrinks.adapter = adapter

                rvDrinks.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "Drink Category!", Toast.LENGTH_SHORT).show()
            }
            DESSERT_CAT_ID -> {
                val rvDesserts = findViewById<View>(R.id.rvProductList) as RecyclerView

                val desserts = Dessert.createDessertsList()

                val adapter = DessertAdapter(desserts) { dessert ->

                    Log.d(TAG, "A row is clicked. Drink price is ${dessert.price}")
                    totalAmount += dessert.price

                    model.totalAmount.value = model.totalAmount.value?.plus(dessert.price)
                    Log.d(TAG, "model value ${model.totalAmount.value}")

                }

                rvDesserts.adapter = adapter

                rvDesserts.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "Dessert Category!", Toast.LENGTH_SHORT).show()
            }
            ICE_CREAM_CAT_ID -> {
                val rvIcecreams = findViewById<View>(R.id.rvProductList) as RecyclerView

                val iceceams = Icecream.createIcecreamsList()

                val adapter = IcecreamAdapter(iceceams) { icecream ->

                    Log.d(TAG, "A row is clicked. Drink price is ${icecream.price}")
                    totalAmount += icecream.price

                    model.totalAmount.value = model.totalAmount.value?.plus(icecream.price)
                    Log.d(TAG, "model value ${model.totalAmount.value}")

                }

                rvIcecreams.adapter = adapter

                rvIcecreams.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "IceCream Category!", Toast.LENGTH_SHORT).show()
            }
            THAI_DISH_CAT_ID -> {
                val rvThaiDishs = findViewById<View>(R.id.rvProductList) as RecyclerView

                val thaidishs = ThaiDish.createThaiDishesList()

                val adapter = ThaiDishAdapter(thaidishs) { thaidish ->

                    Log.d(TAG, "A row is clicked. Drink price is ${thaidish.price}")
                    totalAmount += thaidish.price

                    model.totalAmount.value = model.totalAmount.value?.plus(thaidish.price)
                    Log.d(TAG, "model value ${model.totalAmount.value}")

                }

                rvThaiDishs.adapter = adapter

                rvThaiDishs.layoutManager = LinearLayoutManager(this)

                Toast.makeText(this, "ThaiDish Category!", Toast.LENGTH_SHORT).show()
            }
            FRUIT_CAT_ID -> {
                val rvFruits = findViewById<View>(R.id.rvProductList) as RecyclerView

                val fruits = Fruit.createFruitsList()

                val adapter = FruitAdapter(fruits) { fruit ->

                    Log.d(TAG, "A row is clicked. Drink price is ${fruit.price}")
                    totalAmount += fruit.price

                    model.totalAmount.value = model.totalAmount.value?.plus(fruit.price)
                    Log.d(TAG, "model value ${model.totalAmount.value}")

                }

                rvFruits.adapter = adapter

                rvFruits.layoutManager = LinearLayoutManager(this)

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

    fun onclick_submit_order_btn (view: View) {

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

    fun onclick_retrieve_orders_btn (view: View) {
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