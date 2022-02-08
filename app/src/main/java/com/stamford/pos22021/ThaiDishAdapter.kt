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


class ThaiDishAdapter (
    private val nThaiDishs: List<ThaiDish>,
    private val onItemClicked: (ThaiDish) -> Unit
) : RecyclerView.Adapter<ThaiDishAdapter.ViewHolder>() {
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThaiDishAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)


        val thaidishView = inflater.inflate(R.layout.layout_item_product, parent, false)

        return ViewHolder(thaidishView) {
            onItemClicked(nThaiDishs[it])
        }

    }




    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dessert = nThaiDishs[position]


        val imageView = viewHolder.productImageView
        val nameTextView = viewHolder.productNameTextView
        val priceTextView = viewHolder.productPriceTextView
        val addButton = viewHolder.addProductButton
        val delButton = viewHolder.delProductButton

        when (position) {
            0 -> imageView.setImageResource(R.drawable.pad_krapao)
            1 -> imageView.setImageResource(R.drawable.pad_thai)
            2 -> imageView.setImageResource(R.drawable.pineapple_fried_rice)
            3 -> imageView.setImageResource(R.drawable.tom_yum_kung)
            4 -> imageView.setImageResource(R.drawable.stewd_pork_leg_rice)
            5 -> imageView.setImageResource(R.drawable.thai_omelette_rice)
            6 -> imageView.setImageResource(R.drawable.som_tum)

            else -> {
                imageView.setImageResource(R.drawable.pad_krapao)
            }
        }

        nameTextView.setText(dessert.name)
        priceTextView.setText(dessert.price.toString())
        addButton.setText("+")
        delButton.setText("-")
    }

    override fun getItemCount(): Int {
        return  nThaiDishs.size
    }

}