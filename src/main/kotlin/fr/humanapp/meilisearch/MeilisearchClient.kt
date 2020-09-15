package fr.humanapp.meilisearch

import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import com.github.kittinunf.result.Result

import fr.humanapp.meilisearch.dto.IndexDto
import fr.humanapp.meilisearch.exception.IndexNotFoundException
import fr.humanapp.meilisearch.exception.MeilisearchException

/**
 * Wrapper class to interact with a Meilisearch database instance
 */
public class MeilisearchClient(private val config: MeilisearchConfig) {
	private val gson: Gson = Gson()

	@Throws(MeilisearchException::class)
	constructor(host: String) : this(MeilisearchConfig(host))

	init {
		// Asynchronously attempt to GET /health endpoint
		this.config.healthPath.httpGet().response {
				_, response, _ ->
			if (response.statusCode != 200) throw MeilisearchException("Unable to reach \"${this.config.host}/health\"")
		}
	}

	/**
	 * Retrieve an index
	 *
	 * @param name Name of the index to retrieve
	 *
	 * @return Index from the Meilisearch instance
	 */
	@Throws(IndexNotFoundException::class)
	fun getIndex(name: String): MeilisearchIndex {
		val (_, _, result) = this.config.indexPath(name).httpGet().responseString()

		if (result is Result.Failure) throw IndexNotFoundException("Cannot find index $name", result.getException())

		return gson.fromJson(result.get(), MeilisearchIndex::class.java)
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
	fun createIndex(name: String, primaryKey: String = "id"): MeilisearchIndex {
		val indexToCreate = IndexDto(uid = name, primaryKey = primaryKey)

		// Call Meilisearch REST API to create index
		val (_, _, result) = this.config.indexesPath.httpPost()
			.body(gson.toJson(indexToCreate))
			.responseString()

		if (result is Result.Failure) throw MeilisearchException("Unable to create index $name", result.getException())

		return this.getIndex(name)
	}

	/**
	 * Dynamically get or create an index with default primaryKey (if it does not exist)
	 *
	 * @param name Name of the index to get or create
	 *
	 * @return Found or created index from the Meilisearch instance
	 */
	@Throws(MeilisearchException::class)
	fun index(name: String): MeilisearchIndex {
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

	@Throws(MeilisearchException::class)
	fun deleteIndex(name: String) {
		// Call Meilisearch REST API to delete index
		val (_, _, result) = this.config.indexPath(name).httpDelete().response()

		if (result is Result.Failure) {
			throw MeilisearchException("Error while deleting index $name", result.getException())
		}
	}
}
