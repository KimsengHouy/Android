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




class MacaronAdapter (
    private val nMacarons: List<Macaron>,
    private val onItemClicked: (Macaron) -> Unit
) : RecyclerView.Adapter<MacaronAdapter.ViewHolder>() {
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)


        val macarontView = inflater.inflate(R.layout.layout_item_product, parent, false)

        return ViewHolder(macarontView) {
            onItemClicked(nMacarons[it])
        }

    }




    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val macaron = nMacarons[position]


        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.macaron_blue)
            1 -> imageView.setImageResource(R.drawable.macaron_yellow)
            2 -> imageView.setImageResource(R.drawable.macaron_red)
            3 -> imageView.setImageResource(R.drawable.macaron_pink)
            4 -> imageView.setImageResource(R.drawable.macaron_navy)
            5 -> imageView.setImageResource(R.drawable.macaron_green)
            6 -> imageView.setImageResource(R.drawable.macaron_black)

            else -> {
                imageView.setImageResource(R.drawable.macaron_blue)
            }
        }

        nameTextView.setText(macaron.name)
        priceTextView.setText(macaron.price.toString())
        addButton.setText("+")
        delButton.setText("-")
    }

    override fun getItemCount(): Int {
        return  nMacarons.size
    }

}