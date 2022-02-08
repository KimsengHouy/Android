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


class IcecreamAdapter (
    private val nIcecreams: List<Icecream>,
    private val onItemClicked: (Icecream) -> Unit
) : RecyclerView.Adapter<IcecreamAdapter.ViewHolder>() {
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IcecreamAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)


        val icecreamView = inflater.inflate(R.layout.layout_item_product, parent, false)

        return ViewHolder(icecreamView) {
            onItemClicked(nIcecreams[it])
        }

    }




    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val icecream = nIcecreams[position]


        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.chocolate_icecream)
            1 -> imageView.setImageResource(R.drawable.coconut_icecream)
            2 -> imageView.setImageResource(R.drawable.taro_icecream)
            3 -> imageView.setImageResource(R.drawable.mint_icecream)
            4 -> imageView.setImageResource(R.drawable.vanilla_icecream)
            5 -> imageView.setImageResource(R.drawable.orange_icecream)
            6 -> imageView.setImageResource(R.drawable.strawberry_icecream)

            else -> {
                imageView.setImageResource(R.drawable.chocolate_icecream )
            }
        }

        nameTextView.setText(icecream.name)
        priceTextView.setText(icecream.price.toString())
        addButton.setText("+")
        delButton.setText("-")
    }

    override fun getItemCount(): Int {
        return  nIcecreams.size
    }

}