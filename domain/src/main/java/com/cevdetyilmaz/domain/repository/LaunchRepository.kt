package com.cevdetyilmaz.domain.repository

import androidx.paging.PagingData
import com.cevdetyilmaz.core.util.Resource
import com.cevdetyilmaz.domain.model.Launch
import com.cevdetyilmaz.domain.model.LaunchDetail
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {
    suspend fun getLaunches(): Flow<PagingData<Launch>>
    suspend fun getLaunchDetail(id: String, missionId: String): Flow<Resource<LaunchDetail>>
}