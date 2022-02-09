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


class FruitAdapter (
    private val nFruits: List<Fruit>,
    private val onItemClicked: (Fruit) -> Unit
) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)


        val fruitView = inflater.inflate(R.layout.layout_item_product, parent, false)

        return ViewHolder(fruitView) {
            onItemClicked(nFruits[it])
        }

    }




    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val fruit = nFruits[position]


        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.apple)
            1 -> imageView.setImageResource(R.drawable.orange)
            2 -> imageView.setImageResource(R.drawable.banana)
            3 -> imageView.setImageResource(R.drawable.grape)
            4 -> imageView.setImageResource(R.drawable.strawberry)
            5 -> imageView.setImageResource(R.drawable.papaya)
            6 -> imageView.setImageResource(R.drawable.mango)

            else -> {
                imageView.setImageResource(R.drawable.apple)
            }
        }

        nameTextView.setText(fruit.name)
        priceTextView.setText(fruit.price.toString())
        addButton.setText("+")
        delButton.setText("-")
    }

    override fun getItemCount(): Int {
        return  nFruits.size
    }

}