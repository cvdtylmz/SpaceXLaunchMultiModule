package com.cevdetyilmaz.data.datasource

import com.apollographql.apollo3.api.ApolloResponse
import com.cevdetyilmaz.spacexlaunch.GetLaunchesQuery

interface RemoteDataSource {
    companion object {
        const val LIMIT = 10
    }

    suspend fun getLaunches(offset: Int): ApolloResponse<GetLaunchesQuery.Data>
}