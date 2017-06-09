package com.tobi.movies.popularstream

import com.google.gson.annotations.SerializedName

data class ApiMoviePoster(@SerializedName("id") var movieId: Long,
                          @SerializedName("poster_path") var posterPath: String)
