package com.example.adviqo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adviqo.data.local.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 6, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}