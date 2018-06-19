package com.apherio.shopapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.apherio.shopapp.model.Product
import com.apherio.shopapp.model.ProductSource




@Database(entities = arrayOf(Product::class), version = 1)
abstract class ProductsDatabase : RoomDatabase() {

    /**
     * Get products DAO
     */
    abstract fun ProductsDao(): ProductsDao
}