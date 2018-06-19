package com.apherio.shopapp.ui


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.apherio.shopapp.api.ProductSourceService
import com.apherio.shopapp.db.DatabaseCreator
import com.apherio.shopapp.model.Product
import com.apherio.shopapp.model.network.Resource
import com.apherio.shopapp.repo.ProductRepository

/**
 * A container for [Products] related data to show on the UI.
 */
class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    private var productvm: LiveData<Resource<List<Product>?>>

    init {
        // TODO: Depdancy injection can be done
        val productsRepository = ProductRepository(
                DatabaseCreator.database(application).ProductsDao(),
                ProductSourceService.getProductSourceService()
        )
        productvm = productsRepository.getproduct()
    }

    /**
     * Return products to observe on the UI.
     */
    fun getProducts() = productvm
}