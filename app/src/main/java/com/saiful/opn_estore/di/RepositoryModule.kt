package com.saiful.opn_estore.di

import com.saiful.opn_estore.data.DefaultRepository
import com.saiful.opn_estore.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideRepository(repository: DefaultRepository): Repository {
        return repository
    }
}
