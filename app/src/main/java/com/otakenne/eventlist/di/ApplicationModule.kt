package com.otakenne.eventlist.di

import android.content.Context
import com.otakenne.eventlist.data.datasource.DataSource
import com.otakenne.eventlist.data.datasource.IDataSource
import com.otakenne.eventlist.data.repository.DataRepository
import com.otakenne.eventlist.data.repository.IDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun providesApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun providesRepository(dataSource: DataSource): IDataRepository {
        return DataRepository(dataSource)
    }

    @Provides
    @Singleton
    fun providesDataSource(context: Context): IDataSource {
        return DataSource(context)
    }
}