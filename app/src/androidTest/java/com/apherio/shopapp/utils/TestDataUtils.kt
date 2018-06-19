package com.apherio.shopapp.utils

import com.apherio.shopapp.model.Product
import com.apherio.shopapp.model.ProductSource


object TestDataUtils {

    fun createProductlist(vararg productlist: Product): List<Product> {
        return productlist.toList()
    }
}