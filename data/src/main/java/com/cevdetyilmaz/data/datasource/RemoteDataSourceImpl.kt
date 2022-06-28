package com.cevdetyilmaz.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.cevdetyilmaz.spacexlaunch.GetLaunchesQuery
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apolloClient: ApolloClient) : RemoteDataSource {
    override suspend fun getLaunches(offset: Int): ApolloResponse<GetLaunchesQuery.Data> {
        return apolloClient.query(
            GetLaunchesQuery(
                offset = Optional.presentIfNotNull(offset),
                limit = Optional.presentIfNotNull(RemoteDataSource.LIMIT)
            )
        ).execute()
    }
}