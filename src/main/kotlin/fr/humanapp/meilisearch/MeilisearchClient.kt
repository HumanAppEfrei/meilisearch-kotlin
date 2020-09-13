package fr.humanapp.meilisearch


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

	@Throws(Exception::class)
	fun getIndex(s: String): Any {
		TODO("Method not implemented")
	}

	fun index(name: String): Any? {
		TODO("Method not implemented")
	}
}
