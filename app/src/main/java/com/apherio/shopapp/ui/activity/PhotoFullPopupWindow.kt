package com.apherio.shopapp.ui.activity

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.support.v7.graphics.Palette
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.ProgressBar
import com.apherio.shopapp.R
import com.apherio.shopapp.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.chrisbanes.photoview.PhotoView

/**
 * Created by Apherio .
 */
class PhotoFullPopupWindow(internal var mContext: Context, layout: Int, v: View, imageUrl: String, bitmap: Bitmap?) : PopupWindow((mContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.popup_photo_full, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {

    internal var view: View
    internal var photoView: PhotoView
    internal var loading: ProgressBar
    internal var parent: ViewGroup


    init {

        if (Build.VERSION.SDK_INT >= 21) {
            elevation = 5.0f
        }
        this.view = contentView
        val closeButton = this.view.findViewById<View>(R.id.ib_close) as ImageButton
        isOutsideTouchable = true

        isFocusable = true
        // Set a click listener for the popup window close button
        closeButton.setOnClickListener {
            // Dismiss the popup window
            dismiss()
        }
        //---------Begin customising this popup--------------------

        photoView = view.findViewById<View>(R.id.image) as PhotoView
        loading = view.findViewById<View>(R.id.loading) as ProgressBar
        photoView.maximumScale = 6f
        parent = photoView.parent as ViewGroup
        // ImageUtils.setZoomable(imageView);
        //----------------------------
        if (bitmap != null) {
            loading.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= 16) {
                parent.background = BitmapDrawable(mContext.resources, Constants.fastblur(Bitmap.createScaledBitmap(bitmap, 50, 50, true)))// ));
            } else {
                onPalette(Palette.from(bitmap).generate())

            }
            photoView.setImageBitmap(bitmap)
        } else {
            loading.isIndeterminate = true
            loading.visibility = View.VISIBLE
            Glide.with(mContext).asBitmap()
                    .load(imageUrl)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
                            loading.isIndeterminate = false
                            loading.setBackgroundColor(Color.LTGRAY)
                            return false
                        }


                        override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            if (Build.VERSION.SDK_INT >= 16) {
                                parent.background = BitmapDrawable(mContext.resources, Constants.fastblur(Bitmap.createScaledBitmap(resource, 50, 50, true)))// ));
                            } else {
                                onPalette(Palette.from(resource).generate())

                            }
                            photoView.setImageBitmap(resource)

                            loading.visibility = View.GONE
                            return false
                        }
                    })

                    .into(photoView)

            showAtLocation(v, Gravity.CENTER, 0, 0)
        }
        //------------------------------

    }

    fun onPalette(palette: Palette?) {
        if (null != palette) {
            val parent = photoView.parent.parent as ViewGroup
            parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY))
        }
    }

    companion object {
        private val instance: PhotoFullPopupWindow? = null
    }

}