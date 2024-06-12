package com.example.przyjeciamagazyn.Core.di

import android.app.Application
import com.example.przyjeciamagazyn.Contractors.data.repository.ContractorRepository
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModules {
    @Provides
    @Singleton
    fun provideContextDocumentRepository(app: Application): DocumentRepository {
        return DocumentRepository(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideContextContractorRepository(app: Application): ContractorRepository {
        return ContractorRepository(app.applicationContext)
    }
}
