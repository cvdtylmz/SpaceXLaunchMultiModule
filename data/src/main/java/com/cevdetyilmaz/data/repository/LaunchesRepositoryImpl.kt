package com.cevdetyilmaz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cevdetyilmaz.core.util.Constants
import com.cevdetyilmaz.core.util.Resource
import com.cevdetyilmaz.data.datasource.GetLaunchesPagingSource
import com.cevdetyilmaz.data.datasource.RemoteDataSource
import com.cevdetyilmaz.data.mapper.LaunchesDetailMapper
import com.cevdetyilmaz.data.mapper.LaunchesMapper
import com.cevdetyilmaz.domain.model.Launch
import com.cevdetyilmaz.domain.model.LaunchDetail
import com.cevdetyilmaz.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val launchesMapper: LaunchesMapper,
    private val launchesDetailMapper: LaunchesDetailMapper
) : LaunchRepository {

    override suspend fun getLaunches(): Flow<PagingData<Launch>> {
        return Pager(
            PagingConfig(
                pageSize = Constants.Networking.LIMIT,
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

      override suspend fun getLaunchDetail(
        id: String,
        missionId: String
    ): Flow<Resource<LaunchDetail>> = flow {
        val response = remoteDataSource.getLaunchDetails(id, missionId)
        if (response.hasErrors()) {
            emit(Resource.Failure(response.errors?.firstOrNull()?.message.orEmpty()))
        } else emit(Resource.Success(launchesDetailMapper.mapToDomainModel(response.data)))
    }
}