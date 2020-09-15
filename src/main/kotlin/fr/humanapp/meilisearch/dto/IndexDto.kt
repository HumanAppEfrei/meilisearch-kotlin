package fr.humanapp.meilisearch.dto

import java.util.*

open class IndexDto(
	/**
	 * Unique identifier of the index
	 */
	val uid: String,

	/**
	 * Primary key field for documents in that index
	 */
	val primaryKey: String = "id",

	/**
	 * Name of the index (often identical to **uid**)
	 */
	val name: String = "",

	/**
	 * Creation date
	 */
	val createdAt: Date? = null,

	/**
	 * Last update date
	 */
	val updatedAt: Date? = null
)
