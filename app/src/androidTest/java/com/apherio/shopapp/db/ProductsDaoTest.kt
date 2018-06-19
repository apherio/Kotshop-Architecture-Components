package com.apherio.shopapp.db

import android.support.test.runner.AndroidJUnit4
import com.apherio.shopapp.model.Image
import com.apherio.shopapp.model.Product
import com.apherio.shopapp.utils.LiveDataTestUtil
import com.apherio.shopapp.utils.TestDataUtils
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProductsDaoTest: DbTest() {

        @Test
        @Throws(InterruptedException::class)
        fun insertProducts() {
                val products = TestDataUtils.createProductlist(Product(1, 1233, "Hello", "nike", "123", "134", "euro", Image("")),
                        Product(1, 1233, "Hello", "nike", "123", "134", "euro", Image("")))
                assertThat(db.ProductsDao().insertProducts(products),`is`(listOf(1L,2L)))
        }

        @Test
        @Throws(InterruptedException::class)
        fun insertProductAndRead() {
                val listing = TestDataUtils.createProductlist(
                        Product(1, 1233, "Hello", "nike", "123", "134", "euro", Image("")),
                        Product(2, 12334, "Hellosas", "nikedasd", "1233", "1343", "euro",Image("")))
                db.ProductsDao().insertProducts(listing)

                val loaded = LiveDataTestUtil.getValue(db.ProductsDao().getProducts())
                assertThat(loaded.size, `is`(2))

                // 1st
                val first = loaded[0]
                assertThat(first.brand, `is`("nike"))
                assertThat(first.identifier, `is`(1233))

                // 2nd
                val second = loaded[1]
                assertThat(second.name, `is`("Hellosas"))
                assertThat(second.brand, `is`("nikedasd"))
        }

}