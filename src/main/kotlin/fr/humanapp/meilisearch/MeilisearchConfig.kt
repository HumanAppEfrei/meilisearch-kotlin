package fr.humanapp.meilisearch

/**
 * Wrapper class representing the configuration for a MeilisearchClient instance
 */
public data class MeilisearchConfig(val host: String) {
	private var apiKey: String? = null

	constructor(host: String, apiKey: String) : this(host) {
		this.apiKey = apiKey
	}
}
