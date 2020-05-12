package com.calyrsoft.framework

import com.google.gson.annotations.SerializedName

data class MovieResponse(val page: Int,
                      val results: List<Movie>,
                      @SerializedName("total_pages") val totalPage: Int,
                      @SerializedName("total_result") val totalResults: Int)

