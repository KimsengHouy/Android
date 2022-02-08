package com.stamford.pos22021

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderListAdapter( private val orderList: List<Macaron>, private val onItemClicked: (Macaron) -> Unit) : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    inner class ViewHolder(
        listItemView: View,
        onItemClicked_fun: (Int) -> Unit
    ) : RecyclerView.ViewHolder(listItemView) {

        init {
            listItemView.setOnClickListener {
                onItemClicked_fun(adapterPosition)
            }
        }


        val itemnameTextView: TextView = listItemView.findViewById<TextView>(R.id.itemNameTextView)
        val itempriceTextView: TextView = listItemView.findViewById<TextView>(R.id.itemPriceTextView)
        val itemquantityTextView: TextView = listItemView.findViewById<TextView>(R.id.itemQuantityTextView)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val context = parent.context
        val OrderView = LayoutInflater.from(context).inflate(R.layout.orderviewholder_layout, parent, false)

        return ViewHolder(OrderView) {
            onItemClicked(orderList[it])
        }
    }

    override fun onBindViewHolder(holder:OrderListAdapter.ViewHolder, position: Int) {
        val item = orderList[position]

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