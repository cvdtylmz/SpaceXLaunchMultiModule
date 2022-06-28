package com.cevdetyilmaz.presentation.feature.detail

import com.cevdetyilmaz.domain.model.LaunchDetail

sealed class LaunchDetailEvent {
    class Error(val errorMessage: String) : LaunchDetailEvent()
    object Idle : LaunchDetailEvent()
    object Loading : LaunchDetailEvent()
    class DataLoaded(val data: LaunchDetail) : LaunchDetailEvent()
}