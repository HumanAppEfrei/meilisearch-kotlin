package fr.humanapp.meilisearch.exception

open class MeilisearchException : Exception {
	constructor() : super("Generic Meilisearch exception")

	constructor(message: String) : super(message)

	constructor(cause: Throwable) : super(cause)

	constructor(message: String, cause: Throwable) : super(message, cause)
}
