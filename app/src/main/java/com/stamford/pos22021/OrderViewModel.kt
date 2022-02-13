package com.stamford.pos22021

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel: ViewModel() {

//    var totalAmount: Int = 0

    val totalAmount: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }

    fun getTotalAmount(): LiveData<Int> {
        return totalAmount
    }
}



