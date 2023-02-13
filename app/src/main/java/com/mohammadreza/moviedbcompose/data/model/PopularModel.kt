package com.mohammadreza.moviedbcompose.data.model


import com.google.gson.annotations.SerializedName

data class PopularModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: ArrayList<MovieModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)