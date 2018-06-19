package com.apherio.shopapp.api


import android.arch.lifecycle.LiveData
import com.apherio.shopapp.model.ProductSource
import com.apherio.shopapp.model.network.Resource
import com.apherio.shopapp.utils.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Fetch all the products from Api.
 */
interface ProductSourceService {

    /**
     * Retrieves all the /products
     */
    @GET("/products")
    fun getProductSource(): LiveData<Resource<ProductSource>>


    companion object Factory {
        //Api should Not be exposed but passing it for obvious learning reasons
        private const val BASE_URL = "https://private-91dd6-iosassessment.apiary-mock.com/"

        // TODO: DI can be done over
        fun getProductSourceService(): ProductSourceService {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create(ProductSourceService::class.java)
        }



    }
}
