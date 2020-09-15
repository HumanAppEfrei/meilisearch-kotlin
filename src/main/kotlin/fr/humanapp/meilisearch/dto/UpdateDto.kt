package fr.humanapp.meilisearch.dto

import java.util.Date

data class UpdateType(
	val name: String,
	val number: Long
)

data class UpdateDto(
	val status: String,
	val updateId: Long,
	val type: UpdateType,
	val duration: Double,
	val enqueuedAt: Date,
	val processedAt: Date
)
