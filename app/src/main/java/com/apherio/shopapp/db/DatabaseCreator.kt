package com.apherio.shopapp.db

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Singleton instance to build the database object only once as
 * it's resource heavy.
 */
object DatabaseCreator {

    /**
     * Create database instance when the singleton instance is created for the
     * first time.
     */
    fun database(context: Context): ProductsDatabase {
        return Room.databaseBuilder(context, ProductsDatabase::class.java, "products-db").build()
    }

}