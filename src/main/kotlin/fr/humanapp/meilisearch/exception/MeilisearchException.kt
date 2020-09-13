package fr.humanapp.meilisearch.exception

abstract class MeilisearchException : Exception {
	constructor(message: String) : super(message)

	constructor(cause: Throwable) : super(cause)

	constructor(message: String, cause: Throwable) : super(message, cause)
}
