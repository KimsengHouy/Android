package com.stamford.pos22021

import androidx.room.*

@Dao
interface OrderDao {
    @Query("SELECT * FROM orderTbl")
    fun getAll (): List<Order>

    @Query("SELECT * FROM orderTbl WHERE uid LIKE :id LIMIT 1")
    fun findByID(id: Long): Order

    @Insert
    fun insert(order: Order): Long

    @Delete
    fun delete(order: Order)
}