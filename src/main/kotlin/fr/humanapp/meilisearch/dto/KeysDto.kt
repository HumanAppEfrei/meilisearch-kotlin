package fr.humanapp.meilisearch.dto

/**
 * Meilisearch REST API response for /keys
 */
data class KeysDto(
	val private: String,
	val public: String
)
