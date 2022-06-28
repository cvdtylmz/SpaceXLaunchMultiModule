package com.cevdetyilmaz.data.mapper

import com.cevdetyilmaz.domain.mapper.DomainMapper
import com.cevdetyilmaz.domain.model.LaunchDetail
import com.cevdetyilmaz.spacexlaunch.GetLaunchDetailsQuery
import javax.inject.Inject

class LaunchesDetailMapper @Inject constructor() : DomainMapper<GetLaunchDetailsQuery.Data?, LaunchDetail> {
    override fun mapToDomainModel(dataModel: GetLaunchDetailsQuery.Data?): LaunchDetail {
        return LaunchDetail(
            description = dataModel?.launch?.details.orEmpty(),
            missionName = dataModel?.mission?.name.orEmpty(),
            twitterLink = dataModel?.mission?.twitter.orEmpty(),
            wikiLink = dataModel?.mission?.wikipedia.orEmpty(),
            image = dataModel?.launch?.links?.flickr_images?.firstOrNull().orEmpty(),
            date = dataModel?.launch?.launch_date_utc.toString()
        )
    }
}