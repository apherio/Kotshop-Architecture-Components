package com.apherio.shopapp.adapter


import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.apherio.shopapp.R
import com.apherio.shopapp.model.Product
import com.apherio.shopapp.ui.activity.PhotoFullPopupWindow
import com.apherio.shopapp.utils.gone
import com.apherio.shopapp.utils.inflate
import com.apherio.shopapp.utils.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_product_item.view.*
import java.util.Collections.emptyList


/**
 * The Adapter to show the products on UI
 *
 */
class ProductAdapter(
        private val listener: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    /**
     * List of product
     */
    private var productlist: List<Product> = emptyList()

    /**
     * Inflate the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductHolder(parent.inflate(R.layout.row_product_item))

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(productHolder: ProductHolder, position: Int) = productHolder.bind(productlist[position], listener)

    /**
     * Number of items in the list to display
     */
    override fun getItemCount() = productlist.size

    override fun getItemId(position: Int): Long {
      return  super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    /**
     * View Holder Pattern
     */
    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(product: Product, listener: (Product) -> Unit) = with(itemView) {

            tvProductBrand.text = product.brand
            tvProductname.text = product.name
            tvProductCurrency.text = product.currency
            tvProductCurrentPrice.text = product.currentPrice
            tvProductPrice.text = product.originalPrice
            Glide.with(context)
                    .load(product.image.url)
                    .apply(RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.img_test_one)
                            .error(R.drawable.img_test_one)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ivProductimage)
            setOnClickListener {
                listener(product)

                PhotoFullPopupWindow(context, R.layout.popup_photo_full, ivProductimage, product.image.url, bitmap = null)
            }
            tvProductPrice.visible()
            /** Logic for showing current price only if both original price and
             *  current price are same
             *
             * */
            if (tvProductPrice.text.contains(tvProductCurrentPrice.text)) {
                tvProductPrice.gone()
            }

        }


    }

    /**
     * Swap function to set new data on updating
     */
    fun replaceItems(items: List<Product>) {
        productlist = items
        notifyDataSetChanged()
    }

}