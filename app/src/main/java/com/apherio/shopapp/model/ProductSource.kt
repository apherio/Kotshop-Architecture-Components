package com.apherio.shopapp.model

import com.google.gson.annotations.SerializedName

data class ProductSource(
        @SerializedName("products") var products: List<Product> = emptyList()
)