package com.stamford.pos22021

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DessertAdapter (
    private val nDesserts: List<Dessert>,
    private val onItemClicked: (Dessert) -> Unit
) : RecyclerView.Adapter<DessertAdapter.ViewHolder>() {
    inner class ViewHolder(
        listItemView: View,
        onItemClicked_fun: (Int) -> Unit
    ) : RecyclerView.ViewHolder(listItemView) {

        init {
            listItemView.setOnClickListener {
                onItemClicked_fun(adapterPosition)
            }
        }



        val productImageView: ImageView = listItemView.findViewById<ImageView>(R.id.imageView_product)
        val productNameTextView: TextView = listItemView.findViewById<TextView>(R.id.textView_productName)
        val productPriceTextView: TextView = listItemView.findViewById<TextView>(R.id.textView_productPrice)
        val addProductButton: Button = listItemView.findViewById<Button>(R.id.button_product_add)
        val delProductButton: Button = listItemView.findViewById<Button>(R.id.button_product_del)

        init {
            addProductButton.setOnClickListener {
                OrderActivity.Condition ="addProductButton"

                onItemClicked_fun(adapterPosition)

            }
            delProductButton.setOnClickListener{
                OrderActivity.Condition ="delProductButton"
                onItemClicked_fun(adapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)


        val dessertView = inflater.inflate(R.layout.layout_item_product, parent, false)

        return ViewHolder(dessertView) {
            onItemClicked(nDesserts[it])
        }

    }




    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dessert = nDesserts[position]


        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.waffle)
            1 -> imageView.setImageResource(R.drawable.donut)
            2 -> imageView.setImageResource(R.drawable.apple_pie)
            3 -> imageView.setImageResource(R.drawable.chocolate_cake)
            4 -> imageView.setImageResource(R.drawable.cookies)
            5 -> imageView.setImageResource(R.drawable.chocolate)
            6 -> imageView.setImageResource(R.drawable.cup_cake)

            else -> {
                imageView.setImageResource(R.drawable.waffle)
            }
        }

        nameTextView.setText(dessert.name)
        priceTextView.setText(dessert.price.toString())
        addButton.setText("+")
        delButton.setText("-")
    }

    override fun getItemCount(): Int {
        return  nDesserts.size
    }

}