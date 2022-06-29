package com.cevdetyilmaz.presentation.model

import com.cevdetyilmaz.core.util.Resource
import com.cevdetyilmaz.domain.model.LaunchDetail
import com.cevdetyilmaz.presentation.feature.detail.LaunchDetailEvent
import kotlinx.coroutines.flow.flowOf

object DummyLaunchDetailViewModelResponse {
    const val id = "107"
    const val missionId = "EE86F74"

    private val launchDetailResponse = LaunchDetail(
        description = "SpaceX will launch the first operational mission of its Crew Dragon vehicle as part of NASA's Commercial Crew Transportation Capability Program (CCtCap), carrying 3 NASA astronauts and 1 JAXA astronaut to the International Space Station. This mission will be the second crewed flight to launch from the United States since the end of the Space Shuttle program in 2011.",
        date = "2020-11-16T00:27:00.000Z",
        missionName = "Commercial Resupply Services",
        image = "https://live.staticflickr.com/65535/50618376646_8f52c31fc4_o.jpg",
        wikiLink = "https://en.wikipedia.org/wiki/Commercial_Resupply_Services#SpaceX",
        twitterLink = "https://twitter.com/SpaceX"
    )

    val getLaunchDetailFailureResponse = Resource.Failure<String>(error = "Error")
    val getLaunchDetailSuccessResponse = flowOf(Resource.Success(launchDetailResponse))

    val successResultData = LaunchDetailEvent.DataLoaded(launchDetailResponse)

    val failureResultData = LaunchDetailEvent.Error("Error")

}