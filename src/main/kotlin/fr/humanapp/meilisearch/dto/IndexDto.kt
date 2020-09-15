package fr.humanapp.meilisearch.dto

import java.util.*

data class IndexDto(
	val uid: String,
	val primaryKey: String = "id",
	val createdAt: Date? = null,
	val updatedAt: Date? = null
)
