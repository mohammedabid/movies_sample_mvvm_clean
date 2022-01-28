package com.example.sampleapplistdetail.data.model

import android.os.Parcelable
import com.example.sampleapplistdetail.common.Constants
import com.example.sampleapplistdetail.domain.model.MoviesDetail
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesDetailResponse(
	val originalLanguage: String? = null,
	val imdbId: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val backdropPath: String? = null,
	val revenue: Int? = null,
	val genres: List<GenresItem?>? = null,
	val popularity: Double? = null,
	val productionCountries: List<ProductionCountriesItem?>? = null,
	val id: Int? = null,
	val voteCount: Int? = null,
	val budget: Int? = null,
	val overview: String? = null,
	val originalTitle: String? = null,
	val runtime: Int? = null,
	val posterPath: String? = null,
	val spokenLanguages: List<SpokenLanguagesItem?>? = null,
	val productionCompanies: List<ProductionCompaniesItem?>? = null,
	val releaseDate: String? = null,
	val voteAverage: Double? = null,
	val belongsToCollection: Boolean? = null,
	val tagline: String? = null,
	val adult: Boolean? = null,
	val homepage: String? = null,
	val status: String? = null
) : Parcelable

@Parcelize
data class GenresItem(
	val name: String? = null,
	val id: Int? = null
) : Parcelable

@Parcelize
data class ProductionCountriesItem(
	val iso31661: String? = null,
	val name: String? = null
) : Parcelable

@Parcelize
data class ProductionCompaniesItem(
	val logoPath: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val originCountry: String? = null
) : Parcelable

@Parcelize
data class SpokenLanguagesItem(
	val name: String? = null,
	val iso6391: String? = null
) : Parcelable

fun MoviesDetailResponse.toDomainMovieDetail() : MoviesDetail {
	return MoviesDetail(
		id = this.id,
		title = this.title,
		releaseDate = this.releaseDate,
		overview = this.overview,
		posterPath = Constants.ServiceConstants.IMAGE_URL + this.posterPath
	)
}