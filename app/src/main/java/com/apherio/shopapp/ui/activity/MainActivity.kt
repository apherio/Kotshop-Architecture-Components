package com.apherio.shopapp.ui.activity


import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.apherio.shopapp.R
import com.apherio.shopapp.adapter.ProductAdapter
import com.apherio.shopapp.api.ProductSourceService
import com.apherio.shopapp.model.ProductSource
import com.apherio.shopapp.ui.ProductsViewModel
import com.apherio.shopapp.utils.getViewModel
import com.apherio.shopapp.utils.load
import com.apherio.shopapp.utils.observe
import com.apherio.shopapp.utils.toast
import kotlinx.android.synthetic.main.empty_layout.*
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.progress_layout.*
import java.util.*


/**
 * The Main or Starting Activity.
 *
 */
class MainActivity : AppCompatActivity() {

    private val productViewModel by lazy { getViewModel<ProductsViewModel>() }
    private lateinit var mRandom: Random
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    /**
     * Starting point of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)


        // Setting up RecyclerView and adapter

        product_list.setEmptyView(empty_view)
        product_list.setProgressView(progress_view)

        val adapter = ProductAdapter {
            toast("Clicked on item")

        }
        product_list.adapter = adapter
        product_list.layoutManager = LinearLayoutManager(this)

        // Observing for data change
        productViewModel.getProducts().observe(this) {
            it.load(product_list) {

                // Update the UI as the data has changed
                it?.let {

                    adapter.notifyDataSetChanged()

                    adapter.replaceItems(it)
                }

            }
        }

    }

}





