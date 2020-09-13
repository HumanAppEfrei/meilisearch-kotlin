package fr.humanapp.meilisearch.exception

/**
 * Conveys the message that an index does not exist when an attempt to retrieve it is made
 */
class IndexNotFoundException : MeilisearchException {
	constructor(indexName: String) : super("Index \"$indexName\" not found")

	constructor(cause: Throwable) : super("Could not find index", cause)

	constructor(indexName: String, cause: Throwable) : super("Index \"$indexName\" not found", cause)
}
