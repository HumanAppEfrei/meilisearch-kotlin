package fr.humanapp.meilisearch.exception

/**
 * Base Exception class for all Meilisearch-related exceptions and errors
 */
open class MeilisearchException : Exception {
	constructor() : super("Generic Meilisearch exception")

	constructor(message: String) : super(message)

	constructor(cause: Throwable) : super(cause)

	constructor(message: String, cause: Throwable) : super(message, cause)
}
