package com.cevdetyilmaz.data.mapper

import com.cevdetyilmaz.domain.mapper.DomainMapper
import com.cevdetyilmaz.domain.model.Launch
import com.cevdetyilmaz.spacexlaunch.GetLaunchesQuery
import javax.inject.Inject

class LaunchesMapper @Inject constructor() : DomainMapper<GetLaunchesQuery.LaunchesPast, Launch> {
    override fun mapToDomainModel(dataModel: GetLaunchesQuery.LaunchesPast): Launch {
        return Launch(
            id = dataModel.id.orEmpty(),
            launchDate = dataModel.launch_date_local.toString(),
            missionName = dataModel.mission_name.orEmpty(),
            missionId = if(dataModel.mission_id.isNullOrEmpty()) "Not Found" else dataModel.mission_id.first().orEmpty(),
            images = dataModel.links?.flickr_images.orEmpty().filterNotNull()
        )
    }
}