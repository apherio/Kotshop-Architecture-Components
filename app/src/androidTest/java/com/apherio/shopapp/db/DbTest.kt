package com.apherio.shopapp.db

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.apherio.shopapp.db.ProductsDatabase
import org.junit.After
import org.junit.Before



abstract class DbTest {

    protected lateinit var db: ProductsDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ProductsDatabase::class.java).build()
    }

    @After
    fun closeDb() = db.close()
}