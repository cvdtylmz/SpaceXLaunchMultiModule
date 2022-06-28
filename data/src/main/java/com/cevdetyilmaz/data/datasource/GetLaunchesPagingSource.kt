package com.cevdetyilmaz.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cevdetyilmaz.spacexlaunch.GetLaunchesQuery
import javax.inject.Inject

class GetLaunchesPagingSource @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, GetLaunchesQuery.LaunchesPast>() {
    override fun getRefreshKey(state: PagingState<Int, GetLaunchesQuery.LaunchesPast>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetLaunchesQuery.LaunchesPast> {
        return try {
            val nextPageNumber = params.key ?: 0
            val nextPageWithOffset = ((params.key ?: 0) * RemoteDataSource.LIMIT)
            val response = remoteDataSource.getLaunches(nextPageWithOffset)
            val keyData = response.data?.launchesPast
            if (response.hasErrors() || keyData == null) {
                val e = Exception(response.errors?.firstOrNull()?.message)
                return LoadResult.Error(e)
            }
            LoadResult.Page(
                data = keyData.mapNotNull { it },
                nextKey = if (keyData.isNullOrEmpty()) null else nextPageNumber + 1,
                prevKey = if (nextPageNumber == 0) null else nextPageNumber - 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}