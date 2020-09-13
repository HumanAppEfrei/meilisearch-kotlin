package fr.humanapp.meilisearch

/**
 * Wrapper class to interact with a Meilisearch database instance
 */
public class MeilisearchClient(private val config: MeilisearchConfig) {
	constructor(host: String) : this(MeilisearchConfig(host))

	 init {
		 TODO("Test connection to Meilisearch host")
	 }
}
