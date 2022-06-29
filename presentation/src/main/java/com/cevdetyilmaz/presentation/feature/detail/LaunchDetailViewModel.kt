package com.cevdetyilmaz.presentation.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetyilmaz.core.util.Constants
import com.cevdetyilmaz.core.util.Resource
import com.cevdetyilmaz.domain.usecase.GetLaunchDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailViewModel @Inject constructor(
    private val detailUseCase: GetLaunchDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<String>(Constants.Bundle.launchId).orEmpty()
    private val missionId = savedStateHandle.get<String>(Constants.Bundle.missionId).orEmpty()

    private val _detailFlow = MutableStateFlow<LaunchDetailEvent>(LaunchDetailEvent.Idle)
    val detailFlow = _detailFlow.asStateFlow()

    init {
        getLaunchDetail()
    }

    fun getLaunchDetail() {
        viewModelScope.launch {
            val param = GetLaunchDetailUseCase.Param(id, missionId)
            detailUseCase.getExecutable(param).collectLatest {
                when (it) {
                    Resource.Loading -> _detailFlow.value = LaunchDetailEvent.Loading
                    is Resource.Success -> _detailFlow.value = LaunchDetailEvent.DataLoaded(it.data)
                    is Resource.Failure -> _detailFlow.value = LaunchDetailEvent.Error(it.error)
                }
            }
        }
    }
}
