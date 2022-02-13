package com.stamford.pos22021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_order_line.*
import kotlinx.android.synthetic.main.activity_order_line.*
import kotlinx.android.synthetic.main.activity_order_line.order1

class EditOrderLine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_order_line)


        update_button.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Update OrderLine")
            builder.setMessage("Are you sure you want to update this orderLine")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                val intent = Intent(this, OrderManager2::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,
                    "Update Successfully", Toast.LENGTH_SHORT).show()
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