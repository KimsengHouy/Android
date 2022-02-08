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


class DrinkAdapter (
    private val nDrinks: List<Drink>,
    private val onItemClicked: (Drink) -> Unit
) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {
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
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)


        val drinkView = inflater.inflate(R.layout.layout_item_product, parent, false)

        return ViewHolder(drinkView) {
            onItemClicked(nDrinks[it])
        }

    }




    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val drink = nDrinks[position]


        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.water_bottle)
            1 -> imageView.setImageResource(R.drawable.pepsi)
            2 -> imageView.setImageResource(R.drawable.milk)
            3 -> imageView.setImageResource(R.drawable.thai_tea)
            4 -> imageView.setImageResource(R.drawable.orange_juice)
            5 -> imageView.setImageResource(R.drawable.beer)
            6 -> imageView.setImageResource(R.drawable.wine)

            else -> {
                imageView.setImageResource(R.drawable.water_bottle)
            }
        }

        nameTextView.setText(drink.name)
        priceTextView.setText(drink.price.toString())
        addButton.setText("+")
        delButton.setText("-")
    }

    override fun getItemCount(): Int {
        return  nDrinks.size
    }

}