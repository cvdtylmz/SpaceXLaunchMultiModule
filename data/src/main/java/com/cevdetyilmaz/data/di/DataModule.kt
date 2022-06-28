package com.cevdetyilmaz.data.di

import com.apollographql.apollo3.ApolloClient
import com.cevdetyilmaz.data.datasource.RemoteDataSource
import com.cevdetyilmaz.data.datasource.RemoteDataSourceImpl
import com.cevdetyilmaz.data.mapper.LaunchesDetailMapper
import com.cevdetyilmaz.data.mapper.LaunchesMapper
import com.cevdetyilmaz.data.repository.LaunchRepositoryImpl
import com.cevdetyilmaz.domain.repository.LaunchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(apolloClient: ApolloClient): RemoteDataSource =
        RemoteDataSourceImpl(apolloClient)

    @Provides
    @Singleton
    fun provideLaunchRepository(
        mapper: LaunchesMapper,
        detailMapper: LaunchesDetailMapper,
        remoteDataSource: RemoteDataSource
    ): LaunchRepository = LaunchRepositoryImpl(remoteDataSource, mapper, detailMapper)
}