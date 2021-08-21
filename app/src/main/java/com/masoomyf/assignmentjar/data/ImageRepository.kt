package com.masoomyf.assignmentjar.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.masoomyf.assignmentjar.api.ImageService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepository @Inject constructor(private val service: ImageService) {

    fun getImageResultStream(query: String): Flow<PagingData<UnsplashPhoto>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { ImagePagingSource(service, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}