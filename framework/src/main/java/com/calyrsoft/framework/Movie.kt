package com.calyrsoft.framework

import com.google.gson.annotations.SerializedName

data class Movie(val title: String,
                 val overview: String,
                 @SerializedName("backdrop_path") val backdropPath: String,
                 @SerializedName("poster_path") val posterPath: String)