package com.cevdetyilmaz.domain.usecase

import com.cevdetyilmaz.core.di.IoDispatcher
import com.cevdetyilmaz.core.util.Resource
import com.cevdetyilmaz.domain.model.LaunchDetail
import com.cevdetyilmaz.domain.repository.LaunchRepository
import com.cevdetyilmaz.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetLaunchDetailUseCase @Inject constructor(
    private val repository: LaunchRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) :
    FlowUseCase<GetLaunchDetailUseCase.Param, Resource<LaunchDetail>>(dispatcher) {

    override suspend fun getExecutable(params: Param): Flow<Resource<LaunchDetail>> {
        return repository.getLaunchDetail(params.id, params.missionId).onStart {
            emit(Resource.Loading)
        }
    }

    data class Param(val id: String, val missionId: String)
}