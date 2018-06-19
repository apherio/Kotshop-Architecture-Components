package com.apherio.shopapp.api

import com.apherio.shopapp.utils.LiveDataCallAdapterFactory
import com.apherio.shopapp.utils.LiveDataTestUtil
import com.apherio.shopapp.utils.create
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


@RunWith(JUnit4::class)
class ProductSourceServiceTest : BaseServiceTest() {

    private lateinit var service: ProductSourceService

    @Before
    @Throws(IOException::class)
    fun createService() {
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create()
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun getProductSource() {
        enqueueResponse("products_source.json")
        val productSource = LiveDataTestUtil.getValue(service.getProductSource()).data

        // Dummy request
        mockWebServer.takeRequest()

        // Check product source
        assertThat(productSource, notNullValue())
        // Check list
        val products = productSource?.products
        assertThat(products, notNullValue())

        // Check item 1
        val product1 = products?.get(0)
        assertThat(product1, notNullValue())
        if (product1 != null) {
            assertThat(product1.name, `is`("Buggati Veyron"))
            assertThat(product1.brand, `is`("Buggati"))
            assertThat(product1.currentPrice, `is`("100000"))
        }
    }
}