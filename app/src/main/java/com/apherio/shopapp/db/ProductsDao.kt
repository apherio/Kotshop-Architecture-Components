package com.apherio.shopapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.apherio.shopapp.model.Product


/**
 * Abstracts access to the products shopApp database
 **/
//TODO: Use inheritance for code re-usability.
@Dao
interface  ProductsDao {

    /**
     * Insert products into the database
     */
    @Insert
    fun insertProducts(productlb : List<Product>): List<Long>

    /**
     * Get all the products from database ordered by Name
     */
    @Query("SELECT * FROM  product_list ORDER BY name ASC")
    fun getProducts(): LiveData<List<Product>>

}