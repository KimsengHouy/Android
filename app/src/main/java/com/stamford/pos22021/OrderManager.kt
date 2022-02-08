package com.stamford.pos22021

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Property
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_order_manager.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "http://10.0.2.2/ITE343/pos_api/public/"


class OrderManager : AppCompatActivity() {

    lateinit var myAdapter: OrderManagerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_manager)

        val rvOrderList = findViewById<RecyclerView>(R.id.rvOderList)
        rvOrderList.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        rvOrderList.layoutManager = linearLayoutManager

        getMyOrderData()


    }

    private fun getMyOrderData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyOrderDataItem>?> {
            override fun onResponse(
                call: Call<List<MyOrderDataItem>?>,
                response: retrofit2.Response<List<MyOrderDataItem>?>
            ) {
              val responseBody = response.body()!!

                myAdapter = OrderManagerAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                rvOderList.adapter = myAdapter




            }



            override fun onFailure(call: Call<List<MyOrderDataItem>?>, t: Throwable) {
Log.d("OrderManager", "onFailure: "+t.message)
            }
        })
    }



}






