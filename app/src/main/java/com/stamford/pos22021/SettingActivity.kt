package com.stamford.pos22021

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.icu.text.DateTimePatternGenerator.PatternInfo.OK
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider.getUriForFile
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.File
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.*

class SettingActivity : AppCompatActivity() {

    private val TAG = "SettingActivity"
    private var imageUri: Uri? = null






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)



        val btnSearchContact = findViewById<Button>(R.id.button_searchContact)
// set on-click listener
        btnSearchContact.setOnClickListener {
            Toast.makeText(this, "Kimseng has clicked the search contact button", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ContactProvider::class.java)
            startActivity(intent)
        }

        val btnOrderManager = findViewById<Button>(R.id.button_orderManager)
        btnOrderManager.setOnClickListener {
            Toast.makeText(this, "Kimseng has clicked the order manager button", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, OrderManager2::class.java)
            startActivity(intent)
        }


//        findViewById<FloatingActionButton>(R.id.).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//
//        }


        val takePic = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            isSuccess -> if (isSuccess) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alert Title")
            builder.setMessage("Photo Save Successfully")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(applicationContext, "Yes", Toast.LENGTH_SHORT).show()

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext, "No", Toast.LENGTH_SHORT).show()
                }
                builder.setNeutralButton("Maybe") { dialog, which ->
                    Toast.makeText(applicationContext, "Maybe", Toast.LENGTH_SHORT).show()
                }

                val linearLayout = findViewById<LinearLayout>(R.id.setting_left_panel)
                val factor: Float = linearLayout.context.resources.displayMetrics.density
                val width = (linearLayout.width * factor * 0.5)
                val height = (linearLayout.height * factor * 0.3)

                val imageView = ImageView(this)
                imageView.layoutParams = LinearLayout.LayoutParams(
                    width.toInt(),
                    height.toInt()
                )
                imageView.setImageURI(imageUri)

                linearLayout?.addView(imageView)

            }

            builder.show()
        }else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alert Title")
            builder.setMessage("Photo Save failed")

            builder.setPositiveButton(android.R.string.yes) {
                dialog, which -> Toast.makeText(applicationContext, "Yes", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.no) {
                dialog, which -> Toast.makeText(applicationContext, "No", Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("Maybe") {
                dialog, which -> Toast.makeText(applicationContext, "Maybe", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
        }

        val uploadDailyReportBtn = findViewById<Button>(R.id.UploadDailyReport)

        uploadDailyReportBtn.setOnClickListener {
            val imagePath: File = File(getExternalFilesDir(null), "my_images")
            imagePath.mkdir()
            val newFile = File(imagePath, "img_" + System.currentTimeMillis() + ".jpg")
            val imgUri: Uri = getUriForFile(
                this@SettingActivity,
                "com.stamford.pos22021.fileprovider", newFile
            )
            this.imageUri = imgUri
            takePic.launch(imgUri)
        }

        val uploadImageToRemoteServerBtn = findViewById<Button>(R.id.UploadImagesToRemoteServer)

        uploadImageToRemoteServerBtn.setOnClickListener{
            uploadImage()
        }


        val retrieveOrderFromRemoteServerBtn =
            findViewById<Button>(R.id.RetriveOrderFromRemoteServer)
        retrieveOrderFromRemoteServerBtn.setOnClickListener{
            Log.i(TAG, "Retrieve orders from remote server")

            GlobalScope.launch {
                val url = "http://10.0.2.2/ITE343/pos_api/public/orders"

                val jsonRequest = JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null, {
                        response ->
                        Log.i(TAG, "Response: %s".format(response.toString()))

                        for (i in 0 until response.length()) {
                            val order = response.getJSONObject(i)

                            Log.i(TAG, "Order $i = ${order.get("id")}, branch ID is = ${order.get("branch_id")}, Staff ID is = ${order.get("staff_id")}")
                        }
                    },
                    {
                        Log.i(TAG, "That didn't work! Error is : $it")
                    }
                )
                jsonRequest.retryPolicy = DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    0,
                    1f
                )
                VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)
            }
        }
        val SubmitOrderToRemoteServerBtn =
            findViewById<Button>(R.id.SubmitOrderToRemoteServer)
        SubmitOrderToRemoteServerBtn.setOnClickListener{
            Log.i(TAG, "Submit orders to remote server")

            GlobalScope.launch {
                val localOrders = getOrdersFromLocalDBAsync()

                Log.i(TAG, "Orders:")
                for (order in localOrders.await()) {
                    Log.i(TAG, "Order ID = ${order.uid}, " + "Branch ID = ${order.branchID} " + "Staff ID = ${order.staffID}")

                    val url = "http://10.0.2.2/ITE343/pos_api/public/order"

                    val params = JSONObject()
                    params.put("order_local_id", "${order.uid}")
                    params.put("branch_id", "${order.branchID}")
                    params.put("staff_id", "${order.staffID}")

                    val jsonRequest = JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        params,
                        {
                            response ->
                            Log.i(TAG, "Response is: $response")
                        },
                        {
                            Log.i(TAG, "That didn't work! Error is : $it")
                        }

                    )
                    jsonRequest.retryPolicy = DefaultRetryPolicy(
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        0,
                        1f
                    )
                    VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonRequest)
                }
            }
        }

    }

    private fun uploadImage() {
        val imageView = ImageView(this)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(imageUri!!).addOnSuccessListener {
imageView.setImageURI(null)
            Toast.makeText(this@SettingActivity, "Successfully uploaded",Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()
        }.addOnFailureListener{
if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@SettingActivity, "Failed",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun getOrdersFromLocalDBAsync() = GlobalScope.async {
        val db = POSAppDatabase.getInstance(applicationContext)
        db.orderDao().getAll()
    }
}