package com.example.przyjeciamagazyn.Core.di

import android.app.Application
import com.example.przyjeciamagazyn.Receipts.data.repository.ReceiptRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun provideContextTopHits(app: Application): ReceiptRepository {
        return ReceiptRepository(app.applicationContext)
    }
}
