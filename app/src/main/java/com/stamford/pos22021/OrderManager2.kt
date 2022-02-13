package com.stamford.pos22021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stamford.pos22021.models.Property
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class OrderManager2 : AppCompatActivity() {
    lateinit var data: MutableList<Property>

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: OrderManagerAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_manager)


        manager = LinearLayoutManager(this)
        getAllOrder()



    }

    fun getAllOrder(){
        Api.retrofitService.getAllOrder().enqueue(object: Callback<List<Property>>{
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                if(response.isSuccessful) {
                    recyclerView = findViewById<RecyclerView>(R.id.rvOderList).apply{
                        data = response.body() as MutableList<Property>
                        myAdapter = OrderManagerAdapter2(this@OrderManager2, data) {index -> deleteItem(index)}
                        layoutManager = manager
                        adapter = myAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun deleteItem(index: Int){
        if (::data.isInitialized && ::myAdapter.isInitialized){
            data.removeAt(index)
            myAdapter.setItems(data)
        }
    }



}