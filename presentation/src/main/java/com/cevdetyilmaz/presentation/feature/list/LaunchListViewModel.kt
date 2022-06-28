package com.cevdetyilmaz.presentation.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cevdetyilmaz.domain.model.Launch
import com.cevdetyilmaz.domain.usecase.GetLaunchesPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
    private val getLaunchesPagingUseCase: GetLaunchesPagingUseCase
) : ViewModel() {

    private val _launchResult = MutableLiveData<PagingData<Launch>>()
    val searchResult: LiveData<PagingData<Launch>>
        get() = _launchResult

    fun getLaunchResults() {
        viewModelScope.launch {
            getLaunchesPagingUseCase.getExecutable(Unit).cachedIn(viewModelScope).collectLatest {
                _launchResult.value = it
            }
        }
    }
}