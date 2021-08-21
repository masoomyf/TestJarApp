package com.masoomyf.assignmentjar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.masoomyf.assignmentjar.data.ImageRepository
import com.masoomyf.assignmentjar.data.UnsplashPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    repository: ImageRepository
) : ViewModel() {
    private val _photoList: MutableLiveData<PagingData<UnsplashPhoto>> = MutableLiveData()

    val photoList: LiveData<PagingData<UnsplashPhoto>>
        get() = _photoList

    init {
        val temp = repository.getImageResultStream("flower").cachedIn(viewModelScope)
        collectPics(temp)
    }

    private fun collectPics(photoFlow: Flow<PagingData<UnsplashPhoto>>) {
        viewModelScope.launch {
            photoFlow.collectLatest {
                _photoList.value = it
            }
        }
    }

}