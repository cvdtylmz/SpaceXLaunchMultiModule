package com.cevdetyilmaz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cevdetyilmaz.data.datasource.GetLaunchesPagingSource
import com.cevdetyilmaz.data.datasource.RemoteDataSource
import com.cevdetyilmaz.data.mapper.LaunchesMapper
import com.cevdetyilmaz.domain.model.Launch
import com.cevdetyilmaz.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val launchesMapper: LaunchesMapper,
) : LaunchRepository {

    override suspend fun getLaunches(): Flow<PagingData<Launch>> {
        return Pager(
            PagingConfig(
                pageSize = RemoteDataSource.LIMIT,
                enablePlaceholders = true
            )
        ) {
            GetLaunchesPagingSource(remoteDataSource)
        }.flow.map {
            it.map { result ->
                launchesMapper.mapToDomainModel(result)
            }
        }
    }
}