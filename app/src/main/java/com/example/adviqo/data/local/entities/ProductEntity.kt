package com.example.adviqo.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.adviqo.domain.model.Product

@Entity(tableName = "product_database")
class ProductEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "title")
    val title: String,
    @PrimaryKey(autoGenerate =  true)
    val pk: Int = 0,
)


fun ProductEntity.toProduct() : Product {
    return Product(
        id = id,
        price = price,
        thumbnail = thumbnail,
        title = title,
    )
}
