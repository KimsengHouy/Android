package com.stamford.pos22021

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderListAdapter4(private val orderList: List<Icecream>) : RecyclerView.Adapter<OrderListAdapter4.ViewHolder>() {
    inner class ViewHolder(
        listItemView: View,
    ) : RecyclerView.ViewHolder(listItemView) {


        val itemnameTextView: TextView = listItemView.findViewById<TextView>(R.id.name_orderList)
        val itempriceTextView: TextView = listItemView.findViewById<TextView>(R.id.price_orderList)
        val itemquantityTextView: TextView = listItemView.findViewById<TextView>(R.id.quantity_orderList)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val context = parent.context
        val OrderView = LayoutInflater.from(context).inflate(R.layout.orderviewholder_layout, parent, false)

        return ViewHolder(OrderView)

    }

    override fun onBindViewHolder(holder:OrderListAdapter4.ViewHolder, position: Int) {
        val item:Icecream = orderList[position]

        val itemTextView = holder.itemnameTextView
        val itempriceView = holder.itempriceTextView
        val itemquantityView = holder.itemquantityTextView

        itemTextView.setText(item.name)
        itempriceView.setText(item.price.toString())
        itemquantityView.setText(item.quantity.toString())
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}