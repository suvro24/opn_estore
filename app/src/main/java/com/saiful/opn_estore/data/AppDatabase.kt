package com.saiful.opn_estore.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saiful.opn_estore.data.model.Product


@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao():CartDao
}