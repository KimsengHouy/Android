package com.stamford.pos22021

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderManagerAdapter(val context: Context, val orderList: List<MyOrderDataItem>): RecyclerView.Adapter<OrderManagerAdapter.ViewHolder>() {



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

        var id: TextView
        var order_local_id: TextView
        var branch_id: TextView
        var staff_id: TextView
        var created_at: TextView
        var updated_at: TextView
        var edit: Button
        var delele: Button

        init {
            id = itemView.findViewById(R.id.id)
            order_local_id = itemView.findViewById(R.id.orderLocalId)
            branch_id = itemView.findViewById(R.id.orderBranchId)
            staff_id = itemView.findViewById(R.id.orderStaffId)
            created_at = itemView.findViewById(R.id.orderCreatedTime)
            updated_at = itemView.findViewById(R.id.orderUpdatedime)
            edit = itemView.findViewById(R.id.editOrder)
            delele = itemView.findViewById(R.id.deleteOrder)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.order_layout,parent,false)
        return ViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = orderList[position].id.toString()
        holder.order_local_id.text = orderList[position].order_local_id.toString()
        holder.branch_id.text = orderList[position].branch_id.toString()
        holder.staff_id.text = orderList[position].staff_id.toString()
        holder.created_at.text = orderList[position].created_at
        holder.updated_at.text = orderList[position].updated_at




    }



    override fun getItemCount(): Int {
        return  orderList.size
    }




}