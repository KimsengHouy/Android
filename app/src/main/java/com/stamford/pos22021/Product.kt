package com.stamford.pos22021

import androidx.room.*

open class Product {
    open val id: Long = 0
    open val name: String = "NO NAME"
    open val price: Int = 0
}

@Entity(tableName = "productTbl")
data class ProductDB (
    @PrimaryKey(autoGenerate = true) var uid: Long?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price: Int,
)

