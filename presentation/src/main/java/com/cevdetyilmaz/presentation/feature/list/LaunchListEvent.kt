package com.cevdetyilmaz.presentation.feature.list

import androidx.paging.PagingData
import com.cevdetyilmaz.domain.model.Launch

sealed class LaunchListEvent {
    object Idle : LaunchListEvent()
    class DataLoaded(val data: PagingData<Launch>) : LaunchListEvent()
}