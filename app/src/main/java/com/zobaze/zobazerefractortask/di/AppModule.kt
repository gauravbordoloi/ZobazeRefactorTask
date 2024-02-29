package com.zobaze.zobazerefractortask.di

import android.content.Context
import com.zobaze.zobazerefractortask.ZobazeApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideZobazeApp(@ApplicationContext context: Context): ZobazeApp {
        return context as ZobazeApp
    }


}