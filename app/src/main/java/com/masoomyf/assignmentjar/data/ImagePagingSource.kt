package com.masoomyf.assignmentjar.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.masoomyf.assignmentjar.api.ImageService

class ImagePagingSource(
    private val service: ImageService,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val response = service.getPhotos(query, page, params.loadSize)
            val photos = response.results
            LoadResult.Page(
                data = photos,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return null
    }


    companion object {
        const val STARTING_PAGE = 1
    }
}
