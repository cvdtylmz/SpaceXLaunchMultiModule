package com.cevdetyilmaz.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.cevdetyilmaz.core.util.Constants
import com.cevdetyilmaz.spacexlaunchapp.GetLaunchDetailsQuery
import com.cevdetyilmaz.spacexlaunchapp.GetLaunchesQuery
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apolloClient: ApolloClient) : RemoteDataSource {
    override suspend fun getLaunches(offset: Int): ApolloResponse<GetLaunchesQuery.Data> {
        return apolloClient.query(
            GetLaunchesQuery(
                offset = Optional.presentIfNotNull(offset),
                limit = Optional.presentIfNotNull(Constants.Networking.LIMIT)
            )
        ).execute()
    }

    override suspend fun getLaunchDetails(
        id: String,
        missionId: String
    ): ApolloResponse<GetLaunchDetailsQuery.Data> {
        return apolloClient.query(GetLaunchDetailsQuery(id, missionId)).execute()
    }
}