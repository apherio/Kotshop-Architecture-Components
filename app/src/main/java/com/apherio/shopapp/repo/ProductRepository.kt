package com.apherio.shopapp.repo

import android.arch.lifecycle.LiveData
import com.apherio.shopapp.AppExecutors
import com.apherio.shopapp.api.ProductSourceService
import com.apherio.shopapp.db.ProductsDao
import com.apherio.shopapp.model.Product
import com.apherio.shopapp.model.ProductSource
import com.apherio.shopapp.model.network.Resource

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */
class ProductRepository(

        private val productsDao: ProductsDao,
        private val productSourceService: ProductSourceService,
        private val appExecutors: AppExecutors = AppExecutors()
) {

    /**
     * Fetch the products  from database if exist else fetch from web
     * and persist them in the database
     */
    fun getproduct(): LiveData<Resource<List<Product>?>> {
        return object : NetworkBoundResource<List<Product>, ProductSource>(appExecutors) {
            override fun saveCallResult(item: ProductSource) {
                productsDao.insertProducts(item.products)
            }

            override fun shouldFetch(data: List<Product>?) = true

            override fun loadFromDb() = productsDao.getProducts()

            override fun createCall() = productSourceService.getProductSource()
        }.asLiveData()
    }

}