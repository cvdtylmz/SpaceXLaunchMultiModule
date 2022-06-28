package com.cevdetyilmaz.domain.model

data class Launch(
    val id: String,
    val launchDate: String,
    val missionName: String,
    val missionId: String,
    val images: List<String>,
)