package com.stamford.pos22021

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_order_line.*


class OrderLineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_line)

        val order = intent.getStringExtra("order")
        val staff = intent.getStringExtra("staff")
        val branch = intent.getStringExtra("branch")
        order1.text = order
        staffid1.text = staff
        branchid1.text = branch


        button_edit.setOnClickListener {
            val intent = Intent(this, EditOrderLine::class.java)
            intent.putExtra("order1", order.toString() )
            intent.putExtra("staff1", staff.toString() )
            intent.putExtra("branch1", branch.toString())
            startActivity(intent)

        }



        button_delete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete OrderLine")
            builder.setMessage("Are you sure you want to delete this orderLine")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                val intent = Intent(this, OrderManager2::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }

            builder.setNeutralButton("Maybe") { dialog, which ->
                Toast.makeText(applicationContext,
                    "Let think again!", Toast.LENGTH_SHORT).show()
            }
            builder.show()

        }





    }
}