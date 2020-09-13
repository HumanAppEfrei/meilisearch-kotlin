package fr.humanapp.meilisearch

import fr.humanapp.meilisearch.exception.IndexNotFoundException
import fr.humanapp.meilisearch.exception.MeilisearchException
import java.lang.Exception


/**
 * Wrapper class to interact with a Meilisearch database instance
 */
public class MeilisearchClient(private val config: MeilisearchConfig) {
	@Throws(MeilisearchException::class)
	constructor(host: String) : this(MeilisearchConfig(host)) {
		TODO("Test connection to Meilisearch host not implemented")
	}

	/**
	 * Create an index
	 *
	 * @param name Name of the index to create
	 * @param primaryKey ID field for documents in that index (all documents must contain this key with a different value)
	 *
	 * @return Newly created index in the Meilisearch instance
	 */
	@JvmOverloads
	@Throws(MeilisearchException::class)
	fun createIndex(name: String, primaryKey: String = "id"): Any {
		TODO("Method not implemented")
	}

	/**
	 * Retrieve an index
	 *
	 * @param name Name of the index to retrieve
	 *
	 * @return Index from the Meilisearch instance
	 */
	@Throws(IndexNotFoundException::class)
	fun getIndex(name: String): Any {
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
