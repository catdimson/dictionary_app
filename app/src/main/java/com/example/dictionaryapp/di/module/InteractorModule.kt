package com.example.dictionaryapp.di.module

import com.example.dictionaryapp.di.NAME_LOCAL
import com.example.dictionaryapp.di.NAME_REMOTE
import com.example.dictionaryapp.model.data.DataModel
import com.example.dictionaryapp.model.datasource.MainInteractor
import com.example.dictionaryapp.model.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}