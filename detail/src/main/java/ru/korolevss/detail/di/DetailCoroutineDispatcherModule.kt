package ru.korolevss.detail.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DetailCoroutineDispatcherModule {

    @Provides
    fun provideCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.IO
}