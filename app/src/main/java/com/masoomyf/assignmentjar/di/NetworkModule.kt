package com.masoomyf.assignmentjar.di

import com.masoomyf.assignmentjar.api.ImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideImageService(): ImageService {
        return ImageService.create()
    }
}