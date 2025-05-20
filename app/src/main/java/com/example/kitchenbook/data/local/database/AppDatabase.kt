package com.example.kitchenbook.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kitchenbook.data.local.dao.CartDao
import com.example.kitchenbook.data.local.entity.CartRecipe

@Database(entities = [CartRecipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}