package com.cevdetyilmaz.domain.repository

import androidx.paging.PagingData
import com.cevdetyilmaz.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {
    suspend fun getLaunches(): Flow<PagingData<Launch>>
}