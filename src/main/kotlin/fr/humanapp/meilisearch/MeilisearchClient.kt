package fr.humanapp.meilisearch

import fr.humanapp.meilisearch.exception.IndexNotFoundException


/**
 * Wrapper class to interact with a Meilisearch database instance
 */
public class MeilisearchClient(private val config: MeilisearchConfig) {
	constructor(host: String) : this(MeilisearchConfig(host))

	 init {
		 // TODO("Test connection to Meilisearch host")
	 }

	fun createIndex(name: String, primaryKey: String = "id") {
		TODO("Method not implemented")
	}

	@Throws(IndexNotFoundException::class)
	fun getIndex(s: String): Any {
		TODO("Method not implemented")
	}

	/**
	 * Dynamically get or create an index with default primaryKey (if it does not exist)
	 *
	 * @param name Name of the index to get or create
	 *
	 * @return Found or created index from the Meilisearch instance
	 */
	@Throws(MeilisearchException::class)
	fun index(name: String): Any? {
		return try {
			getIndex(name)
		} catch (notFound: IndexNotFoundException) {
			try {
				createIndex(name)
				getIndex(name)
			} catch (e: Exception) {
				throw MeilisearchException()
			}
		}
	}
}
