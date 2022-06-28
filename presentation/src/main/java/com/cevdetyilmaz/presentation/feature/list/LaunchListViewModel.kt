package com.cevdetyilmaz.presentation.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cevdetyilmaz.domain.usecase.GetLaunchesPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
    private val getLaunchesPagingUseCase: GetLaunchesPagingUseCase
) : ViewModel() {

    private val _result = MutableStateFlow<LaunchListEvent>(LaunchListEvent.Idle)
    val launchResult = _result.asStateFlow()

    init {
        getLaunchResults()
    }

    private fun getLaunchResults() {
        viewModelScope.launch {
            getLaunchesPagingUseCase.getExecutable(Unit).cachedIn(viewModelScope).collectLatest {
                _result.value = LaunchListEvent.DataLoaded(it)
            }
        }
    }
}