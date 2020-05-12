package com.calyrsoft.framework

import com.calyrsoft.domain.Movie as DomainMovie
import com.calyrsoft.framework.Movie as ServerMovie

fun ServerMovie.toDomainMovie() : DomainMovie = DomainMovie(title, overview, posterPath )
