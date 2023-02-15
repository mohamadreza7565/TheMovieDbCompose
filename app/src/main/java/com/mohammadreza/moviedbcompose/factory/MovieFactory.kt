package com.mohammadreza.moviedbcompose.factory

import com.mohammadreza.moviedbcompose.data.model.MovieModel

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class MovieFactory {

    private val lorem =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

    fun createMokMovie(): MovieModel {
        return MovieModel(
            adult = false,
            backdropPath = "",
            belongsToCollection = null,
            budget = 1,
            genres = arrayListOf(),
            homepage = "",
            id = 1,
            imdbId = "",
            originalLanguage = "English",
            originalTitle = "Title lorem",
            overview = lorem,
            popularity = 1.0,
            posterPath = "",
            productionCompanies = arrayListOf(),
            productionCountries = arrayListOf(),
            releaseDate = "",
            revenue = 1,
            runtime = 1,
            spokenLanguages = arrayListOf(),
            status = "",
            tagline = "",
            title = "",
            video = false,
            voteAverage = 1.0,
            voteCount = 1
        )
    }

}