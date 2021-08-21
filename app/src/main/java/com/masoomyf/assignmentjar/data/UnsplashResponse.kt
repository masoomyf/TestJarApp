package com.masoomyf.assignmentjar.data

import com.google.gson.annotations.SerializedName

data class UnsplashResponse(
    @SerializedName("results") val results: List<UnsplashPhoto>,
    @SerializedName("total_pages") val totalPages: Int
)