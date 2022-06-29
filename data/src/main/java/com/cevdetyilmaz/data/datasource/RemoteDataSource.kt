package com.cevdetyilmaz.data.datasource

import com.apollographql.apollo3.api.ApolloResponse
import com.cevdetyilmaz.spacexlaunchapp.GetLaunchDetailsQuery
import com.cevdetyilmaz.spacexlaunchapp.GetLaunchesQuery

interface RemoteDataSource {
    suspend fun getLaunches(offset: Int): ApolloResponse<GetLaunchesQuery.Data>
    suspend fun getLaunchDetails(
        id: String,
        missionId: String
    ): ApolloResponse<GetLaunchDetailsQuery.Data>
}