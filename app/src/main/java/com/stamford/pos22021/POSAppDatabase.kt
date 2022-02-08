package com.stamford.pos22021

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Order::class, OrderLine::class], version = 2)
abstract class POSAppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun orderLineDao(): OrderLineDao

    companion object {
        private var INSTANCE: POSAppDatabase? = null

        val MIGRATION_1_2 = object  : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE 'Macaron' + ('id' INTEGER, 'name' TEXT, " + "PRIMARY KEY('id'))")
            }
        }
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override  fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
            }
        }

        fun getInstance(context: Context): POSAppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    POSAppDatabase::class.java,
                    "pos_app.db")
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
            }
            return  INSTANCE as POSAppDatabase
        }
    }
}