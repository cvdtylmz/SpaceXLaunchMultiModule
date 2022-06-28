package com.cevdetyilmaz.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

abstract class FlowUseCase<in Params, Type> constructor(
    private val dispatcher: CoroutineDispatcher
) {
    abstract suspend fun getExecutable(params: Params): Flow<Type>

    suspend operator fun invoke(params: Params): Flow<Type> {
        return getExecutable(params).flowOn(dispatcher)
    }
}