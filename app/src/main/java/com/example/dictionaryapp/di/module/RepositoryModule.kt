package com.example.dictionaryapp.di.module

import com.example.dictionaryapp.di.NAME_LOCAL
import com.example.dictionaryapp.di.NAME_REMOTE
import com.example.dictionaryapp.model.data.DataModel
import com.example.dictionaryapp.model.datasource.DataSource
import com.example.dictionaryapp.model.datasource.RetrofitImpl
import com.example.dictionaryapp.model.datasource.RoomDataBaseImpl
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.model.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> = RepositoryImpl(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> = RepositoryImpl(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> = RetrofitImpl()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImpl()
}