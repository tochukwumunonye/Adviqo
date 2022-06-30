package com.example.adviqo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.adviqo.data.local.entities.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM product_database ORDER BY PK DESC LIMIT 5")
    fun returnLastProducts() : List<ProductEntity>

}
