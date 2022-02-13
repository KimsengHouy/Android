package com.stamford.pos22021.models

data class Property  (val id: Int,
                      val order_local_id: Int,
                      val branch_id: Int,
                      val staff_id: Int,
                      val created_at: String,
                      val updated_at: String,
                      var selected: Boolean? = false)
