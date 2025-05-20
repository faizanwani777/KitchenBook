package com.example.kitchenbook.di

import android.content.Context
import androidx.room.Room
import com.example.kitchenbook.data.local.dao.CartDao
import com.example.kitchenbook.data.local.database.AppDatabase
import com.example.kitchenbook.data.remote.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.spoonacular.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi {
     return   retrofit.create(RecipeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "cart_recipes").build()
    }

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao {
        return db.cartDao()
    }
}