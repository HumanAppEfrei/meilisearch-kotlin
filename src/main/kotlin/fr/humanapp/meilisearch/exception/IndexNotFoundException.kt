package fr.humanapp.meilisearch.exception

class IndexNotFoundException : MeilisearchException {
	constructor(indexName: String) : super("Index \"$indexName\" not found")

	constructor(cause: Throwable) : super("Could not find index", cause)

	constructor(indexName: String, cause: Throwable) : super("Index \"$indexName\" not found", cause)
}
