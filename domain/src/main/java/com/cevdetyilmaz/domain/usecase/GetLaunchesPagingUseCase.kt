package com.cevdetyilmaz.domain.usecase

import androidx.paging.PagingData
import com.cevdetyilmaz.core.di.IoDispatcher
import com.cevdetyilmaz.domain.model.Launch
import com.cevdetyilmaz.domain.repository.LaunchRepository
import com.cevdetyilmaz.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLaunchesPagingUseCase @Inject constructor(
    private val repository: LaunchRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, PagingData<Launch>>(dispatcher) {

    override suspend fun getExecutable(params: Unit): Flow<PagingData<Launch>> =
        repository.getLaunches()
}