package com.apherio.shopapp.model

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json


@Entity(tableName = "product_list")
data class Product(
        @PrimaryKey(autoGenerate = true) var p_id: Int = 0,
        @SerializedName("identifier") var identifier: Int = 0, // 255
        @SerializedName("name") var name: String, // Grass seeds
        @SerializedName("brand") var brand: String, // Farmer Bob
        @SerializedName("original_price") var originalPrice: String, // 10
        @SerializedName("current_price") var currentPrice: String, // 5
        @SerializedName("currency") var currency: String? = null,
        @SerializedName("image")
        @Json(name = "image")
        @Embedded
        var image: Image) {
        @Ignore
        @Relation(parentColumn = "image", entityColumn = "img_url", entity = Image::class)
        var img: List<Image> = ArrayList()
}




