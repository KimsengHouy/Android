package com.stamford.pos22021

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import com.stamford.pos22021.models.Property


class OrderManagerAdapter2(var mContext:Context, private val data: List<Property>, val onClickDelete: (Int) -> Unit): RecyclerView.Adapter<OrderManagerAdapter2.MyViewHolder>() {

private var listData: MutableList<Property> = data as MutableList<Property>
    var currentSelectedIndex = -1

   inner class MyViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView)  {
fun bind(property: Property, index: Int) {
    val id = itemView.findViewById<TextView>(R.id.id)
   val  order_local_id = itemView.findViewById<TextView>(R.id.orderLocalId)
    val branch_id = itemView.findViewById<TextView>(R.id.orderBranchId)
    val staff_id = itemView.findViewById<TextView>(R.id.orderStaffId)
    val created_at = itemView.findViewById<TextView>(R.id.orderCreatedTime)
    val updated_at = itemView.findViewById<TextView>(R.id.orderUpdatedime)
    val deleteButton = itemView.findViewById<Button>(R.id.buttonDelete)
    val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.constraint1)


    deleteButton.setOnClickListener {deleteItem (index)}





    constraintLayout.visibility = View.VISIBLE


    if(property.selected == true) {
        deleteButton.visibility = View.VISIBLE
    }else {
        deleteButton.visibility = View.GONE
    }

    id.text = property.id.toString()
    order_local_id.text = property.order_local_id.toString()
    branch_id.text = property.branch_id.toString()
    staff_id.text = property.staff_id.toString()
    created_at.text = property.updated_at
    updated_at.text = property.created_at



    constraintLayout.setOnLongClickListener {markSelectedItem(index)}

    constraintLayout.setOnClickListener {
        val intent = Intent (mContext, OrderLineActivity::class.java)
        intent.putExtra("order", listData[position].order_local_id.toString())
        intent.putExtra("staff", listData[position].staff_id.toString())
        intent.putExtra("branch", listData[position].staff_id.toString())

        mContext.startActivity(intent)




    }

}
       fun markSelectedItem(index: Int) : Boolean {
           for(item in listData) {
               item.selected = false
           }
listData.get(index).selected = true
           currentSelectedIndex = index
           notifyDataSetChanged()

           return true
       }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_layout,parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData[position], position)

    }

    override fun getItemCount(): Int {
        return  listData.size
    }

fun deleteItem(index: Int) {
    listData.removeAt(index)
    notifyDataSetChanged()
}
fun setItems(items: List<Property>){
//    listData = items
//    notifyDataSetChanged()
}
}





